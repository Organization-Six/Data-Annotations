package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.Icon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowLabelView extends JFrame {

	private JPanel contentPane;

	public ShowLabelView() {
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

		JButton fileIcon = new JButton(new ImageIcon(ShowLabelView.class.getResource("/res/file.png")));
		toolBar.add(fileIcon);

		JButton folderIcon = new JButton(new ImageIcon(ShowLabelView.class.getResource("/res/folder.png")));
		toolBar.add(folderIcon);

		JButton labelIcon = new JButton(new ImageIcon(ShowLabelView.class.getResource("/res/label.png")));
		toolBar.add(labelIcon);

		JButton chartIcon = new JButton(new ImageIcon(ShowLabelView.class.getResource("/res/chart.png")));
		toolBar.add(chartIcon);
		chartIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChartView chartview = new ChartView();
				chartview.setLocation(ShowLabelView.this.getLocation());
				chartview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				chartview.setVisible(true);
				dispose();
			}
		});

		JButton okButton = new JButton("\u786E\u5B9A");
		okButton.setBounds(70, 195, 90, 25);
		contentPane.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasteView pasteview = new PasteView();
				pasteview.setLocation(ShowLabelView.this.getLocation());
				pasteview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pasteview.setVisible(true);
				dispose();
			}
		});

		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setBounds(270, 195, 90, 25);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasteView pasteview = new PasteView();
				pasteview.setLocation(ShowLabelView.this.getLocation());
				pasteview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pasteview.setVisible(true);
				dispose();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 40, 350, 100);
		contentPane.add(scrollPane);

		String[] columnNames = { "\u6807\u7B7E\u7C7B\u578B", "\u6807\u7B7E\u9009\u98791", "\u6807\u7B7E\u9009\u98792" };
		Object[][] rowData = { { "\u8BC4\u4EF7", "\u597D\u8BC4", "\u5DEE\u8BC4" },
				{ "\u662F\u5426\u5E7F\u544A", "\u5E7F\u544A", "\u975E\u5E7F\u544A" }, };
		JTable labelTable = new JTable(rowData, columnNames);
		scrollPane.setViewportView(labelTable);

		JButton createLabelButton = new JButton("+\u81EA\u5B9A\u4E49\u6807\u7B7E");
		createLabelButton.setBounds(160, 150, 110, 25);
		contentPane.add(createLabelButton);
		createLabelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateLabelView createlabelview = new CreateLabelView();
				createlabelview.setLocation(ShowLabelView.this.getLocation());
				createlabelview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				createlabelview.setVisible(true);
				dispose();
			}
		});
	}
}
