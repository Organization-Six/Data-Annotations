package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;
import Model.Spider;
import View.dialog.DownLoadDialog;
import View.dialog.MessageDialog;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import java.awt.SystemColor;

public class IndexView extends Frame {
	
	private ArrayList<String> choiceList = new ArrayList<String>();
	DefaultListModel d = new DefaultListModel();
	private String[] labels;

	private JList list;
	private JScrollPane scrollPane;
	private JComboBox labelComboBox;
	
	private CommentBank cmtBank;// = new CommentBank();	
	private LabelBank labBank;// = new LabelBank();
	
	//private ArrayList<Comment> cmtList = new ArrayList<Comment>();
	//private ArrayList<Label> labelList = new ArrayList<Label>();
	public static DownLoadDialog dialog;
	
	private int index;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IndexView(final CommentBank cmtBank, final LabelBank labBank) {
		super();
		this.cmtBank = cmtBank;
		this.labBank = labBank;
		initData();
		
		importMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根	
				//String importStockID = JOptionPane.showInputDialog(IndexView.this,"请输入股票ID","导入评论",JOptionPane.PLAIN_MESSAGE);
				cmtBank.Load();
				labBank.Load();
				System.out.println(cmtBank.getComment().get(0).getCmtText());
				initImport();
				JOptionPane.showMessageDialog(IndexView.this, "导入成功");
			}
		});
		
		exportMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				//String exportStockID = JOptionPane.showInputDialog(IndexView.this,"请输入需要下载的股票代码","",JOptionPane.PLAIN_MESSAGE);
				//cmtBank.setComment(cmtList);
				cmtBank.Save();
				//labBank.setLabel(labelList);
				labBank.Save();
				JOptionPane.showMessageDialog(IndexView.this, "导出成功");
			}
		});
		
		downloadMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				String exportStockID = JOptionPane.showInputDialog(IndexView.this,"请输入需要下载的股票代码","",JOptionPane.PLAIN_MESSAGE);
				ArrayList<String> comments;
				comments = Spider.Load(exportStockID);
				
				initDown(comments);
			}
		});
		
		labelIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showLabelView = new ShowLabelView(cmtBank, labBank, -1);
				showLabelView.setLocation(IndexView.this.getLocation());
				showLabelView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showLabelView.setVisible(true);
				dispose();
			}
		});
		
		chartIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChartView chartView = new ChartView(cmtBank, labBank, -1);
				chartView.setLocation(IndexView.this.getLocation());
				chartView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				chartView.setVisible(true);
				dispose();
			}
		});
		
		
		final JPopupMenu popupMenu = new JPopupMenu(); 
		JMenuItem jm1 = new JMenuItem("标注");
		JMenuItem jm2 = new JMenuItem("删除") ;
		popupMenu.add(jm1);
		popupMenu.add(jm2);
	
		jm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根	
				PasteView pasteView = new PasteView(cmtBank, labBank, index);
				pasteView.setLocation(IndexView.this.getLocation());
				pasteView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pasteView.setVisible(true);
				dispose();
			}		
		});	
		jm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
