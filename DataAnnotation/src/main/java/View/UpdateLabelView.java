package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;

import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateLabelView extends Frame {

	//private ArrayList<Comment> cmtList = new ArrayList<Comment>();
	//private ArrayList<Label> labelList = new ArrayList<Label>();
	private CommentBank cmtBank;
	private LabelBank labBank;
	
	private final int index;
	private final int row;
	
	JEditorPane[] ep;
	JLabel[] ta;
	JEditorPane labelTypeEditorPane;

	public UpdateLabelView(final CommentBank cmtBank, final LabelBank labBank, final int index, final int row) {
		super();
		this.cmtBank = cmtBank;
		this.labBank = labBank;
		this.index = index;
		this.row = row;
		

		JButton okButton = new JButton("\u786E\u5B9A");
		okButton.setBounds(240, 550, 90, 25);
		contentPane.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labBank.getLabel().get(row).setLabType(labelTypeEditorPane.getText());
				labBank.getLabel().get(row).getLabChoise().clear();
				for(JEditorPane jep:ep) {
					labBank.getLabel().get(row).getLabChoise().add(jep.getText());
				}
				
				ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
				showlabelview.setLocation(UpdateLabelView.this.getLocation());
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
				showlabelview.setLocation(UpdateLabelView.this.getLocation());
				showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showlabelview.setVisible(true);
				dispose();
			}
		});

		JLabel updateLabelType = new JLabel();
		updateLabelType.setBackground(UIManager.getColor("Button.background"));
		updateLabelType.setText("\u8BF7\u8F93\u5165\u4FEE\u6539\u540E\u6807\u7B7E\u7C7B\u578B\uFF1A");
		updateLabelType.setFont(new Font("Monospaced", Font.PLAIN, 17));
		updateLabelType.setBounds(150, 50, 200, 25);
		contentPane.add(updateLabelType);


		labelTypeEditorPane = new JEditorPane();
		labelTypeEditorPane.setFont(new Font("宋体", Font.PLAIN, 16));
		labelTypeEditorPane.setBounds(340, 50, 190, 22);
		labelTypeEditorPane.setText(labBank.getLabel().get(row).getLabType());
		contentPane.add(labelTypeEditorPane);
		
		
		int n = labBank.getLabel().get(row).getLabChoise().size();
		ep = new JEditorPane[n];
		ta = new JLabel[n];
		for(int i = 0; i < n; i++) {
			ta[i] = new JLabel();
			ta[i].setBackground(UIManager.getColor("Button.background"));
			ta[i].setText("请输入修改后标签选项"+(i+1)+"：");
			ta[i].setFont(new Font("Monospaced", Font.PLAIN, 17));
			ta[i].setBounds(150, (90+i*40), 200, 25);
			contentPane.add(ta[i]);
			
			ep[i] = new JEditorPane();
			ep[i].setBounds(340, (90+i*40), 190, 22);
			ep[i].setFont(new Font("宋体", Font.PLAIN, 16));
			ep[i].setText(labBank.getLabel().get(row).getLabChoise().get(i));
			contentPane.add(ep[i]);
		}
		//System.out.println(hep[2]);

	}

}