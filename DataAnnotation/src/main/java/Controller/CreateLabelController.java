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
import Model.LogAnnotation;
import View.CreateLabelView;
import View.ShowLabelView;

@Component
public class CreateLabelController {
	private CommentBank cmtBank;
	private LabelBank labBank;
	
	private final int index;
	private int count = 0;
	private ArrayList<JEditorPane> jepList = new ArrayList<JEditorPane>();
	private ArrayList<JLabel> jtaList = new ArrayList<JLabel>();
	
	public CreateLabelController(final CommentBank cmtBank, final LabelBank labBank, final int index,final CreateLabelView view) {
		// TODO 自动生成的构造函数存根
		this.cmtBank = cmtBank;
		this.labBank = labBank;
		this.index = index;
		
		CreateLabelView.okButton.addActionListener(new ActionListener() {
			@LogAnnotation(className = "Controller.CreateLabelController" , content = "create Label")
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> choiceList = new ArrayList<String>();
				choiceList.add(CreateLabelView.label1EditorPane.getText());
				choiceList.add(CreateLabelView.label2EditorPane.getText());
				for(JEditorPane jep: jepList) {
					choiceList.add(jep.getText());
				}
				Label label = new Label(CreateLabelView.labelTypeEditorPane.getText(),choiceList);
				labBank.getLabel().add(label);
				ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
				showlabelview.setLocation(view.getLocation());
				showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showlabelview.setVisible(true);
				view.dispose();
			}
		});
		CreateLabelView.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
				showlabelview.setLocation(view.getLocation());
				showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showlabelview.setVisible(true);
				view.dispose();
			}
		});
		CreateLabelView.addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				jepList.add(new JEditorPane());
				jepList.get(count).setBounds(340, (170+40*count), 190, 22);	
				jepList.get(count).setFont(new Font("宋体", Font.PLAIN, 16));
				CreateLabelView.contentPane.add(jepList.get(count));
				
				jtaList.add(new JLabel());
				jtaList.get(count).setText("请输入自定义标签选项"+(count+3)+"：");
				jtaList.get(count).setBounds(150, (170+40*count), 200, 25);
				jtaList.get(count).setFont(new Font("Monospaced", Font.PLAIN, 17));
				jtaList.get(count).setBackground(UIManager.getColor("Button.background"));
				CreateLabelView.contentPane.add(jtaList.get(count));
				count ++;
				CreateLabelView.contentPane.repaint();	
			}
		});
	}

}
