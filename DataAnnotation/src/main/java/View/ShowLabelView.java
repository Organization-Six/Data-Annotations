package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.PasteController;
import Controller.ShowLabelController;
import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ShowLabelView extends Frame {

	//private ArrayList<Comment> cmtList = new ArrayList<Comment>();
	//private ArrayList<Label> labelList = new ArrayList<Label>();
//	private CommentBank cmtBank;
//	private LabelBank labBank;
//	private final int index;
//	private String[] labels;
//	String[] columnNames = { "标签类型", "标签项" };
//	String[][] rowData;
//	public int row;
	
	private ShowLabelController controller;
	private JList list;
	DefaultListModel d = new DefaultListModel();	
	public static JTable labelTable;
	DefaultTableModel tableModel;
	public static JMenuItem jm1,jm2;
	public static JPopupMenu popupMenu;
	public static JButton createLabelButton,cancelButton,okButton;

	public ShowLabelView(final CommentBank cmtBank, final LabelBank labBank, final int index) {
		super();
		popupMenu = new JPopupMenu(); 
		jm1 = new JMenuItem("修改");
		jm2 = new JMenuItem("删除") ;
		createLabelButton = new JButton("+\u81EA\u5B9A\u4E49\u6807\u7B7E");
		cancelButton = new JButton("\u53D6\u6D88");
		okButton = new JButton("\u786E\u5B9A");
		controller = new ShowLabelController(cmtBank,labBank,index,this);
		
		okButton.setBounds(240, 550, 90, 25);
		contentPane.add(okButton);

		cancelButton.setBounds(600, 550, 90, 25);
		contentPane.add(cancelButton);
		
		popupMenu.add(jm1);
		popupMenu.add(jm2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 40, 700, 400);
	
		//labelTable = new JTable(d);
		labelTable.setFont(new Font("宋体", Font.PLAIN, 15));
		labelTable.setBounds(42, 100, 350, 100);	
		labelTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 16));
				
		scrollPane.setViewportView(labelTable);
		contentPane.add(scrollPane);

		createLabelButton.setBounds(420, 480, 110, 25);
		contentPane.add(createLabelButton);
		
	}
}
