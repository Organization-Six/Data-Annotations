package View.dialog;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import Model.Comment;
import Model.DownLoadBar;
import Model.Spider.Data;

public class DownLoadDialog extends JDialog{
	private ArrayList<Comment> cmtList;
	private ArrayList<Data> dataList;
	
	public DownLoadDialog(JFrame f,String s, ArrayList<Comment> cmtList, ArrayList<Data> dataList) { 				//构造方法	
	       super(f,s);
	       this.cmtList = cmtList;
	       this.dataList = dataList;
	       
	       setBounds(800, 400, 350, 200);
	       final JPanel panel = new JPanel();  
           final JLabel label = new JLabel();
           
           JLabel title = new JLabel("                                 下载管理");
           add(title,BorderLayout.NORTH);
           
	       JPanel stp = new JPanel();  
           JProgressBar jpb = new JProgressBar();  
           jpb.setMinimum(1);  
           jpb.setMaximum(dataList.size());  
           stp.add(jpb);  
           stp.add(label);
           add(stp,BorderLayout.CENTER);

           JButton stopBtn = new JButton("终止");
           //SZ002304
   		   add(stopBtn,BorderLayout.SOUTH);
   		   stopBtn.addActionListener(new ActionListener() {
   			   public void actionPerformed(ActionEvent e) {
   				   DownLoadBar.isStop = true;
   				   setVisible(false);
   				   dispose();
   			   }
   		   });
           DownLoadBar sw = new DownLoadBar(label, panel, jpb, cmtList, dataList);  
           sw.execute();       	       
	}
}
