package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateLabelView extends JFrame {

	private JPanel contentPane;

	public UpdateLabelView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateLabelView.class.getResource("/res/app.png")));
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

		JButton fileIcon = new JButton(new ImageIcon(UpdateLabelView.class.getResource("/res/file.png")));
		toolBar.add(fileIcon);

		JButton folderIcon = new JButton(new ImageIcon(UpdateLabelView.class.getResource("/res/folder.png")));
		toolBar.add(folderIcon);

		JButton labelIcon = new JButton(new ImageIcon(UpdateLabelView.class.getResource("/res/label.png")));
		toolBar.add(labelIcon);
		labelIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showlabelview = new ShowLabelView();
				showlabelview.setVisible(true);
				dispose();
			}
		});

		JButton chartIcon = new JButton(new ImageIcon(UpdateLabelView.class.getResource("/res/chart.png")));
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

		JTextArea updateLabelType = new JTextArea();
		updateLabelType.setBackground(UIManager.getColor("Button.background"));
		updateLabelType.setText("\u8BF7\u8F93\u5165\u4FEE\u6539\u540E\u6807\u7B7E\u7C7B\u578B\uFF1A");
		updateLabelType.setBounds(65, 55, 145, 25);
		contentPane.add(updateLabelType);

		JTextArea updateLabel1 = new JTextArea();
		updateLabel1.setBackground(UIManager.getColor("Button.background"));
		updateLabel1.setText("\u8BF7\u8F93\u5165\u4FEE\u6539\u540E\u6807\u7B7E\u9009\u98791\uFF1A");
		updateLabel1.setBounds(65, 97, 150, 25);
		contentPane.add(updateLabel1);

		JTextArea updateLabel2 = new JTextArea();
		updateLabel2.setBackground(UIManager.getColor("Button.background"));
		updateLabel2.setText("\u8BF7\u8F93\u5165\u4FEE\u6539\u540E\u6807\u7B7E\u9009\u98792\uFF1A");
		updateLabel2.setBounds(65, 140, 150, 25);
		contentPane.add(updateLabel2);

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
