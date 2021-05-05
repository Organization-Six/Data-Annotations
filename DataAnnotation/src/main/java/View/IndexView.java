package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;
import Model.Spider;
import Model.Spider.Data;
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
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
				// TODO �Զ����ɵķ������	

//				JFileChooser loadFileChooser=new JFileChooser();
//				String loadFilePath;
//				loadFileChooser.setDialogTitle("��ѡ������ļ�");
//				loadFileChooser.setCurrentDirectory(new File("./"));
//				FileNameExtensionFilter filter = new FileNameExtensionFilter("�ı��ļ�(*.txt)", "txt");
//				loadFileChooser.setFileFilter(filter);
//				loadFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		        int option = loadFileChooser.showOpenDialog(null);		 // ��ʾ�ļ��򿪶Ի���
//		        if (option == JFileChooser.APPROVE_OPTION) {		// ȷ���û����´򿪰�ť������ȡ����ť
//		        	loadFilePath = loadFileChooser.getSelectedFile().getAbsolutePath();
//			        System.out.println(loadFilePath);
//		        }
		        
		        cmtBank.getComment().clear();
				labBank.getLabel().clear();
				cmtBank.Load();
				labBank.Load();
				System.out.println(cmtBank.getComment().get(0).getCmtText());
				initImport();
				JOptionPane.showMessageDialog(IndexView.this, "����ɹ�");
			}
		});
		
		exportMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
//				JFileChooser saveFileChooser = new JFileChooser();
//				saveFileChooser.setDialogTitle("��ѡ�񱣴�·��");
//				saveFileChooser.setCurrentDirectory(new File("./"));
//			    int retval = saveFileChooser.showSaveDialog(null);
//			    if (retval == JFileChooser.APPROVE_OPTION) {
//			    	File file = saveFileChooser.getSelectedFile();
//			    	if (file == null) {
//			    		return;
//			    	}
//			    	if (!file.getName().toLowerCase().endsWith(".txt")) {
//			    		file = new File(file.getParentFile(), file.getName() + ".txt");
//			    	}
//			    	System.out.println(file.getAbsolutePath());
//			    }
			    
		    	cmtBank.Save();
				labBank.Save();
				JOptionPane.showMessageDialog(IndexView.this, "�����ɹ�");
				cmtBank.getComment().clear();
				labBank.getLabel().clear();
				d.clear();
				initData();
				labelComboBox.setModel(new DefaultComboBoxModel(labels));

			}
		});
		
		downloadMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				String exportStockID = null;
				exportStockID = JOptionPane.showInputDialog(IndexView.this,"��������Ҫ���صĹ�Ʊ����","�����Ʊ����",JOptionPane.PLAIN_MESSAGE);
				if(!(exportStockID.isEmpty())) {
					ArrayList<Data> comments;
					comments = Spider.Load(exportStockID);
					
					initDown(cmtBank.getComment(), comments);
				}				
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
		JMenuItem jm1 = new JMenuItem("��ע");
		JMenuItem jm2 = new JMenuItem("ɾ��") ;
		popupMenu.add(jm1);
		popupMenu.add(jm2);
	
		jm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������	
				PasteView pasteView = new PasteView(cmtBank, labBank, index);
				pasteView.setLocation(IndexView.this.getLocation());
				pasteView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pasteView.setVisible(true);
				dispose();
			}		
		});	
		jm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
