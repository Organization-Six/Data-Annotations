package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class PasteView extends Frame {
	
	private ArrayList<String> typeList = new ArrayList<String>();
	private String[] types;
	
	private ArrayList<String> choiceList = new ArrayList<String>();
	private String[] labels;
	
	private LabelBank labBank;
	private CommentBank cmtBank;
	//private ArrayList<Label> labelList = new ArrayList<Label>();
	//private ArrayList<Comment> cmtList = new ArrayList<Comment>();
	String item;
	JComboBox labelComboBox;
	JComboBox labelTypeComboBox;
	
	private final int index;
	private Comment comment;
	
	public PasteView(final CommentBank cmtBank, final LabelBank labBank, final int index) {
		super();
		this.labBank = labBank;
		this.cmtBank = cmtBank;
		this.index = index;
		
		initData();
		
		labelIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
				showlabelview.setLocation(PasteView.this.getLocation());
				showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showlabelview.setVisible(true);
				dispose();
			}
		});


		JButton okButton = new JButton("\u786E\u5B9A");
		okButton.setBounds(70, 195, 90, 25);
		contentPane.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comment.isCmtIsMark()) {
					comment.getLabelList().add(item);
				}
				else {
					comment.setCmtIsMark(true);
					comment.setLabelList(new ArrayList<String>(Arrays.asList(item)));
				}
				IndexView indexview = new IndexView(cmtBank, labBank);
				indexview.setLocation(PasteView.this.getLocation());
				indexview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				indexview.setVisible(true);
				dispose();
			}
		});

		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setBounds(270, 195, 90, 25);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IndexView indexview = new IndexView(cmtBank, labBank);
				indexview.setLocation(PasteView.this.getLocation());
				indexview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				indexview.setVisible(true);
				dispose();
			}
		});

		JTextPane commentTextPane = new JTextPane();
		commentTextPane.setText(comment.getCmtWriter()+" "+comment.getCmtTime()+" "+comment.getCmtText());
		commentTextPane.setBounds(20, 40, 200, 130);
		contentPane.add(commentTextPane);

		JTextArea labelTypeTextArea = new JTextArea();
		labelTypeTextArea.setBackground(UIManager.getColor("Button.background"));
		labelTypeTextArea.setText("\u9009\u62E9\u6807\u7B7E\u7C7B\u578B");
		labelTypeTextArea.setBounds(230, 65, 84, 25);
		contentPane.add(labelTypeTextArea);

		JTextArea labelTextArea = new JTextArea();
		labelTextArea.setBackground(UIManager.getColor("Button.background"));
		labelTextArea.setText("\u9009\u62E9\u6807\u7B7E");
		labelTextArea.setBounds(245, 125, 56, 25);
		contentPane.add(labelTextArea);

//		for(int i = 0; i < labBank.getLabel().size(); i++) {
//			new ButtonGroup();
//		}
//		JRadioButton 
//		labelTypeComboBox = new JComboBox();
//		labelTypeComboBox.setModel(new DefaultComboBoxModel(types));
//		labelTypeComboBox.setBounds(330, 65, 90, 25);
//		labelTypeComboBox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e){
//				if(e.getStateChange() == ItemEvent.SELECTED){
//					String type = (String) e.getItem();
//					for(int i = 0; i < labBank.getLabel().size(); i++) {
//						if(labBank.getLabel().get(i).getLabType().equals(type)) {
//							choiceList.clear();
//							choiceList.add(" ");
//							choiceList.addAll(labBank.getLabel().get(i).getLabChoise());
//							labels = (String[]) choiceList.toArray(new String[0]);
//							labelComboBox.setModel(new DefaultComboBoxModel(labels));
//						}
//					}
//				}
//			}
//		});
//		contentPane.add(labelTypeComboBox);

//		labelComboBox = new JComboBox();
//		labelComboBox.setModel(new DefaultComboBoxModel(labels));
//		labelComboBox.setBounds(330, 125, 90, 25);
//		labelComboBox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e){
//				if(e.getStateChange() == ItemEvent.SELECTED){
//					item = (String) e.getItem();
//				}
//			}
//		});
//		contentPane.add(labelComboBox);
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
}
