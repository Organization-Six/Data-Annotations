package Controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import org.springframework.stereotype.Component;

import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;
import Model.LogAnnotation;
import View.IndexView;
import View.PasteView;
import View.ShowLabelView;

@Component
public class PasteController {
	private ArrayList<String> typeList = new ArrayList<String>();
	private String[] types;
	
	private ArrayList<String> choiceList = new ArrayList<String>();
	private String[] labels;
	
	private LabelBank labBank;
	private CommentBank cmtBank;
	
	String item;
	String type;
	private final int index;
	private Comment comment;
	public PasteController(final CommentBank cmtBank, final LabelBank labBank, final int index,final PasteView view) {
		// TODO 自动生成的构造函数存根
		this.labBank = labBank;
		this.cmtBank = cmtBank;
		this.index = index;
		initData();
		
		PasteView.labelIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
				showlabelview.setLocation(view.getLocation());
				showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showlabelview.setVisible(true);
				view.dispose();
			}
		});		
		PasteView.okButton.addActionListener(new ActionListener() {
			@LogAnnotation(className = "Controller.PasteController" , content = "paste label to comment")
			public void actionPerformed(ActionEvent e) {
				if(comment.isCmtIsMark()) {	
					String k = null;
			    	ArrayList<String> oldLabelList = comment.getLabelList();
			    	for(String oldLabel : oldLabelList) {
			    		if(oldLabel.contains(type)) 
				    			k=oldLabel;			    							    			       			    
				    }			    	
		    		oldLabelList.remove(k);	
	    			comment.setLabelList(oldLabelList);	
					comment.getLabelList().add(type+"-"+item);
					System.out.println(comment.getLabelList());
				}
				else {
					comment.setCmtIsMark(true);
					comment.setLabelList(new ArrayList<String>(Arrays.asList(type+"-"+item)));
				}
				IndexView indexview = new IndexView(cmtBank, labBank);
				indexview.setLocation(view.getLocation());
				indexview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				indexview.setVisible(true);
				view.dispose();
			}
		});
		PasteView.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IndexView indexview = new IndexView(cmtBank, labBank);
				indexview.setLocation(view.getLocation());
				indexview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				indexview.setVisible(true);
				view.dispose();
			}
		});
		PasteView.commentTextPane.setText(comment.getCmtWriter()+" "+comment.getCmtTime()+" "+comment.getCmtText());
		for(int i = 0; i < types.length; i++) {
			JRadioButton labelType = new JRadioButton(types[i]);
			labelType.setBounds(5,10+20*i,100,20);
			labelType.setFont(new Font("瀹嬩綋", Font.PLAIN, 14));
			labelType.addActionListener(new LabelTypeListener());
			PasteView.labelTypePane.add(labelType);
			PasteView.labelTypeButtonGroup.add(labelType);
		}
	}
	private void initData() {
		comment = cmtBank.getComment().get(index);
		
		typeList.add(" ");
		choiceList.add(" ");
		for(int i = 0; i < labBank.getLabel().size(); i++) {
			Label label = labBank.getLabel().get(i);
			typeList.add(label.getLabType());
		}

		
		//lablist.addAll(label1.getLabChoise());
		//lablist.addAll(label2.getLabChoise());
		
		types = (String[]) typeList.toArray(new String[0]); 
		labels = (String[]) choiceList.toArray(new String[0]); 
	}
	
	private void labelRefresh() {
		PasteView.labelPane.removeAll();
		ButtonGroup labelButtonGroup=new ButtonGroup();		
		for(int i = 0; i < labels.length; i++) {
			JRadioButton labelRb=new JRadioButton(labels[i]);			
			labelRb.setBounds(5+i%3*125,i/3*20,120,20);
			labelRb.setFont(new Font("瀹嬩綋", Font.PLAIN, 14));
			labelRb.addActionListener(new LabelListener());		
			PasteView.labelPane.add(labelRb);
			labelButtonGroup.add(labelRb);
		}	
		PasteView.labelPane.repaint();
	}
	
	@Component
	class LabelTypeListener implements ActionListener{
		@LogAnnotation(className = "Controller.PasteController" , content = "update choiceList")
		public void actionPerformed(ActionEvent e){//if (e.getSource() ==button1)
			type=e.getActionCommand();
			for(int i = 0; i < labBank.getLabel().size(); i++) {
				if(labBank.getLabel().get(i).getLabType().equals(type)) {
					choiceList.clear();
					choiceList.add(" ");
					choiceList.addAll(labBank.getLabel().get(i).getLabChoise());
					labels = (String[]) choiceList.toArray(new String[0]); 
					labelRefresh();				
				}
			}
		}
	}
	
	class LabelListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
			item = e.getActionCommand();
//			System.out.println("閫変簡"+item);
		}		
	}
}
