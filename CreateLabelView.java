package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import Model.CommentBank;
import Model.Label;
import Model.LabelBank;

import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;

public class CreateLabelView extends Frame {

	//private ArrayList<Comment> cmtList = new ArrayList<Comment>();
	//private ArrayList<Label> labelList = new ArrayList<Label>();
	private CommentBank cmtBank;
	private LabelBank labBank;
	
	private final int index;
	private int count = 0;
	
	JEditorPane labelTypeEditorPane;
	JEditorPane label1EditorPane;
	JEditorPane label2EditorPane;
	
	private ArrayList<JEditorPane> jepList = new ArrayList<JEditorPane>();
	private ArrayList<JLabel> jtaList = new ArrayList<JLabel>();
	
	public CreateLabelView(final CommentBank cmtBank, final LabelBank labBank, final int index) {
		super();
		this.cmtBank = cmtBank;
		this.labBank = labBank;
		this.index = index;

		JButton okButton = new JButton("\u786E\u5B9A");
		okButton.setBounds(240, 550, 90, 25);
		contentPane.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> choiceList = new ArrayList<String>();
				choiceList.add(label1EditorPane.getText());
				choiceList.add(label2EditorPane.getText());
				for(JEditorPane jep: jepList) {
					choiceList.add(jep.getText());
				}
				Label label = new Label(labelTypeEditorPane.getText(),choiceList);
				labBank.getLabel().add(label);
				ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
				showlabelview.setLocation(CreateLabelView.this.getLocation());
				showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showlabelview.setVisible(true);
				dispose();
			}
		});

		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setBounds(600, 550, 90, 25);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
				showlabelview.setLocation(CreateLabelView.this.getLocation());
				showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showlabelview.setVisible(true);
				dispose();
			}
		});

		JLabel createLabelType = new JLabel();
		createLabelType.setFont(new Font("Monospaced", Font.PLAIN, 17));
		createLabelType.setBackground(UIManager.getColor("Button.background"));
		createLabelType.setText("\u8BF7\u8F93\u5165\u81EA\u5B9A\u4E49\u6807\u7B7E\u7C7B\u578B\uFF1A");
		createLabelType.setBounds(150, 50, 200, 25);
		contentPane.add(createLabelType);

		JLabel createLabel1 = new JLabel();
		createLabel1.setFont(new Font("Monospaced", Font.PLAIN, 17));
		createLabel1.setBackground(UIManager.getColor("Button.background"));
		createLabel1.setText("\u8BF7\u8F93\u5165\u81EA\u5B9A\u4E49\u6807\u7B7E\u9009\u98791\uFF1A");
		createLabel1.setBounds(150, 90, 200, 25);
		contentPane.add(createLabel1);

		JLabel createLabel2 = new JLabel();
		createLabel2.setFont(new Font("Monospaced", Font.PLAIN, 17));
		createLabel2.setBackground(UIManager.getColor("Button.background"));
		createLabel2.setText("\u8BF7\u8F93\u5165\u81EA\u5B9A\u4E49\u6807\u7B7E\u9009\u98792\uFF1A");
		createLabel2.setBounds(150, 130, 200, 25);
		contentPane.add(createLabel2);

		labelTypeEditorPane = new JEditorPane();
		labelTypeEditorPane.setFont(new Font("宋体", Font.PLAIN, 16));
		labelTypeEditorPane.setBounds(340, 50, 190, 22);
		contentPane.add(labelTypeEditorPane);

		label1EditorPane = new JEditorPane();
		label1EditorPane.setFont(new Font("宋体", Font.PLAIN, 16));
		label1EditorPane.setBounds(340, 90, 190, 22);
		contentPane.add(label1EditorPane);

		label2EditorPane = new JEditorPane();
		label2EditorPane.setFont(new Font("宋体", Font.PLAIN, 16));
		label2EditorPane.setBounds(340, 130, 190, 22);
		contentPane.add(label2EditorPane);
		
		Button addBtn = new Button("+");
		addBtn.setForeground(Color.WHITE);
		addBtn.setBackground(Color.BLUE);
		addBtn.setBounds(750, 50, 120, 35);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				jepList.add(new JEditorPane());
				jepList.get(count).setBounds(340, (170+40*count), 190, 22);	
				jepList.get(count).setFont(new Font("宋体", Font.PLAIN, 16));
				contentPane.add(jepList.get(count));
				
				jtaList.add(new JLabel());
				jtaList.get(count).setText("请输入自定义标签选项"+(count+3)+"：");
				jtaList.get(count).setBounds(150, (170+40*count), 200, 25);
				jtaList.get(count).setFont(new Font("Monospaced", Font.PLAIN, 17));
				jtaList.get(count).setBackground(UIManager.getColor("Button.background"));
				contentPane.add(jtaList.get(count));
				count ++;
				contentPane.repaint();	
			}
		});
		contentPane.add(addBtn);
	}
}
