package Controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import org.springframework.stereotype.Component;

import Model.CommentBank;
import Model.Label;
import Model.LabelBank;
import Model.LogAspect;
import View.ShowLabelView;
import View.UpdateLabelView;

@Component
public class UpdateLabelController {
	private CommentBank cmtBank;
	private LabelBank labBank;
	
	private final int index;
	private final int row;
	public UpdateLabelController(final CommentBank cmtBank, final LabelBank labBank, final int index, final int row,final UpdateLabelView view) {
		// TODO 自动生成的构造函数存根
		this.cmtBank = cmtBank;
		this.labBank = labBank;
		this.index = index;
		this.row = row;
		
		UpdateLabelView.okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogAspect.Log("Controller.UpdateLabelController" , "update label");
				String oldType = labBank.getLabel().get(row).getLabType();			    
			    ArrayList<String> oldLabels = new ArrayList<String>();
			    for(String choose:labBank.getLabel().get(row).getLabChoise()) {
			    	oldLabels.add(choose);
			    }
			    Label oldLab = new Label(oldType,oldLabels);
//			    System.out.println(oldLab.getLabChoise());
				labBank.getLabel().get(row).setLabType(UpdateLabelView.labelTypeEditorPane.getText());
				labBank.getLabel().get(row).getLabChoise().clear();
				for(JEditorPane jep:UpdateLabelView.ep) {
					labBank.getLabel().get(row).getLabChoise().add(jep.getText());
				}
				
				Label newLabel = labBank.getLabel().get(row);
			    for(int i = 0; i < cmtBank.getComment().size(); i++) {
			    	ArrayList<String> oldLabelList = cmtBank.getComment().get(i).getLabelList();
			    	for(String oldLabel : oldLabelList) {
			    		if(oldLabel.contains(oldType)) {
			    			int k = oldLab.getLabChoise().indexOf(oldLabel.split("-")[1]);
//			    			System.out.println(k);
//			    			System.out.println(oldLabel.split("-")[1]);
//			    			System.out.println(oldLab.getLabChoise());
			    			oldLabelList.set(oldLabelList.indexOf(oldLabel), newLabel.getLabType()+"-"+newLabel.getLabChoise().get(k));
			    			cmtBank.getComment().get(i).setLabelList(oldLabelList);
			    		}
			    	}
			    }			
				ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
				showlabelview.setLocation(view.getLocation());
				showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showlabelview.setVisible(true);
				view.dispose();
			}
		});
		UpdateLabelView.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
				showlabelview.setLocation(view.getLocation());
				showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showlabelview.setVisible(true);
				view.dispose();
			}
		});
		UpdateLabelView.labelTypeEditorPane.setText(labBank.getLabel().get(row).getLabType());
		
		int n = labBank.getLabel().get(row).getLabChoise().size();
		UpdateLabelView.ep = new JEditorPane[n];
		UpdateLabelView.ta = new JLabel[n];
		for(int i = 0; i < n; i++) {
			UpdateLabelView.ta[i] = new JLabel();
			UpdateLabelView.ta[i].setBackground(UIManager.getColor("Button.background"));
			UpdateLabelView.ta[i].setText("请输入修改后标签选项"+(i+1)+"：");
			UpdateLabelView.ta[i].setFont(new Font("Monospaced", Font.PLAIN, 17));
			UpdateLabelView.ta[i].setBounds(150, (90+i*40), 200, 25);
			UpdateLabelView.contentPane.add(UpdateLabelView.ta[i]);
			
			UpdateLabelView.ep[i] = new JEditorPane();
			UpdateLabelView.ep[i].setBounds(340, (90+i*40), 190, 22);
			UpdateLabelView.ep[i].setFont(new Font("宋体", Font.PLAIN, 16));
			UpdateLabelView.ep[i].setText(labBank.getLabel().get(row).getLabChoise().get(i));
			UpdateLabelView.contentPane.add(UpdateLabelView.ep[i]);
		}
		//System.out.println(hep[2]);
	}

}
