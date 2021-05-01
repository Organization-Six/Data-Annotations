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
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JRadioButton;

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
	
	JPanel labelPane;
	JRadioButton labelRb1,labelRb2,labelRb3;
	
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
		okButton.setBounds(250, 550, 90, 25);
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
		cancelButton.setBounds(650, 550, 90, 25);
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
		commentTextPane.setFont(new Font("宋体", Font.PLAIN, 20));
		commentTextPane.setText(comment.getCmtWriter()+" "+comment.getCmtTime()+" "+comment.getCmtText());
		commentTextPane.setBounds(30, 55, 520, 450);
		contentPane.add(commentTextPane);

		JLabel labelTypeLabel = new JLabel();
		labelTypeLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		labelTypeLabel.setBackground(UIManager.getColor("Button.background"));
		labelTypeLabel.setText("\u9009\u62E9\u6807\u7B7E\u7C7B\u578B");
		labelTypeLabel.setBounds(565, 65, 124, 27);
		contentPane.add(labelTypeLabel);

		JLabel labelLabel = new JLabel();
		labelLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		labelLabel.setBackground(UIManager.getColor("Button.background"));
		labelLabel.setText("\u9009\u62E9\u6807\u7B7E");
		labelLabel.setBounds(565, 400, 90, 25);
		contentPane.add(labelLabel);
		
		JPanel labelTypePane = new JPanel();
		labelTypePane.setBounds(570, 100, 375, 280);
		labelTypePane.setLayout(null);
		getContentPane().add(labelTypePane);
		
		ButtonGroup labelTypeButtonGroup=new ButtonGroup();
		for(int i = 0; i < types.length; i++) {
			JRadioButton labelType = new JRadioButton(types[i]);
			labelType.setBounds(5,10+20*i,100,20);
			labelType.setFont(new Font("宋体", Font.PLAIN, 14));
			labelType.addActionListener(new LabelTypeListener());
			labelTypePane.add(labelType);
			labelTypeButtonGroup.add(labelType);
		}
		
		labelPane = new JPanel();
		labelPane.setBounds(570, 440, 375, 37);
		labelPane.setLayout(null);
		getContentPane().add(labelPane);
		
		ButtonGroup labelButtonGroup=new ButtonGroup();		
		labelRb1 = new JRadioButton();
		labelRb1.setBounds(5,0,120,37);
		labelRb1.setFont(new Font("宋体", Font.PLAIN, 14));
		labelRb1.addActionListener(new LabelTypeListener());
		labelPane.add(labelRb1);
		labelButtonGroup.add(labelRb1);
		
		labelRb2 = new JRadioButton();
		labelRb2.setBounds(130,0,120,37);
		labelRb2.setFont(new Font("宋体", Font.PLAIN, 14));
		labelRb2.addActionListener(new LabelTypeListener());
		labelPane.add(labelRb2);
		labelButtonGroup.add(labelRb2);
		
		labelRb3 = new JRadioButton();
		labelRb3.setBounds(255,0,120,37);
		labelRb3.setFont(new Font("宋体", Font.PLAIN, 14));
		labelRb3.addActionListener(new LabelTypeListener());
		labelPane.add(labelRb3);
		labelButtonGroup.add(labelRb3);
		
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
		labelRb1.setText(labels[0]);
		labelRb2.setText(labels[1]);
		labelRb3.setText(labels[2]);
		labelPane.repaint();		
	}
	
	class LabelTypeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){//if (e.getSource() ==button1)
			String type=e.getActionCommand();
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
}
