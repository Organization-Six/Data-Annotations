package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class CreateLabelView extends JFrame {

	private JPanel contentPane;

	public CreateLabelView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreateLabelView.class.getResource("/res/app.png")));
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

		JButton fileIcon = new JButton(new ImageIcon(CreateLabelView.class.getResource("/res/file.png")));
		toolBar.add(fileIcon);

		JButton folderIcon = new JButton(new ImageIcon(CreateLabelView.class.getResource("/res/folder.png")));
		toolBar.add(folderIcon);

		JButton labelIcon = new JButton(new ImageIcon(CreateLabelView.class.getResource("/res/label.png")));
		toolBar.add(labelIcon);
		labelIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showlabelview = new ShowLabelView();
				showlabelview.setVisible(true);
				dispose();
			}
		});

		JButton chartIcon = new JButton(new ImageIcon(CreateLabelView.class.getResource("/res/chart.png")));
		toolBar.add(chartIcon);
		chartIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChartView chartview = new ChartView();
				chartview.setVisible(true);
				dispose();
			}
		});

		JButton okButton = new JButton("\u786E\u5B9A");
		okButton.setBounds(70, 195, 90, 25);
		contentPane.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showlabelview = new ShowLabelView();
				showlabelview.setVisible(true);
				dispose();
			}
		});

		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setBounds(270, 195, 90, 25);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showlabelview = new ShowLabelView();
				showlabelview.setVisible(true);
				dispose();
			}
		});

		JTextArea createLabelType = new JTextArea();
		createLabelType.setBackground(UIManager.getColor("Button.background"));
		createLabelType.setText("\u8BF7\u8F93\u5165\u81EA\u5B9A\u4E49\u6807\u7B7E\u7C7B\u578B\uFF1A");
		createLabelType.setBounds(65, 55, 145, 25);
		contentPane.add(createLabelType);

		JTextArea createLabel1 = new JTextArea();
		createLabel1.setBackground(UIManager.getColor("Button.background"));
		createLabel1.setText("\u8BF7\u8F93\u5165\u81EA\u5B9A\u4E49\u6807\u7B7E\u9009\u98791\uFF1A");
		createLabel1.setBounds(65, 97, 150, 25);
		contentPane.add(createLabel1);

		JTextArea createLabel2 = new JTextArea();
		createLabel2.setBackground(UIManager.getColor("Button.background"));
		createLabel2.setText("\u8BF7\u8F93\u5165\u81EA\u5B9A\u4E49\u6807\u7B7E\u9009\u98792\uFF1A");
		createLabel2.setBounds(65, 140, 150, 25);
		contentPane.add(createLabel2);

		JEditorPane labelTypeEditorPane = new JEditorPane();
		labelTypeEditorPane.setBounds(255, 55, 110, 21);
		contentPane.add(labelTypeEditorPane);

		JEditorPane label1EditorPane = new JEditorPane();
		label1EditorPane.setBounds(255, 97, 110, 21);
		contentPane.add(label1EditorPane);

		JEditorPane label2EditorPane = new JEditorPane();
		label2EditorPane.setBounds(255, 140, 110, 21);
		contentPane.add(label2EditorPane);
	}
}
