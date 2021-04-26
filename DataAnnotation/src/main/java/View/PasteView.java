package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JScrollBar;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;

public class PasteView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasteView frame = new PasteView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PasteView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PasteView.class.getResource("/res/app.png")));
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
				ShowLabelView showlabelview = new ShowLabelView();
				showlabelview.setVisible(true);
				dispose();
			}
		});

		JButton chartIcon = new JButton(new ImageIcon(PasteView.class.getResource("/res/chart.png")));
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

		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setBounds(270, 195, 90, 25);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IndexView indexview = new IndexView();
				indexview.setVisible(true);
				dispose();
			}
		});

		JTextPane commentTextPane = new JTextPane();
		commentTextPane.setText("\u65E0\u6570\u636E");
		commentTextPane.setBounds(20, 40, 200, 130);
		contentPane.add(commentTextPane);

		JTextArea labelTypeTextArea = new JTextArea();
		labelTypeTextArea.setBackground(UIManager.getColor("Button.background"));
		labelTypeTextArea.setText("\u9009\u62E9\u6807\u7B7E\u7C7B\u578B");
		labelTypeTextArea.setBounds(230, 65, 84, 25);
		contentPane.add(labelTypeTextArea);

		JTextArea labelTextArea = new JTextArea();
		labelTextArea.setBackground(UIManager.getColor("Button.background"));
		labelTextArea.setText("\u9009\u62E9\u6807\u7B7E");
		labelTextArea.setBounds(245, 125, 56, 25);
		contentPane.add(labelTextArea);

		JComboBox labelTypeComboBox = new JComboBox();
		labelTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "\u8BC4\u4EF7", "\u662F\u5426\u5E7F\u544A"}));
		labelTypeComboBox.setBounds(330, 65, 90, 25);
		contentPane.add(labelTypeComboBox);

		JComboBox labelComboBox = new JComboBox();
		labelComboBox.setBounds(330, 125, 90, 25);
		contentPane.add(labelComboBox);
	}
}
