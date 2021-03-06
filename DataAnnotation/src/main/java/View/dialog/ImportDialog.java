package View.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.IndexController;
import Model.LogAnnotation;
import View.IndexView;
import View.PasteView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class ImportDialog extends JDialog {
	public boolean fileflag = false;
	String commentPath = "";
	String labelPath = "";

	public ImportDialog(JFrame f, String s) {
		super(f, s);
		
		final JPanel contentPanel = new JPanel();
		setBounds(100, 100, 650, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		final JTextPane cmtPath = new JTextPane();
		cmtPath.setEnabled(false);
		cmtPath.setBackground(Color.WHITE);
		cmtPath.setDisabledTextColor(Color.BLACK);
		cmtPath.setBounds(10, 80, 520, 25);
		contentPanel.add(cmtPath);

		final JTextPane lbPath = new JTextPane();
		lbPath.setEnabled(false);
		lbPath.setBackground(Color.WHITE);
		lbPath.setDisabledTextColor(Color.BLACK);
		lbPath.setBounds(10, 150, 520, 25);
		contentPanel.add(lbPath);
		
		JLabel cmtLabel = new JLabel("评论文件路径");
		cmtLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		cmtLabel.setBounds(10, 55, 100, 25);
		contentPanel.add(cmtLabel);
		
		JLabel labelLabel = new JLabel("标签文件路径");
		labelLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		labelLabel.setBounds(10, 125, 100, 25);
		contentPanel.add(labelLabel);
		
		JButton cmtBrowserButton = new JButton("浏览");
		cmtBrowserButton.setBounds(550, 80, 70, 25);
		contentPanel.add(cmtBrowserButton);
		cmtBrowserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commentPath = openfile(cmtPath);
			}
		});

		JButton labelBrowserButton = new JButton("浏览");
		labelBrowserButton.setBounds(550, 150, 70, 25);
		contentPanel.add(labelBrowserButton);
		labelBrowserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelPath = openfile(lbPath);
			}
		});
		
		JButton okButton = new JButton("确定");
		okButton.setBounds(120, 210, 90, 25);
		contentPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(commentPath.equals("") || labelPath.equals(""))
				{
					JOptionPane.showMessageDialog(ImportDialog.this, "路径为空");
				}
				else
				{
					IndexController.commentPath = commentPath;
					IndexController.labelPath = labelPath;
					fileflag = true;
					dispose();
				}
				
			}
		});
		
		JButton cancelButton = new JButton("取消");
		cancelButton.setBounds(400, 210, 90, 25);
		contentPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	private String openfile(JTextPane filepath)
	{
		JFileChooser loadFileChooser=new JFileChooser();
		String loadFilePath = "";
		loadFileChooser.setDialogTitle("请选择加载文件");
		loadFileChooser.setCurrentDirectory(new File("./"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("文本文件(*.txt)", "txt");
		loadFileChooser.setFileFilter(filter);
		loadFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int option = loadFileChooser.showOpenDialog(null);		 // 显示文件打开对话框
        if (option == JFileChooser.APPROVE_OPTION) {		// 确定用户按下打开按钮，而非取消按钮
        	loadFilePath = loadFileChooser.getSelectedFile().getAbsolutePath();
	        System.out.println(loadFilePath);
	        filepath.setText(loadFilePath);
        }
        return loadFilePath;
	}
}