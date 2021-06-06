package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.IndexController;
import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;
import Model.MyRenderer;
import Model.Spider;
import Model.Spider.Data;
import View.dialog.DownLoadDialog;
import View.dialog.ExportDialog;
import View.dialog.ImportDialog;
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
//	private CommentBank cmtBank;// = new CommentBank();	
//	private LabelBank labBank;// = new LabelBank();	
	//private ArrayList<Comment> cmtList = new ArrayList<Comment>();
	//private ArrayList<Label> labelList = new ArrayList<Label>();
//	private int index;	
	
	private JScrollPane scrollPane;
	public static DownLoadDialog dialog;
	public static JPopupMenu popupMenu;
	public static JMenuItem jm1,jm2;
	public static JComboBox labelComboBox;
	public static JList list;
	public static DefaultListModel d = new DefaultListModel();
	private IndexController controller;
	public static ImportDialog brsdialog;
	public static ExportDialog exdialog;
	public static ArrayList<Integer> conflictList = new ArrayList<Integer>();
	public static ArrayList<Integer> confirmList = new ArrayList<Integer>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IndexView(final CommentBank cmtBank, final LabelBank labBank) {
		super();

		popupMenu = new JPopupMenu(); 
		jm1 = new JMenuItem("标注");
		jm2 = new JMenuItem("删除") ;
		list = new JList(d);
		labelComboBox = new JComboBox();
		controller = new IndexController(cmtBank,labBank,this);
				
		JPanel panel = new JPanel();
		panel.setBounds(42, 100, 900, 500);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(900,500));

		popupMenu.add(jm1);
		popupMenu.add(jm2);

		
		list.setFont(new Font("宋体", Font.PLAIN, 24));
		list.setBounds(42, 100, 900, 136);	
		list.setFixedCellWidth(list.getBounds().width);
		list.setFixedCellHeight(40);
		//边框
		list.setCellRenderer(new MyRenderer(conflictList, confirmList));
		
//		list.setCellRenderer(new ListCellRenderer() {
//			public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3,
//					boolean arg4) {
//				// TODO 自动生成的方法存根
//				DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
//			    Border lineBorder = BorderFactory.createLineBorder(new Color(121,121,121),1);
//				JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(arg0, arg1, arg2, arg3, arg4);		    
//		        renderer.setBorder(lineBorder);		        
//		        		      
//		        return renderer;
//			}
//		});
		
		scrollPane.setViewportView(list);
		panel.add(scrollPane);
		contentPane.add(panel);	
				
		JLabel titleLabel = new JLabel("\u80A1\u7968\u8BC4\u8BBA");
		titleLabel.setBounds(413, 31, 126, 45);
		getContentPane().add(titleLabel);
		titleLabel.setFont(new Font("黑体", Font.BOLD, 27));
				
		labelComboBox.setBounds(813, 58, 126, 37);
		getContentPane().add(labelComboBox);
		labelComboBox.setBackground(Color.WHITE);
		labelComboBox.setFont(new Font("宋体", Font.PLAIN, 24));
		labelComboBox.setForeground(new Color(0, 0, 0));
		
	}	
}
