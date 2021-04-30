package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {
	protected JPanel contentPane;
	protected JMenuItem importMenuItem;
	protected JMenuItem exportMenuItem;
	protected JMenuItem downloadMenuItem;
	protected JButton labelIcon;
	protected JButton chartIcon;
	
	public Frame() {
		setIconImage(new ImageIcon("src/main/resources/image/img_dataannotation.png").getImage());
		setTitle("\u6570\u636E\u6807\u6CE8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 1000, 700);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("\u6587\u4EF6");
		menuBar.add(fileMenu);

		importMenuItem = new JMenuItem("\u5BFC\u5165\u6587\u4EF6");
		importMenuItem.setIcon(null);
		fileMenu.add(importMenuItem);

		exportMenuItem = new JMenuItem("\u5BFC\u51FA\u6587\u4EF6");
		fileMenu.add(exportMenuItem);
		
		downloadMenuItem = new JMenuItem("下载评论");
		fileMenu.add(downloadMenuItem);

		JMenuItem exitMenuItem = new JMenuItem("\u9000\u51FA");
		exitMenuItem.addActionListener(new ActionListener() {
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

		labelIcon = new JButton(new ImageIcon(PasteView.class.getResource("/res/label.png")));
		toolBar.add(labelIcon);
		
		
		chartIcon = new JButton(new ImageIcon(PasteView.class.getResource("/res/chart.png")));
		toolBar.add(chartIcon);
	}

}