//ɾ������Ӧ		
				System.out.println("������");
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
		list.setFont(new Font("����", Font.PLAIN, 24));
		list.setBounds(42, 100, 900, 136);	
		list.setFixedCellWidth(list.getBounds().width);
		list.setFixedCellHeight(40);
		list.setCellRenderer(new ListCellRenderer() {
			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3,
					boolean arg4) {
				// TODO �Զ����ɵķ������
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
			   list.setSelectedIndex(list.locationToIndex(e.getPoint())); //��ȡ��������� 
			   if (e.isPopupTrigger()&&list.getSelectedIndex()!=-1 && cmtBank.getComment().size() != 0) { 
				   //****comment = cmtBank.getComment().get(list.getSelectedIndex());
				   index = list.getSelectedIndex();
//��ȡ����Ķ����ֵ
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
		titleLabel.setFont(new Font("����", Font.BOLD, 27));
		
		labelComboBox = new JComboBox();
		labelComboBox.setBounds(813, 58, 126, 37);
		getContentPane().add(labelComboBox);
		labelComboBox.setModel(new DefaultComboBoxModel(labels));
		labelComboBox.setBackground(Color.WHITE);
		labelComboBox.setFont(new Font("����", Font.PLAIN, 24));
		labelComboBox.setForeground(new Color(0, 0, 0));
		//������仯����Ӧ
				labelComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e){
						if(e.getStateChange() == ItemEvent.SELECTED){
							String item = (String) e.getItem();
							d.clear();
							if(e.getItem().equals("ȫ��")) {
								for(int i = 0; i < cmtBank.getComment().size(); i++) {
									d.addElement(cmtBank.getComment().get(i).getCmtWriter() + " " + cmtBank.getComment().get(i).getCmtTime() + " "+ cmtBank.getComment().get(i).getCmtText());
									list = new JList(d);
								}
								if(d.getSize() == 0) {
									d.addElement("������");
								}
							}
							else if(e.getItem().equals("δ����")) {
								for(int i = 0; i < cmtBank.getComment().size(); i++) {
									if(!(cmtBank.getComment().get(i).isCmtIsMark())) {
										d.addElement(cmtBank.getComment().get(i).getCmtWriter() + " " + cmtBank.getComment().get(i).getCmtTime() + " "+ cmtBank.getComment().get(i).getCmtText());
										list = new JList(d);
									}
								}
								if(d.getSize() == 0) {
									d.addElement("������");
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
									d.addElement("������");
								}
							}
						}
					}
				});
	}
	
	
	private void initData() {
		//ֻ��Ĭ�ϵ�ģ�������/ɾ������
		choiceList.clear();
		d.clear();
		if(cmtBank.getComment().size() == 0) {
			d.addElement("������");
		}
		for(int i = 0; i < cmtBank.getComment().size(); i++){
			Comment comment = cmtBank.getComment().get(i);
			d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
		}
		
		choiceList.add("ȫ��");
		choiceList.add("δ����");
		for(int i = 0; i < labBank.getLabel().size(); i++){
			Label label = labBank.getLabel().get(i);
			choiceList.addAll(label.getLabChoise());			
		}
		labels = (String[]) choiceList.toArray(new String[0]);
		//System.out.println(cmtBank.getComment().get(0).getCmtText());
	}
	
	private void initDown(ArrayList<Comment> cmtList, ArrayList<Data> comments) {
		d.clear();
		cmtBank.getComment().clear();
		labBank.getLabel().clear();

		dialog = new DownLoadDialog(null,"���ش���", cmtList, comments);
		dialog.setModal(true);
		dialog.setVisible(true);
		
//		for(String comment: cmtBank) {
//			//cmtBank.getComment().add(comment);
//			//d.addElement(comment.getCmtWriter()+ " - "+comment.getCmtTime()+" - "+comment.getCmtText());
//			d.addElement(comment);
//		}
//		System.out.println(d.getSize());
		initData();
		labelComboBox.setModel(new DefaultComboBoxModel(labels));
		list = new JList(d);


	}
	private void initImport() {
		//cmtBank = new CommentBank();
		//cmtBank.Load();
		initData();
		labelComboBox.setModel(new DefaultComboBoxModel(labels));
//		d.clear();
//		for(int i = 0; i < cmtBank.getComment().size(); i++){
//			Comment comment = cmtBank.getComment().get(i);
//			d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
//		}
//		
//		//labBank = new LabelBank();
//		//labBank.Load();
//		for(int i = 0; i < labBank.getLabel().size(); i++){
//			Label label = labBank.getLabel().get(i);
//			choiceList.addAll(label.getLabChoise());			
//		}
//		labels = (String[]) choiceList.toArray(new String[0]);
//		labelComboBox.setModel(new DefaultComboBoxModel(labels));

	}
	
}
