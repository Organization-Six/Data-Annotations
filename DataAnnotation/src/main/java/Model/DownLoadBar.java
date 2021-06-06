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

import org.springframework.stereotype.Component;

import Model.Spider.Data;
import View.IndexView; 

@Component
public class DownLoadBar extends SwingWorker<ArrayList<Comment>,String>{  
	  
    private JLabel status;  
    private JPanel viewer;    
    private JProgressBar jpb;
    private ArrayList<Comment> cmtList;
    private ArrayList<Data> dataList;
    public static boolean isStop = false;
    
	public DownLoadBar(JLabel status, JPanel viewer, JProgressBar jpb, ArrayList<Comment> cmtList, ArrayList<Data> dataList){  
        this.status = status;  
        this.viewer = viewer;    
        this.jpb = jpb;
        this.cmtList = cmtList;
        this.dataList = dataList;
    }  
  
    @Override  
    protected ArrayList<Comment> doInBackground() throws Exception {
    	LogAspect.Log( "Model.DownLoadBar" , "DownLoadBar");
        for(int i = 0; i < dataList.size(); i++){
        	if(isStop) {
        		break;
        	}
        	cmtList.add(new Comment(dataList.get(i).getComment(), dataList.get(i).getName(), dataList.get(i).getTime()));
        	Thread.sleep(20);
        	System.out.println("已经加载了  "+(i+1)+"/"+dataList.size());
            publish("已经加载了  ("+(i+1)+")/"+dataList.size());  
        }  
        return cmtList;  
    }  
    
    @Override
    protected void process(List<String> chunks) {
    	LogAspect.Log( "Model.DownLoadBar" , "process");
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
