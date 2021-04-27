package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Component;

import javax.swing.UIManager;
import javax.swing.JScrollBar;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import java.awt.SystemColor;

public class IndexView extends JFrame {

	private JPanel contentPane;
	
	private String[] labels= {"全部","未分类","好评","差评","广告","非广告"};
	private String[] texts = {"内容1","内容2","内容3","内容4"};
	
	private int pageSum;
	private int pageNo=1;
	private JTextField pageNoInput;
	private JList list;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IndexView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShowLabelView.class.getResource("/res/app.png")));
		setTitle("\u6570\u636E\u6807\u6CE8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("\u6587\u4EF6");
		menuBar.add(fileMenu);

		JMenuItem importMenuItem = new JMenuItem("\u5BFC\u5165\u6587\u4EF6");
		importMenuItem.setIcon(null);
		importMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根	
				String importStockID = JOptionPane.showInputDialog(IndexView.this,"请输入股票ID","导入评论",JOptionPane.PLAIN_MESSAGE);
			}
		});
		fileMenu.add(importMenuItem);

		JMenuItem exportMenuItem = new JMenuItem("\u5BFC\u51FA\u6587\u4EF6");
		exportMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				String exportStockID = JOptionPane.showInputDialog(IndexView.this,"请输入需要下载的股票代码","",JOptionPane.PLAIN_MESSAGE);
			}
		});
		fileMenu.add(exportMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("\u9000\u51FA");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				dispose();
			}
		});
		fileMenu.add(exitMenuItem);

		JMenu editMenu = new JMenu("\u7F16\u8F91");
		menuBar.add(editMenu);

		JMenuItem copyMenuItem = new JMenuItem("\u590D\u5236");
		editMenu.add(copyMenuItem);

		JMenuItem pasteMenuItem = new JMenuItem("\u7C98\u8D34");
		editMenu.add(pasteMenuItem);

		JMenu viewMenu = new JMenu("\u89C6\u56FE");
		menuBar.add(viewMenu);

		JMenuItem toolMenuItem = new JMenuItem("\u5DE5\u5177\u680F");
		viewMenu.add(toolMenuItem);

		JMenu helpMenu = new JMenu("\u5E2E\u52A9");
		menuBar.add(helpMenu);

		JMenuItem aboutMenuItem = new JMenuItem("\u5173\u4E8E");
		helpMenu.add(aboutMenuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 130, 21);
		contentPane.add(toolBar);

		JButton fileIcon = new JButton(new ImageIcon(PasteView.class.getResource("/res/file.png")));
		toolBar.add(fileIcon);

		JButton folderIcon = new JButton(new ImageIcon(PasteView.class.getResource("/res/folder.png")));
		toolBar.add(folderIcon);

		JButton labelIcon = new JButton(new ImageIcon(PasteView.class.getResource("/res/label.png")));
		toolBar.add(labelIcon);
		labelIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showLabelView = new ShowLabelView();
				showLabelView.setLocation(IndexView.this.getLocation());
				showLabelView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showLabelView.setVisible(true);
				dispose();
			}
		});
		
		JButton chartIcon = new JButton(new ImageIcon(PasteView.class.getResource("/res/chart.png")));
		toolBar.add(chartIcon);
		chartIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChartView chartView = new ChartView();
				chartView.setLocation(IndexView.this.getLocation());
				chartView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				chartView.setVisible(true);
				dispose();
			}
		});
		
		JLabel titleLabel = new JLabel("\u80A1\u7968\u8BC4\u8BBA");
		titleLabel.setFont(new Font("黑体", Font.BOLD, 17));
		titleLabel.setBounds(166, 10, 89, 21);
		contentPane.add(titleLabel);
		
		JComboBox labelComboBox = new JComboBox();
		labelComboBox.setModel(new DefaultComboBoxModel(labels));
		labelComboBox.setBackground(Color.WHITE);
		labelComboBox.setFont(new Font("宋体", Font.PLAIN, 12));
		labelComboBox.setForeground(new Color(0, 0, 0));
		labelComboBox.setBounds(344, 11, 58, 21);
