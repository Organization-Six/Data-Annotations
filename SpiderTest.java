package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Spider;
import Model.Spider.Data;

public class SpiderTest {

	private Spider spider;
	//private ArrayList<String> comments;
	
    @Before
    public void setUp() throws Exception {
    	spider = new Spider();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getComments() {
        assertNotNull(spider.Load("SZ002304"));
    }

    @Test
    public void downLoad() {
    	ArrayList<Data> emptyComments = Spider.Load("");
    	assertNotNull(emptyComments);
    	ArrayList<Data> jsonComments = spider.Load("SZ002304");
        assertTrue(jsonComments.get(0).getComment().contains("洋河股份"));
    }



}
