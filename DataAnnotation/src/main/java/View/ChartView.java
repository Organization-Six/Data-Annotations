package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ChartView extends JFrame {

	private JPanel contentPane;
	private String[] labels= {" ","评价","是否广告"};	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ChartView() {
		setIconImage(new ImageIcon("src/main/resources/image/img_dataannotation.png").getImage());
		setTitle("\u6570\u636E\u6807\u6CE8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("\u6587\u4EF6");
		menuBar.add(fileMenu);

		JMenuItem importMenuItem = new JMenuItem("\u5BFC\u5165\u6587\u4EF6");
		importMenuItem.setIcon(null);
		fileMenu.add(importMenuItem);

		JMenuItem exportMenuItem = new JMenuItem("\u5BFC\u51FA\u6587\u4EF6");
		fileMenu.add(exportMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("\u9000\u51FA");
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
				showLabelView.setLocation(ChartView.this.getLocation());
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
				chartView.setLocation(ChartView.this.getLocation());
				chartView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				chartView.setVisible(true);
				dispose();
			}
		});
		
		JComboBox labelComboBox = new JComboBox();
		labelComboBox.setModel(new DefaultComboBoxModel(labels));
		labelComboBox.setBackground(Color.WHITE);
		labelComboBox.setFont(new Font("宋体", Font.PLAIN, 12));
		labelComboBox.setForeground(new Color(0, 0, 0));
		labelComboBox.setBounds(28, 88, 102, 21);
//下拉框变化的响应
		contentPane.add(labelComboBox);
		
		JButton returnIndexButton = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		returnIndexButton.setBounds(163, 186, 97, 25);
		returnIndexButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				IndexView indexView = new IndexView();
				indexView.setLocation(ChartView.this.getLocation());
				indexView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				indexView.setVisible(true);
				dispose();	
			}
		});
		contentPane.add(returnIndexButton);
		
		JPanel chartPanel = new JPanel();
		chartPanel.setBounds(189, 25, 172, 149);
		chartPanel.setLayout(null);
		contentPane.add(chartPanel);
		
//仅做占位用
		JLabel lblNewLabel = new JLabel("\u663E\u793A\u997C\u56FE");
		lblNewLabel.setSize(172, 149);
		lblNewLabel.setLocation(0, 0);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chartPanel.add(lblNewLabel);
	}
}