//下拉框变化的响应
		contentPane.add(labelComboBox);
				
		int nowPage = 1;
		pageSum = texts.length/3+1;
		JLabel pageSumLabel = new JLabel();
		pageSumLabel.setHorizontalAlignment(SwingConstants.LEFT);
		pageSumLabel.setFont(new Font("宋体", Font.PLAIN, 13));
		pageSumLabel.setBounds(211, 190, 36, 15);
		pageSumLabel.setText("/"+pageSum);
		contentPane.add(pageSumLabel);
		
		JPopupMenu popupMenu = new JPopupMenu(); 
		JMenuItem jm1 = new JMenuItem("标注");
		JMenuItem jm2 = new JMenuItem("删除") ;
		popupMenu.add(jm1);
		popupMenu.add(jm2);
	
		jm1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根	
				PasteView pasteView = new PasteView();
				pasteView.setLocation(IndexView.this.getLocation());
				pasteView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pasteView.setVisible(true);
				dispose();
			}		
		});	
		jm2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
//删除项响应				
			}		
		});	
		
		String[] onePageText=getPageList(pageNo);
		list = new JList(onePageText);
		list.setFont(new Font("宋体", Font.PLAIN, 12));
		list.setBounds(42, 41, 349, 136);	
		list.setFixedCellWidth(list.getBounds().width);
		list.setFixedCellHeight(list.getBounds().height/3);
		list.setCellRenderer(new ListCellRenderer() {
			@Override
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
			   if (e.isPopupTrigger()&&list.getSelectedIndex()!=-1) { 
//获取点击的对象的值
//		            Object selected = list.getModel().getElementAt(list.getSelectedIndex()); 
		            popupMenu.show(e.getComponent(),e.getX(), e.getY()); 
	           }  		     
		   } 
		   @Override 
		   public void mouseReleased(MouseEvent e) { 
			   if (e.isPopupTrigger()&&list.getSelectedIndex()!=-1) {   
		            popupMenu.show(e.getComponent(),e.getX(), e.getY()); 
	           } 
		   }  	  							   		    
		}); 
		contentPane.add(list);
			
		pageNoInput = new JTextField();
		pageNoInput.setHorizontalAlignment(SwingConstants.CENTER);
		pageNoInput.setFont(new Font("宋体", Font.PLAIN, 12));
		pageNoInput.setText("1");
		pageNoInput.setBounds(180, 190, 26, 15);
		pageNoInput.setColumns(10);		
		pageNoInput.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();				
				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){}
				else	
					e.consume(); 	
				if(keyChar == KeyEvent.VK_ENTER) {
					int a = Integer.valueOf(pageNoInput.getText());
					if( a >= 1  && a <= pageSum ) {
						list.setListData(getPageList(a));
					}
					else {
						list.setListData(getPageList(1));
						pageNoInput.setText("1");
					}
				}
			}
		});
		contentPane.add(pageNoInput);
		
		JButton imgLeft = new JButton(new ImageIcon("src/main/resources/image/img_left.png"));
		imgLeft.setBounds(155, 189, 16, 16);
		imgLeft.setBackground(new Color(240,240,240));
		imgLeft.setBorder(null);
		imgLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				int a=Integer.valueOf(pageNoInput.getText());
				a--;
				if( a > 0 && a <= pageSum) {
					list.setListData(getPageList(a));
					pageNoInput.setText(String.valueOf(a));
				}
				else {
					list.setListData(getPageList(1));
					pageNoInput.setText("1");
				}					
			}			
		});
		contentPane.add(imgLeft);
		
		JButton imgRight = new JButton(new ImageIcon("src/main/resources/image/img_right.png"));
		imgRight.setBounds(245, 189, 16, 16);
		imgRight.setBackground(new Color(240,240,240));
		imgRight.setBorder(null);
		imgRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根				
				int a=Integer.valueOf(pageNoInput.getText());
				a++;
				if( a > 0 && a <= pageSum) {
					list.setListData(getPageList(a));
					pageNoInput.setText(String.valueOf(a));
				}
				else {
					list.setListData(getPageList(1));
					pageNoInput.setText("1");
				}					
			}			
		});
		contentPane.add(imgRight);				
	}
	
	private String[] getPageList(int pageNo) {
		String[] onePageText = new String[3];
		int start=(pageNo-1)*3;
		for(int i=0;i<3;i++) {
			if( start+i < texts.length)
				onePageText[i]=texts[start+i];
			else
				onePageText[i]=" ";
		}
		return onePageText;
	}
}
