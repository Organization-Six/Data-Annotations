package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Label;
import Model.LabelBank;

public class LabelBankTest {

	private LabelBank labBankBackup;

    @Before
    public void setUp() throws Exception {
    	labBankBackup = new LabelBank();
    	labBankBackup.Load("label.txt");
    }

    @After
    public void tearDown() throws Exception {
    	labBankBackup.Save("label.txt");
    }

    @Test
    public void getComments() {
    	//assertEquals(true, isCorrect);
    	assertNotNull(labBankBackup.getLabel());
    	LabelBank labBankSaver = new LabelBank();
        assertNotNull(labBankSaver.getLabel());
        assertEquals(0, labBankSaver.getLabel().size());
    }

    @Test
    public void save() {
    	LabelBank labBankSaverTest = new LabelBank();
        assertEquals(0, labBankSaverTest.getLabel().size());
        labBankSaverTest.getLabel().add(new Label("身高",new ArrayList<String>(Arrays.asList("高","矮"))));
        labBankSaverTest.getLabel().add(new Label("体重",new ArrayList<String>(Arrays.asList("胖","瘦","中等"))));
        labBankSaverTest.Save("label.txt");

        LabelBank labBankLoader = new LabelBank();
        labBankLoader.Load("label.txt");

        assertNotNull(labBankLoader.getLabel());
        assertEquals(labBankSaverTest.getLabel().size(),labBankLoader.getLabel().size());
        for(int i = 0; i < labBankSaverTest.getLabel().size(); i++){
            Label labthis = labBankSaverTest.getLabel().get(i);
            Label labthat = labBankLoader.getLabel().get(i);
            assertEquals(labthat.getLabType(),labthis.getLabType());
            
            assertEquals(labthat.getLabChoise().size(),labthis.getLabChoise().size());
            for(int j = 0; j < labthat.getLabChoise().size(); j++) {
            	assertEquals(labthat.getLabChoise().get(j), labthis.getLabChoise().get(j));
            }
            
        }
    }

    @Test
    public void saveEmpty() {
    	LabelBank labBank = new LabelBank();
        assertEquals(0, labBank.getLabel().size());
        labBank.Save("label.txt");

        LabelBank labBankLoader = new LabelBank();
        labBankLoader.Load("label.txt");

        assertNotNull(labBankLoader.getLabel());
        assertEquals(0,labBankLoader.getLabel().size());
    }

}
