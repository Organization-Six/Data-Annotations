package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.springframework.stereotype.Component;

import Model.CommentBank;
import Model.LabelBank;
import Model.LogAnnotation;
import View.ChartView;
import View.CreateLabelView;
import View.IndexView;
import View.PasteView;
import View.ShowLabelView;
import View.UpdateLabelView;

@Component
public class ShowLabelController {
	private CommentBank cmtBank;
	private LabelBank labBank;
	private final int index;
	private String[] labels;
	String[] columnNames = { "标签类型", "标签项" };
	String[][] rowData;
	public int row;
	
	public ShowLabelController(final CommentBank cmtBank, final LabelBank labBank, final int index,final ShowLabelView view) {
		// TODO 自动生成的构造函数存根
		this.cmtBank = cmtBank;
		this.labBank = labBank;
		this.index = index;
		initData();
		
		ShowLabelView.labelTable = new JTable(rowData, columnNames);
		ShowLabelView.chartIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index == -1) {
					ChartView chartview = new ChartView(cmtBank, labBank, -1);
					chartview.setLocation(view.getLocation());
					chartview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					chartview.setVisible(true);
					view.dispose();
				}
			}
		});
		ShowLabelView.okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index == -1) {
					IndexView indexView = new IndexView(cmtBank, labBank);
					indexView.setLocation(view.getLocation());
					indexView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					indexView.setVisible(true);
					view.dispose();
				}
				else {
					PasteView pasteview = new PasteView(cmtBank, labBank, index);
					pasteview.setLocation(view.getLocation());
					pasteview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					pasteview.setVisible(true);
					view.dispose();
				}
			}
		});
		ShowLabelView.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index == -1) {
					IndexView indexView = new IndexView(cmtBank, labBank);
					indexView.setLocation(view.getLocation());
					indexView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					indexView.setVisible(true);
					view.dispose();
				}
				else {
					PasteView pasteview = new PasteView(cmtBank, labBank, index);
					pasteview.setLocation(view.getLocation());
					pasteview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					pasteview.setVisible(true);
					view.dispose();
				}
			}
		});
		
		ShowLabelView.jm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根	
				UpdateLabelView updateLabelView = new UpdateLabelView(cmtBank, labBank, index, row);
				updateLabelView.setLocation(view.getLocation());
				updateLabelView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				updateLabelView.setVisible(true);
				view.dispose();
			}		
		});	
		ShowLabelView.jm2.addActionListener(new ActionListener() {
			@LogAnnotation(className = "Controller.ShowLabelController" , content = "remove label")
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
//删除项响应		
				if(row >= 0) {
					System.out.println(labBank.getLabel().size()+"   111   " +row);
					labBank.getLabel().remove(row);
					//initData();
					//ShowLabelView.this.revalidate();
					ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
					showlabelview.setLocation(view.getLocation());
					showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					showlabelview.setVisible(true);
					view.dispose();
					
					System.out.println(labBank.getLabel().size()+"222" + row);
				}
			}		
		});	
		ShowLabelView.labelTable.addMouseListener(new MouseAdapter(){
	           @Override 
	 		   public void mousePressed(MouseEvent e) { 
	        	   if(e.isPopupTrigger()) {
	            		
	        	   }
	 		   } 
	 		   @Override 
	 		   public void mouseReleased(MouseEvent e) {
	 			  if(e.getButton() == MouseEvent.BUTTON3) {
	 				 row = ShowLabelView.labelTable.getSelectedRow();
	 				 System.out.println(row);
	 				 if(row >= 0) {
	 					ShowLabelView.popupMenu.show(e.getComponent(),e.getX(), e.getY()); 
	 				 }
	 			  }
	 		   }  	  				

	        }); 
		ShowLabelView.createLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateLabelView createlabelview = new CreateLabelView(cmtBank, labBank, index);
				createlabelview.setLocation(view.getLocation());
				createlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				createlabelview.setVisible(true);
				view.dispose();
			}
		});
		
	}
	private void initData() {
		int n = labBank.getLabel().size();
		rowData = new String[n][2];
		for(int i = 0; i < n; i++) {
			rowData[i][0] = labBank.getLabel().get(i).getLabType();
			String choices = "";
			for(String c: labBank.getLabel().get(i).getLabChoise()) {
				choices = choices + c +",";
			}
			rowData[i][1] = choices;
			
		}	
	}
}
