package View.dialog;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import Model.DownLoadBar;

public class DownLoadDialog extends JDialog{
	private ArrayList<String> cmtList = new ArrayList<String>();
	private ArrayList<String> strList;
	
	public DownLoadDialog(JFrame f,String s, ArrayList<String> strList) { 				//构造方法	
	       super(f,s);
	       this.strList = strList;
	       
	       setBounds(800, 400, 350, 200);		//设置大小，要与验证图片匹配
	       final JPanel panel = new JPanel();  
           final JLabel label = new JLabel();
           
           JLabel title = new JLabel("                                 下载管理");
           add(title,BorderLayout.NORTH);
           
	       JPanel stp = new JPanel();  
           JProgressBar jpb = new JProgressBar();  
           jpb.setMinimum(1);  
           jpb.setMaximum(strList.size());  
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
           DownLoadBar sw = new DownLoadBar(label, panel, jpb, cmtList, strList);  
           sw.execute();
	       //add(layeredPane,BorderLayout.CENTER);	//加入到验证对话框的中间       	       
	}
}
