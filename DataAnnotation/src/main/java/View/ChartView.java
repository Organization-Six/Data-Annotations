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

import Model.Chart;
import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;
import Model.Percent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ChartView extends Frame {

	private String[] labels= {" "};	
	
	//private ArrayList<Comment> cmtList = new ArrayList<Comment>();
	//private ArrayList<Label> labelList = new ArrayList<Label>();
	private CommentBank cmtBank;
	private LabelBank labBank;
	
	private final int index;
	private int n;
	private int[] counts;
	
	private ArrayList<String> choiceList = new ArrayList<String>();
	private ArrayList<Percent> percent = new ArrayList<Percent>();
	
	private ChartPanel cp;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ChartView(final CommentBank cmtBank, final LabelBank labBank, final int index) {
		super();
		this.cmtBank = cmtBank;
		this.labBank = labBank;
		this.index = index;
		
		choiceList.add(" ");
		for(int i = 0; i < labBank.getLabel().size(); i++){
			Label label = labBank.getLabel().get(i);
			choiceList.add(label.getLabType());			
		}
		labels = (String[]) choiceList.toArray(new String[0]);
		
		JComboBox labelComboBox = new JComboBox();
		labelComboBox.setModel(new DefaultComboBoxModel(labels));
		labelComboBox.setBackground(Color.WHITE);
		labelComboBox.setFont(new Font("宋体", Font.PLAIN, 12));
		labelComboBox.setForeground(new Color(0, 0, 0));
		labelComboBox.setBounds(28, 88, 102, 21);
		//下拉框变化的响应
		labelComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					String type = (String) e.getItem();
					percent.clear();
					for(int i = 0; i < labBank.getLabel().size(); i++) {
						if(labBank.getLabel().get(i).getLabType().equals(type)) {
							choiceList = (ArrayList<String>) labBank.getLabel().get(i).getLabChoise().clone();
							break;
						}
					}
					System.out.println(choiceList);
					n = choiceList.size();
					counts = new int[n];
					for(int i = 0; i < cmtBank.getComment().size(); i++) {
						for(int j = 0; j < n; j++) {
							if(cmtBank.getComment().get(i).getLabelList().contains(choiceList.get(j))) {
								counts[j]++;
								//sum++;
							}
						}
					}
					for(int i = 0; i < n; i++) {
						percent.add(new Percent(choiceList.get(i), counts[i]));
					}
					
					System.out.println(percent.get(0).getLabel()+percent.get(0).getPercent());
					contentPane.remove(cp);
					cp = new Chart(type+"标签统计饼图",percent).getChartPanel();
					cp.updateUI();
					cp.setBounds(200,25,500,400);
					//cp.repaint();
					System.out.println(cp);
			    	contentPane.add(cp);
			    	contentPane.repaint();
			    	
				}
			}
		});
		contentPane.add(labelComboBox);
		
		cp = new Chart("测试",percent).getChartPanel();
		cp.setBounds(200,25,500,400);
		System.out.println(cp);
    	contentPane.add(cp);
		
		JButton returnIndexButton = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		returnIndexButton.setBounds(430, 550, 97, 25);
		returnIndexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				IndexView indexView = new IndexView(cmtBank, labBank);
				indexView.setLocation(ChartView.this.getLocation());
				indexView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				indexView.setVisible(true);
				dispose();	
			}
		});
		contentPane.add(returnIndexButton);
		
//		chartPanel = new JPanel();
//		chartPanel.setBounds(189, 25, 500, 400);
//		chartPanel.setLayout(null);
//		contentPane.add(chartPanel);
		
////仅做占位用
//		JLabel lblNewLabel = new JLabel("\u663E\u793A\u997C\u56FE");
//		lblNewLabel.setSize(172, 149);
//		lblNewLabel.setLocation(0, 0);
//		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
//		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.black));
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		chartPanel.add(lblNewLabel);
	}
}
