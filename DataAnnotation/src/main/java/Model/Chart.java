package Model;

import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Chart {
	ChartPanel frame1;
	String title;
	static ArrayList<Percent> percent;
	DefaultPieDataset data;
	JFreeChart chart;
	
	public Chart(String title, ArrayList<Percent> percent){
		this.title = title;
		this.percent = percent;
		
		  this.data = getDataSet(percent);
	      chart = ChartFactory.createPieChart3D(title,this.data,true,false,false);
	    //���ðٷֱ�
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0.00%");//���һ��DecimalFormat������Ҫ������С������
	      NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//���StandardPieSectionLabelGenerator����
	      pieplot.setLabelGenerator(sp1);//���ñ�ͼ��ʾ�ٷֱ�
	  
	  //û�����ݵ�ʱ����ʾ������
	      pieplot.setNoDataMessage("��������ʾ");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	  
	      pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ
	      pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ
	      frame1=new ChartPanel (chart,true);
	      chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������
	      PiePlot piePlot= (PiePlot) chart.getPlot();//��ȡͼ���������
	      piePlot.setLabelFont(new Font("����",Font.BOLD,10));//�������
	      chart.getLegend().setItemFont(new Font("����",Font.BOLD,10));
	}
	
    private static DefaultPieDataset getDataSet(ArrayList<Percent> percent) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(Percent per: percent) {
        	dataset.setValue(per.getLabel(), per.getPercent());
        }
//        dataset.setValue("ƻ��",100);
//        dataset.setValue("����",200);
//        dataset.setValue("����",300);
//        dataset.setValue("�㽶",400);
//        dataset.setValue("��֦",500);
        return dataset;
}
    public ChartPanel getChartPanel(){
    	return frame1;
    	
    }
    
//    public ChartPanel updateChart(String title, ArrayList<Percent> percent) {
//    	data = getDataSet(percent);
//    	chart = ChartFactory.createPieChart3D(title,data,true,false,false);
//    	frame1.setChart(chart);
//		return frame1;
//    	
//    }
}