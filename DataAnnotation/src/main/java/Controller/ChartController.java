package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;


import Model.Chart;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;

import Model.LogAspect;
import Model.Percent;
import View.ChartView;
import View.IndexView;


public class ChartController {
	private ArrayList<String> choiceList = new ArrayList<String>();
	private ArrayList<Percent> percent = new ArrayList<Percent>();
	private CommentBank cmtBank;
	private LabelBank labBank;
	private String[] labels= {" "};	
	private final int index;
	private int n;
	private int[] counts;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ChartController(final CommentBank cmtBank, final LabelBank labBank, final int index,final ChartView view) {		
		// TODO 自动生成的构造函数存根
		this.cmtBank = cmtBank;
		this.labBank = labBank;
		this.index = index;
		
		choiceList.add(" ");
		for(int i = 0; i < labBank.getLabel().size(); i++){
			Label label = labBank.getLabel().get(i);
			choiceList.add(label.getLabType());			
		}
		labels = (String[]) choiceList.toArray(new String[0]);
		
		//下拉框变化的响应
		ChartView.labelComboBox.setModel(new DefaultComboBoxModel(labels));
		ChartView.labelComboBox.addItemListener(new ItemListener() {
			@SuppressWarnings("unchecked")
			public void itemStateChanged(ItemEvent e){
				
				LogAspect.Log("Controller.ChartController","update chart");
				
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
					ChartView.contentPane.remove(ChartView.cp);
					ChartView.cp = new Chart(type+"标签统计饼图",percent).getChartPanel();
					ChartView.cp.updateUI();
					ChartView.cp.setBounds(200,25,500,400);
					//cp.repaint();
					System.out.println(ChartView.cp);
					ChartView.contentPane.add(ChartView.cp);
					ChartView.contentPane.repaint();			    	
				}
			}
		});	
		ChartView.cp = new Chart("测试",percent).getChartPanel();	
		ChartView.returnIndexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				IndexView indexView = new IndexView(cmtBank, labBank);
				indexView.setLocation(view.getLocation());
				indexView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				indexView.setVisible(true);
				view.dispose();	
			}
		});
	}

}
