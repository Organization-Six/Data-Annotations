package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;
import Model.LogAspect;
import Model.Spider;
import Model.Spider.Data;
import View.ChartView;
import View.IndexView;
import View.PasteView;
import View.ShowLabelView;
import View.dialog.DownLoadDialog;
import View.dialog.ExportDialog;
import View.dialog.ImportDialog;

@Component
public class IndexController {
	private CommentBank cmtBank;	
	private LabelBank labBank;
	private int index;
	private ArrayList<String> choiceList = new ArrayList<String>();
	private String[] labels;
	public static String commentPath = "";
	public static String labelPath = "";
	
	//public static ArrayList table = new ArrayList();
	
	@SuppressWarnings("unchecked")
	public IndexController(final CommentBank cmtBank, final LabelBank labBank,final IndexView view) {
		// TODO 自动生成的构造函数存根
		this.cmtBank = cmtBank;
		this.labBank = labBank;
		initData();
		IndexView.jm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根	
				PasteView pasteView = new PasteView(cmtBank, labBank, index);
				pasteView.setLocation(view.getLocation());
				pasteView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pasteView.setVisible(true);
				view.dispose();
			}		
		});	
		IndexView.jm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
//删除项响应		
				LogAspect.Log( "Controller.IndexController", "remove comment");
				System.out.println("进来了");
				if(index >= 0) {
					cmtBank.getComment().remove(index);
					initData();
					CommentBank.table.remove(index);
					System.out.println(cmtBank.getComment().size());
				}
			}		
		});	
		IndexView.importMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				LogAspect.Log( "Controller.IndexController", "import comment");
				commentPath = "";
				labelPath = "";
				initport();

//				JFileChooser loadFileChooser=new JFileChooser();
//				String loadFilePath;
//				loadFileChooser.setDialogTitle("请选择加载文件");
//				loadFileChooser.setCurrentDirectory(new File("./"));
//				FileNameExtensionFilter filter = new FileNameExtensionFilter("文本文件(*.txt)", "txt");
//				loadFileChooser.setFileFilter(filter);
//				loadFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		        int option = loadFileChooser.showOpenDialog(null);		 // 显示文件打开对话框
//		        if (option == JFileChooser.APPROVE_OPTION) {		// 确定用户按下打开按钮，而非取消按钮
//		        	loadFilePath = loadFileChooser.getSelectedFile().getAbsolutePath();
//			        System.out.println(loadFilePath);
//		        }

				if (view.brsdialog.fileflag == true) {
//					cmtBank.getComment().clear();
//					labBank.getLabel().clear();
					
					try {
						cmtBank.Load(commentPath);
						labBank.Load(labelPath);
						System.out.println(cmtBank.getComment().get(0).getCmtText());
						initImport();						
						JOptionPane.showMessageDialog(view, "导入成功");
					} catch(Exception e) {
						JOptionPane.showMessageDialog(view, "导入失败");
					}
				}

			}

			
		});
		
		IndexView.exportMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				LogAspect.Log( "Controller.IndexController", "export comment");
				commentPath = "";
				labelPath = "";
				initexport();
				
