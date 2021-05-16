package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;

import Controller.ChartController;
import Model.Chart;
import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;
import Model.Percent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ChartView extends Frame {

//	private String[] labels= {" "};	
	
	//private ArrayList<Comment> cmtList = new ArrayList<Comment>();
	//private ArrayList<Label> labelList = new ArrayList<Label>();
//	private CommentBank cmtBank;
//	private LabelBank labBank;
//	private final int index;
//	private int n;
//	private int[] counts;
	
//	private ArrayList<String> choiceList = new ArrayList<String>();
//	private ArrayList<Percent> percent = new ArrayList<Percent>();
	
	@SuppressWarnings("unused")
	private ChartController controller;
	public static ChartPanel cp;
	public static JComboBox<String> labelComboBox;
	public static JButton returnIndexButton;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ChartView(final CommentBank cmtBank, final LabelBank labBank, final int index) {
		super();
		labelComboBox = new JComboBox();	
		returnIndexButton = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		controller = new ChartController(cmtBank,labBank,index,this);
		
		labelComboBox.setBackground(Color.WHITE);
		labelComboBox.setFont(new Font("ËÎÌו", Font.PLAIN, 12));
		labelComboBox.setForeground(new Color(0, 0, 0));
		labelComboBox.setBounds(28, 88, 102, 21);
		contentPane.add(labelComboBox);
			
		cp.setBounds(200,25,500,400);
		System.out.println(cp);
    	contentPane.add(cp);
		
		returnIndexButton.setBounds(430, 550, 97, 25);
		contentPane.add(returnIndexButton);
		
//		chartPanel = new JPanel();
//		chartPanel.setBounds(189, 25, 500, 400);
//		chartPanel.setLayout(null);
//		contentPane.add(chartPanel);
	}
}
