package Model;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;  
import javax.swing.SwingWorker;

import View.IndexView; 

public class DownLoadBar extends SwingWorker<ArrayList<String>,String>{  
	  
    private JLabel status;  
    private JPanel viewer;    
    private JProgressBar jpb;
    private ArrayList<String> cmtList;
    private ArrayList<String> strList;
    public static boolean isStop = false;
    
	public DownLoadBar(JLabel status, JPanel viewer, JProgressBar jpb, ArrayList<String> cmtList, ArrayList<String> strList){  
        this.status = status;  
        this.viewer = viewer;    
        this.jpb = jpb;
        this.cmtList = cmtList;
        this.strList = strList;
    }  
  
    @Override  
    protected ArrayList<String> doInBackground() throws Exception {   
        for(int i = 0; i < strList.size(); i++){
        	if(isStop) {
        		break;
        	}
        	cmtList.add(strList.get(i));
        	Thread.sleep(3000);
        	System.out.println("已经加载了  "+(i+1)+"/"+strList.size());
            publish("已经加载了  ("+(i+1)+")/"+strList.size());  
        }  
        return cmtList;  
    }  
    
    @Override
    protected void process(List<String> chunks) {  
        status.setText(chunks.get(chunks.size()-1));
        System.out.println("chunks:"+chunks.get(chunks.size()-1).substring(chunks.get(chunks.size()-1).indexOf("(")+1,chunks.get(chunks.size()-1).indexOf(")")).trim());
        int x = Integer.parseInt(chunks.get(chunks.size()-1).substring(chunks.get(chunks.size()-1).indexOf("(")+1,chunks.get(chunks.size()-1).indexOf(")")).trim());  
        jpb.setValue(x);  
          
        for(String str : chunks){  
            System.out.println(str);  
        }  
    }  
  
    @Override  
    protected void done() {
    	JOptionPane.showMessageDialog(null, "下载完成");
    }  

}
