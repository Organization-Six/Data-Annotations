package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import Model.CommentBank;
import Model.Label;
import Model.LabelBank;

import javax.swing.UIManager;

import Controller.ChartController;
import Controller.CreateLabelController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font; 

public class CreateLabelView extends Frame {

	//private ArrayList<Comment> cmtList = new ArrayList<Comment>();
	//private ArrayList<Label> labelList = new ArrayList<Label>();
//	private CommentBank cmtBank;
//	private LabelBank labBank;
//	
//	private final int index;
//	private int count = 0;
	private CreateLabelController controller;
	public static JEditorPane labelTypeEditorPane;
	public static JEditorPane label1EditorPane;
	public static JEditorPane label2EditorPane;
	public static JButton okButton,addBtn,cancelButton;
//	private ArrayList<JEditorPane> jepList = new ArrayList<JEditorPane>();
//	private ArrayList<JLabel> jtaList = new ArrayList<JLabel>();
	
	public CreateLabelView(final CommentBank cmtBank, final LabelBank labBank, final int index) {
		super();
		
		okButton = new JButton("\u786E\u5B9A");
		cancelButton = new JButton("\u53D6\u6D88");
		addBtn = new JButton("+");
		labelTypeEditorPane = new JEditorPane();
		label1EditorPane = new JEditorPane();
		label2EditorPane = new JEditorPane();
		controller = new CreateLabelController(cmtBank,labBank,index,this);
			
		okButton.setBounds(240, 550, 90, 25);
		contentPane.add(okButton);
		
		cancelButton.setBounds(600, 550, 90, 25);
		contentPane.add(cancelButton);

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

		labelTypeEditorPane.setFont(new Font("宋体", Font.PLAIN, 16));
		labelTypeEditorPane.setBounds(340, 50, 190, 22);
		contentPane.add(labelTypeEditorPane);
	
		label1EditorPane.setFont(new Font("宋体", Font.PLAIN, 16));
		label1EditorPane.setBounds(340, 90, 190, 22);
		contentPane.add(label1EditorPane);

		label2EditorPane.setFont(new Font("宋体", Font.PLAIN, 16));
		label2EditorPane.setBounds(340, 130, 190, 22);
		contentPane.add(label2EditorPane);
		
		addBtn.setForeground(Color.WHITE);
		addBtn.setBackground(Color.BLUE);
		addBtn.setBounds(750, 50, 120, 35);
		contentPane.add(addBtn);
	}
}
