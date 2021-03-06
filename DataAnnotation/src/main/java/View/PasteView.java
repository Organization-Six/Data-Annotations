package View;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import Controller.PasteController;

import javax.swing.ButtonGroup;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PasteView extends Frame {
	
//	private ArrayList<String> typeList = new ArrayList<String>();
//	private String[] types;
//	
//	private ArrayList<String> choiceList = new ArrayList<String>();
//	private String[] labels;
//	
//	private LabelBank labBank;
//	private CommentBank cmtBank;
	//private ArrayList<Label> labelList = new ArrayList<Label>();
	//private ArrayList<Comment> cmtList = new ArrayList<Comment>();
//	String item;
//	private final int index;
//	private Comment comment;
	private PasteController controller;
	public static JComboBox labelComboBox;
	public static JComboBox labelTypeComboBox;
	public static JPanel labelPane;
	public static ButtonGroup labelTypeButtonGroup;
	public static JButton okButton,cancelButton;
	public static JTextArea commentTextPane;
	public static JPanel labelTypePane;
	
	public PasteView(final CommentBank cmtBank, final LabelBank labBank, final int index) {
		super();
		okButton = new JButton("\u786E\u5B9A");
		cancelButton = new JButton("\u53D6\u6D88");
		labelTypeButtonGroup=new ButtonGroup();
		commentTextPane = new JTextArea();
		labelTypePane = new JPanel();
		labelPane = new JPanel();
		controller = new PasteController(cmtBank,labBank,index,this);
		
		
		okButton.setBounds(250, 550, 90, 25);
		contentPane.add(okButton);

		cancelButton.setBounds(650, 550, 90, 25);
		contentPane.add(cancelButton);
				
		JScrollPane js=new JScrollPane(commentTextPane);
		js.setBounds(30, 55,535, 450);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		commentTextPane.setFont(new Font("??????", Font.PLAIN, 20));
		commentTextPane.setOpaque(true); 
		commentTextPane.setBackground(Color.WHITE);
		commentTextPane.setBounds(30, 55, 520, 450);
		commentTextPane.setDisabledTextColor(Color.BLACK);
		commentTextPane.setLineWrap(true);
		commentTextPane.disable();
		
		contentPane.add(js);
		
		JLabel labelTypeLabel = new JLabel();
		labelTypeLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		labelTypeLabel.setBackground(UIManager.getColor("Button.background"));
		labelTypeLabel.setText("\u9009\u62E9\u6807\u7B7E\u7C7B\u578B");
		labelTypeLabel.setBounds(575, 65, 124, 27);
		contentPane.add(labelTypeLabel);

		JLabel labelLabel = new JLabel();
		labelLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		labelLabel.setBackground(UIManager.getColor("Button.background"));
		labelLabel.setText("\u9009\u62E9\u6807\u7B7E");
		labelLabel.setBounds(575, 350, 90, 25);
		contentPane.add(labelLabel);
		
		labelTypePane.setBounds(580, 100, 375, 240);
		labelTypePane.setLayout(null);
		getContentPane().add(labelTypePane);

		labelPane.setBounds(580, 380, 375, 124);
		labelPane.setLayout(null);
		getContentPane().add(labelPane);
		
//		for(int i = 0; i < labBank.getLabel().size(); i++) {
//			new ButtonGroup();
//		}
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
//
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
}
