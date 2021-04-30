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
	private CommentBank cmtBank;
	private LabelBank labBank;
	private final int index;
	
	private JList list;
	DefaultListModel d = new DefaultListModel();
	private String[] labels;
	JTable labelTable;
	DefaultTableModel tableModel;
	
	String[] columnNames = { "标签类型", "标签项" };
	String[][] rowData;
	public int row;

	public ShowLabelView(final CommentBank cmtBank, final LabelBank labBank, final int index) {
		super();
		this.cmtBank = cmtBank;
		this.labBank = labBank;
		this.index = index;


		chartIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index == -1) {
					ChartView chartview = new ChartView(cmtBank, labBank, -1);
					chartview.setLocation(ShowLabelView.this.getLocation());
					chartview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					chartview.setVisible(true);
					dispose();
				}
			}
		});

		JButton okButton = new JButton("\u786E\u5B9A");
		okButton.setBounds(240, 550, 90, 25);
		contentPane.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index == -1) {
					IndexView indexView = new IndexView(cmtBank, labBank);
					indexView.setLocation(ShowLabelView.this.getLocation());
					indexView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					indexView.setVisible(true);
					dispose();
				}
				else {
					PasteView pasteview = new PasteView(cmtBank, labBank, index);
					pasteview.setLocation(ShowLabelView.this.getLocation());
					pasteview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					pasteview.setVisible(true);
					dispose();
				}
			}
		});

		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setBounds(600, 550, 90, 25);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(index == -1) {
					IndexView indexView = new IndexView(cmtBank, labBank);
					indexView.setLocation(ShowLabelView.this.getLocation());
					indexView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					indexView.setVisible(true);
					dispose();
				}
				else {
					PasteView pasteview = new PasteView(cmtBank, labBank, index);
					pasteview.setLocation(ShowLabelView.this.getLocation());
					pasteview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					pasteview.setVisible(true);
					dispose();
				}
			}
		});
		
		final JPopupMenu popupMenu = new JPopupMenu(); 
		JMenuItem jm1 = new JMenuItem("修改");
		JMenuItem jm2 = new JMenuItem("删除") ;
		popupMenu.add(jm1);
		popupMenu.add(jm2);
		
		jm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根	
				UpdateLabelView updateLabelView = new UpdateLabelView(cmtBank, labBank, index, row);
				updateLabelView.setLocation(ShowLabelView.this.getLocation());
				updateLabelView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				updateLabelView.setVisible(true);
				dispose();
			}		
		});	
		jm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
//删除项响应		
				if(row >= 0) {
					System.out.println(labBank.getLabel().size()+"   111   " +row);
					labBank.getLabel().remove(row);
					//initData();
					//ShowLabelView.this.revalidate();
					ShowLabelView showlabelview = new ShowLabelView(cmtBank, labBank, index);
					showlabelview.setLocation(ShowLabelView.this.getLocation());
					showlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					showlabelview.setVisible(true);
					dispose();
					
					System.out.println(labBank.getLabel().size()+"222" + row);
				}
			}		
		});	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 40, 700, 400);

		initData();
		
		labelTable = new JTable(rowData, columnNames);
		
		//labelTable = new JTable(d);
		labelTable.setFont(new Font("宋体", Font.PLAIN, 12));
		labelTable.setBounds(42, 100, 350, 100);	

		labelTable.addMouseListener(new MouseAdapter(){
           @Override 
 		   public void mousePressed(MouseEvent e) { 
        	   if(e.isPopupTrigger()) {
            		
        	   }
 		   } 
 		   @Override 
 		   public void mouseReleased(MouseEvent e) {
 			  if(e.isPopupTrigger()) {
 				 row = labelTable.getSelectedRow();
 				 System.out.println(row);
 				 if(row >= 0) {
 					 popupMenu.show(e.getComponent(),e.getX(), e.getY()); 
 				 }
 			  }
 		   }  	  				

        }); 

		
		scrollPane.setViewportView(labelTable);
		contentPane.add(scrollPane);

		JButton createLabelButton = new JButton("+\u81EA\u5B9A\u4E49\u6807\u7B7E");
		createLabelButton.setBounds(420, 480, 110, 25);
		contentPane.add(createLabelButton);
		createLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateLabelView createlabelview = new CreateLabelView(cmtBank, labBank, index);
				createlabelview.setLocation(ShowLabelView.this.getLocation());
				createlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				createlabelview.setVisible(true);
				dispose();
			}
		});
	}
	
	private void initData() {
		int n = labBank.getLabel().size();
		rowData = new String[n][2];
		for(int i = 0; i < n; i++) {
			rowData[i][0] = labBank.getLabel().get(i).getLabType();
			String choices = "";
			for(String c: labBank.getLabel().get(i).getLabChoise()) {
				choices = choices + c +",";
			}
			rowData[i][1] = choices;
			
		}	
	}
}