//删除项响应		
				System.out.println("进来了");
				if(index >= 0) {
					cmtBank.getComment().remove(index);
					initData();
					System.out.println(cmtBank.getComment().size());
				}
			}		
		});	
		
		JPanel panel = new JPanel();
		panel.setBounds(42, 100, 900, 500);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(900,500));

		list = new JList(d);
		list.setFont(new Font("宋体", Font.PLAIN, 24));
		list.setBounds(42, 100, 900, 136);	
		list.setFixedCellWidth(list.getBounds().width);
		list.setFixedCellHeight(40);
		list.setCellRenderer(new ListCellRenderer() {
			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3,
					boolean arg4) {
				// TODO 自动生成的方法存根
				DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
			    Border lineBorder = BorderFactory.createLineBorder(new Color(121,121,121),1);
				JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(arg0, arg1, arg2, arg3, arg4);		    
		        renderer.setBorder(lineBorder);
		        return renderer;
			}
		});
		list.addMouseListener(new MouseAdapter() { 	    			  				  
		   @Override 
		   public void mouseClicked(MouseEvent e) { } 

		   @Override 
		   public void mousePressed(MouseEvent e) { 
			   list.setSelectedIndex(list.locationToIndex(e.getPoint())); //获取鼠标点击的项 
			   if (e.isPopupTrigger()&&list.getSelectedIndex()!=-1 && cmtBank.getComment().size() != 0) { 
				   //****comment = cmtBank.getComment().get(list.getSelectedIndex());
				   index = list.getSelectedIndex();
//获取点击的对象的值
//		            Object selected = list.getModel().getElementAt(list.getSelectedIndex());
		            popupMenu.show(e.getComponent(),e.getX(), e.getY()); 
	           }  		     
		   } 
		   @Override 
		   public void mouseReleased(MouseEvent e) { 
			   if (e.isPopupTrigger()&&list.getSelectedIndex()!=-1 &&cmtBank.getComment().size() != 0) {   
		            popupMenu.show(e.getComponent(),e.getX(), e.getY());
		            index = list.getSelectedIndex();
	           }
		   }  	  							   		    
		});
		scrollPane.setViewportView(list);
		panel.add(scrollPane);
		contentPane.add(panel);	
		
		
		JLabel titleLabel = new JLabel("\u80A1\u7968\u8BC4\u8BBA");
		titleLabel.setBounds(413, 31, 126, 45);
		getContentPane().add(titleLabel);
		titleLabel.setFont(new Font("黑体", Font.BOLD, 27));
		
		labelComboBox = new JComboBox();
		labelComboBox.setBounds(813, 58, 126, 37);
		getContentPane().add(labelComboBox);
		labelComboBox.setModel(new DefaultComboBoxModel(labels));
		labelComboBox.setBackground(Color.WHITE);
		labelComboBox.setFont(new Font("宋体", Font.PLAIN, 24));
		labelComboBox.setForeground(new Color(0, 0, 0));
		//下拉框变化的响应
				labelComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e){
						if(e.getStateChange() == ItemEvent.SELECTED){
							String item = (String) e.getItem();
							d.clear();
							if(e.getItem().equals("全部")) {
								for(int i = 0; i < cmtBank.getComment().size(); i++) {
									d.addElement(cmtBank.getComment().get(i).getCmtWriter() + " " + cmtBank.getComment().get(i).getCmtTime() + " "+ cmtBank.getComment().get(i).getCmtText());
									list = new JList(d);
								}
								if(d.getSize() == 0) {
									d.addElement("无数据");
								}
							}
							else if(e.getItem().equals("未分类")) {
								for(int i = 0; i < cmtBank.getComment().size(); i++) {
									if(!(cmtBank.getComment().get(i).isCmtIsMark())) {
										d.addElement(cmtBank.getComment().get(i).getCmtWriter() + " " + cmtBank.getComment().get(i).getCmtTime() + " "+ cmtBank.getComment().get(i).getCmtText());
										list = new JList(d);
									}
								}
								if(d.getSize() == 0) {
									d.addElement("无数据");
								}
								
							}
							else {
								for(int i = 0; i < cmtBank.getComment().size(); i++) {
									if(cmtBank.getComment().get(i).getLabelList().contains(item)) {
										//d.clear();
										d.addElement(cmtBank.getComment().get(i).getCmtWriter() + " " + cmtBank.getComment().get(i).getCmtTime() + " "+ cmtBank.getComment().get(i).getCmtText());
										list = new JList(d);
									}
									else {
										System.out.println("1");
									}
								}
								if(d.getSize() == 0) {
									d.addElement("无数据");
								}
							}
						}
					}
				});
	}
	
	
	private void initData() {
		//只有默认的模型有添加/删除方法
		d.clear();
		if(cmtBank.getComment().size() == 0) {
			d.addElement("无数据");
		}
		for(int i = 0; i < cmtBank.getComment().size(); i++){
			Comment comment = cmtBank.getComment().get(i);
			d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
		}
		
		choiceList.add("全部");
		choiceList.add("未分类");
		for(int i = 0; i < labBank.getLabel().size(); i++){
			Label label = labBank.getLabel().get(i);
			choiceList.addAll(label.getLabChoise());			
		}
		labels = (String[]) choiceList.toArray(new String[0]);
		//System.out.println(cmtBank.getComment().get(0).getCmtText());
	}
	
	private void initDown(ArrayList<String> comments) {
		d.clear();
		//cmtList.clear();
		int n = comments.size();
		dialog = new DownLoadDialog(null,"下载窗口",comments);
		dialog.setModal(true);
		dialog.setVisible(true);
		
//		for(String comment: comments) {
//			//cmtBank.getComment().add(comment);
//			//d.addElement(comment.getCmtWriter()+ " - "+comment.getCmtTime()+" - "+comment.getCmtText());
//			d.addElement(comment);
//		}
//		System.out.println(d.getSize());
		list = new JList(d);


	}
	private void initImport() {
		//cmtBank = new CommentBank();
		//cmtBank.Load();
		d.clear();
		for(int i = 0; i < cmtBank.getComment().size(); i++){
			Comment comment = cmtBank.getComment().get(i);
			d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
		}
		
		//labBank = new LabelBank();
		//labBank.Load();
		for(int i = 0; i < labBank.getLabel().size(); i++){
			Label label = labBank.getLabel().get(i);
			choiceList.addAll(label.getLabChoise());			
		}
		labels = (String[]) choiceList.toArray(new String[0]);
		labelComboBox.setModel(new DefaultComboBoxModel(labels));

	}
	
}
