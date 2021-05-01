package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;

import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Toolkit;
import java.awt.Button;
import java.awt.Color; 

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
	private ArrayList<JTextArea> jtaList = new ArrayList<JTextArea>();
	
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

		JTextArea createLabelType = new JTextArea();
		createLabelType.setBackground(UIManager.getColor("Button.background"));
		createLabelType.setText("\u8BF7\u8F93\u5165\u81EA\u5B9A\u4E49\u6807\u7B7E\u7C7B\u578B\uFF1A");
		createLabelType.setBounds(65, 55, 145, 25);
		contentPane.add(createLabelType);

		JTextArea createLabel1 = new JTextArea();
		createLabel1.setBackground(UIManager.getColor("Button.background"));
		createLabel1.setText("\u8BF7\u8F93\u5165\u81EA\u5B9A\u4E49\u6807\u7B7E\u9009\u98791\uFF1A");
		createLabel1.setBounds(65, 100, 150, 25);
		contentPane.add(createLabel1);

		JTextArea createLabel2 = new JTextArea();
		createLabel2.setBackground(UIManager.getColor("Button.background"));
		createLabel2.setText("\u8BF7\u8F93\u5165\u81EA\u5B9A\u4E49\u6807\u7B7E\u9009\u98792\uFF1A");
		createLabel2.setBounds(65, 140, 150, 25);
		contentPane.add(createLabel2);

		labelTypeEditorPane = new JEditorPane();
		labelTypeEditorPane.setBounds(255, 55, 110, 21);
		contentPane.add(labelTypeEditorPane);

		label1EditorPane = new JEditorPane();
		label1EditorPane.setBounds(255, 100, 110, 21);
		contentPane.add(label1EditorPane);

		label2EditorPane = new JEditorPane();
		label2EditorPane.setBounds(255, 140, 110, 21);
		contentPane.add(label2EditorPane);
		
		Button addBtn = new Button("+");
		addBtn.setForeground(Color.WHITE);
		addBtn.setBackground(Color.BLUE);
		addBtn.setBounds(839, 290, 121, 30);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				jepList.add(new JEditorPane());
				jepList.get(count).setBounds(255, (180+40*count), 110, 21);
				contentPane.add(jepList.get(count));
				
				jtaList.add(new JTextArea());
				jtaList.get(count).setText("请输入自定义标签选项"+(count+3)+"：");
				jtaList.get(count).setBounds(65, (180+40*count), 150, 25);
				contentPane.add(jtaList.get(count));
				count ++;
				
			}
		});
		contentPane.add(addBtn);
	}
}
