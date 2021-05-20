package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Comment;
import Model.CommentBank;

public class CommentBankTest {

	private CommentBank cmtBankBackup;

    @Before
    public void setUp() throws Exception {
    	cmtBankBackup = new CommentBank();
    	cmtBankBackup.Load();
    }

    @After
    public void tearDown() throws Exception {
    	cmtBankBackup.Save();
    }

    @Test
    public void getComments() {
    	//assertEquals(true, isCorrect);
    	assertNotNull(cmtBankBackup.getComment());
    	CommentBank cmtBankSaver = new CommentBank();
        assertNotNull(cmtBankSaver.getComment());
        assertEquals(0, cmtBankSaver.getComment().size());
    }

    @Test
    public void save() {
    	CommentBank cmtBankSaverTest = new CommentBank();
        assertEquals(0, cmtBankSaverTest.getComment().size());
        cmtBankSaverTest.getComment().add(new Comment("中国平安", "maluko", "2021-5-2"));
        cmtBankSaverTest.getComment().add(new Comment("中国人寿", "张三", "2021-5-4"));
        cmtBankSaverTest.Save();

        CommentBank cmtBankLoader = new CommentBank();
        cmtBankLoader.Load();

        assertNotNull(cmtBankLoader.getComment());
        assertEquals(cmtBankSaverTest.getComment().size(),cmtBankLoader.getComment().size());
        for(int i = 0; i < cmtBankSaverTest.getComment().size(); i++){
            Comment cmtthis = cmtBankSaverTest.getComment().get(i);
            Comment cmtthat = cmtBankLoader.getComment().get(i);
            assertEquals(cmtthat.getCmtText(),cmtthis.getCmtText());
            assertEquals(cmtthat.getCmtWriter(),cmtthis.getCmtWriter());
            assertEquals(cmtthat.getCmtTime(),cmtthis.getCmtTime());
            
            assertEquals(cmtthat.getLabelList().size(),cmtthis.getLabelList().size());
            for(int j = 0; j < cmtthat.getLabelList().size(); j++) {
            	assertEquals(cmtthat.getLabelList().get(j), cmtthis.getLabelList().get(j));
            }
            assertEquals(cmtthat.getLabelList(),cmtthis.getLabelList());
        }
    }

    @Test
    public void saveEmpty() {
    	CommentBank cmtBank = new CommentBank();
        assertEquals(0, cmtBank.getComment().size());
        cmtBank.Save();

        CommentBank cmtBankLoader = new CommentBank();
        cmtBankLoader.Load();

        assertNotNull(cmtBankLoader.getComment());
        assertEquals(0,cmtBankLoader.getComment().size());
    }

}