//				JFileChooser saveFileChooser = new JFileChooser();
//				saveFileChooser.setDialogTitle("请选择保存路径");
//				saveFileChooser.setCurrentDirectory(new File("./"));
//			    int retval = saveFileChooser.showSaveDialog(null);
//			    if (retval == JFileChooser.APPROVE_OPTION) {
//			    	File file = saveFileChooser.getSelectedFile();
//			    	if (file == null) {
//			    		return;
//			    	}
//			    	if (!file.getName().toLowerCase().endsWith(".txt")) {
//			    		file = new File(file.getParentFile(), file.getName() + ".txt");
//			    	}
//			    	System.out.println(file.getAbsolutePath());
//			    }

				if (view.exdialog.fileflag == true) {
					
					try {
						cmtBank.Save(commentPath);
						labBank.Save(labelPath);
						JOptionPane.showMessageDialog(view, "导出成功");
						cmtBank.getComment().clear();
						labBank.getLabel().clear();
						IndexView.d.clear();
						initData();
						IndexView.labelComboBox.setModel(new DefaultComboBoxModel(labels));
					} catch(Exception e) {
						JOptionPane.showMessageDialog(view, "导出失败");
					}
					
				}

			}
		});
		
		IndexView.downloadMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				LogAspect.Log( "Controller.IndexController", "download comment");
				String exportStockID = null;
				exportStockID = JOptionPane.showInputDialog(view,"请输入需要下载的股票代码","输入股票代码",JOptionPane.PLAIN_MESSAGE);
				if(!(exportStockID.isEmpty())) {
					ArrayList<Data> comments;
					comments = Spider.Load(exportStockID);
					
					initDown(cmtBank.getComment(), comments);
				}				
			}
		});
		
		IndexView.labelIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowLabelView showLabelView = new ShowLabelView(cmtBank, labBank, -1);
				showLabelView.setLocation(view.getLocation());
				showLabelView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				showLabelView.setVisible(true);
				view.dispose();
			}
		});
		
		IndexView.chartIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChartView chartView = new ChartView(cmtBank, labBank, -1);
				chartView.setLocation(view.getLocation());
				chartView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				chartView.setVisible(true);
				view.dispose();
			}
		});
		
		IndexView.list.addMouseListener(new MouseAdapter() { 	    			  				  
			   @Override 
			   public void mouseClicked(MouseEvent e) { } 

			   @Override 
			   public void mousePressed(MouseEvent e) { 
				   IndexView.list.setSelectedIndex(IndexView.list.locationToIndex(e.getPoint())); //获取鼠标点击的项 
				   if (e.isPopupTrigger()&&IndexView.list.getSelectedIndex()!=-1 && cmtBank.getComment().size() != 0) { 
					   //****comment = cmtBank.getComment().get(list.getSelectedIndex());
					   index = IndexView.list.getSelectedIndex();
	//获取点击的对象的值
//			            Object selected = list.getModel().getElementAt(list.getSelectedIndex());
					   IndexView.popupMenu.show(e.getComponent(),e.getX(), e.getY()); 
		           }  		     
			   } 
			   @Override 
			   public void mouseReleased(MouseEvent e) { 
				   if (e.isPopupTrigger()&&IndexView.list.getSelectedIndex()!=-1 &&cmtBank.getComment().size() != 0) {   
					   IndexView.popupMenu.show(e.getComponent(),e.getX(), e.getY());
			            index = IndexView.list.getSelectedIndex();
		           }
			   }  	  							   		    
			});
		//下拉框变化的响应
		IndexView.labelComboBox.setModel(new DefaultComboBoxModel(labels));
		IndexView.labelComboBox.addItemListener(new ItemListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void itemStateChanged(ItemEvent e){
				LogAspect.Log( "Controller.IndexController", "update labelComboBox");
				if(e.getStateChange() == ItemEvent.SELECTED){
					String item = (String) e.getItem();
					IndexView.d.clear();
					if(e.getItem().equals("全部")) {
						IndexView.conflictList.clear();
						IndexView.confirmList.clear();
						for(int i = 0; i < cmtBank.getComment().size(); i++) {
							Comment comment = cmtBank.getComment().get(i);
							if(comment.getIsConflict()) {
								if(comment.isConfirm()) {
									IndexView.confirmList.add(i);
								}
								else {
									IndexView.conflictList.add(i);
								}
								IndexView.d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
							}
							else
								IndexView.d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
							IndexView.list = new JList(IndexView.d);
						}
						if(IndexView.d.getSize() == 0) {
							IndexView.d.addElement("无数据");
						}
					}
					else if(e.getItem().equals("未分类")) {
						IndexView.conflictList.clear();
						IndexView.confirmList.clear();
						for(int i = 0; i < cmtBank.getComment().size(); i++) {
							if(!(cmtBank.getComment().get(i).isCmtIsMark())) {
								Comment comment = cmtBank.getComment().get(i);
								if(comment.getIsConflict()) {
									if(comment.isConfirm()) {
										IndexView.confirmList.add(i);
									}
									else {
										IndexView.conflictList.add(i);
									}
									IndexView.d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
								}
								else
									IndexView.d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
								IndexView.list = new JList(IndexView.d);
							}
						}
						if(IndexView.d.getSize() == 0) {
							IndexView.d.addElement("无数据");
						}
						
					}
					else {
						IndexView.conflictList.clear();
						IndexView.confirmList.clear();
						for(int i = 0; i < cmtBank.getComment().size(); i++) {
							System.out.println(cmtBank.getComment().get(i).getLabelList());
							if(cmtBank.getComment().get(i).getLabelList().contains(item)) {
								//d.clear();
								Comment comment = cmtBank.getComment().get(i);
								if(comment.getIsConflict()) {
									if(comment.isConfirm()) {
										IndexView.confirmList.add(i);
									}
									else {
										IndexView.conflictList.add(i);
									}
									IndexView.d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
								}
								else
									IndexView.d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
								IndexView.list = new JList(IndexView.d);
							}
							else {
								System.out.println("1");
							}
						}
						if(IndexView.d.getSize() == 0) {
							IndexView.d.addElement("无数据");
						}
					}
				}
			}
		});
	}
	@SuppressWarnings("unchecked")
	private void initData() {
		//只有默认的模型有添加/删除方法
		choiceList.clear();
		IndexView.d.clear();
		IndexView.conflictList.clear();
		IndexView.confirmList.clear();
		if(cmtBank.getComment().size() == 0) {
			IndexView.d.addElement("无数据");
		}
		for(int i = 0; i < cmtBank.getComment().size(); i++){
			Comment comment = cmtBank.getComment().get(i);			
			if(comment.getIsConflict()) {
				if(comment.isConfirm()) {
					IndexView.confirmList.add(i);
				}
				else {
					IndexView.conflictList.add(i);
				}
//	    		System.out.println("冲突");
				IndexView.d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
			}
			else
				IndexView.d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
//    		System.out.println(IndexView.d.get(0));
		}
		
		
		choiceList.add("全部");
		choiceList.add("未分类");
		for(int i = 0; i < labBank.getLabel().size(); i++){
			Label label = labBank.getLabel().get(i);
			for(String choose:label.getLabChoise()) {
				choiceList.add(label.getLabType()+"-"+choose);
			}
			//choiceList.addAll(label.getLabChoise());			
		}
		labels = (String[]) choiceList.toArray(new String[0]);
		//System.out.println(cmtBank.getComment().get(0).getCmtText());
	}
	
	
	private void initport() {
		IndexView.brsdialog = new ImportDialog(null, "选择文件");
		IndexView.brsdialog.setLocationRelativeTo(null);;
		IndexView.brsdialog.setModal(true);
		IndexView.brsdialog.setVisible(true);
	}
	
	private void initexport() {
		IndexView.exdialog = new ExportDialog(null, "选择文件");
		IndexView.exdialog.setLocationRelativeTo(null);;
		IndexView.exdialog.setModal(true);
		IndexView.exdialog.setVisible(true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initDown(ArrayList<Comment> cmtList, ArrayList<Data> comments) {
		IndexView.d.clear();
		cmtBank.getComment().clear();
		labBank.getLabel().clear();

		IndexView.dialog = new DownLoadDialog(null,"下载窗口", cmtList, comments);
		IndexView.dialog.setModal(true);
		IndexView.dialog.setVisible(true);
		
//		for(String comment: cmtBank) {
//			//cmtBank.getComment().add(comment);
//			//d.addElement(comment.getCmtWriter()+ " - "+comment.getCmtTime()+" - "+comment.getCmtText());
//			d.addElement(comment);
//		}
//		System.out.println(d.getSize());
		initData();
		IndexView.labelComboBox.setModel(new DefaultComboBoxModel(labels));		
		IndexView.list = new JList(IndexView.d);
		


	}
	
	@SuppressWarnings("unchecked")
	private void initImport() {
		//cmtBank = new CommentBank();
		//cmtBank.Load();
		initData();
		IndexView.labelComboBox.setModel(new DefaultComboBoxModel(labels));
//		d.clear();
//		for(int i = 0; i < cmtBank.getComment().size(); i++){
//			Comment comment = cmtBank.getComment().get(i);
//			d.addElement(comment.getCmtWriter()+ "-"+comment.getCmtTime()+"-"+comment.getCmtText());
//		}
//		
//		//labBank = new LabelBank();
//		//labBank.Load();
//		for(int i = 0; i < labBank.getLabel().size(); i++){
//			Label label = labBank.getLabel().get(i);
//			choiceList.addAll(label.getLabChoise());			
//		}
//		labels = (String[]) choiceList.toArray(new String[0]);
//		labelComboBox.setModel(new DefaultComboBoxModel(labels));

	}
}
