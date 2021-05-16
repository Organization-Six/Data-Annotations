package JavaTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.InputEvent;

import javax.swing.JDialog;
import  javax.swing.JFrame;
import javax.swing.JList;

import  junit.framework.TestCase;

import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.Timeout;
import  org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JEditorPaneOperator;
import  org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JListOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.jemmy.operators.JMenuOperator;
import org.netbeans.jemmy.operators.JPopupMenuOperator;
import org.netbeans.jemmy.operators.JRadioButtonOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import  org.netbeans.jemmy.operators.JTextAreaOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import  org.netbeans.jemmy.operators.JMenuItemOperator;
import org.netbeans.jemmy.util.NameComponentChooser;
import org.netbeans.jemmy.drivers.buttons.*;

import Model.CommentBank;
import Model.LabelBank;
import View.*;

public class UITest extends TestCase{
	
	private IndexView indexView;
    private JFrameOperator frmOpr;
	final CommentBank cmtBank = new CommentBank();
	final LabelBank labBank = new LabelBank();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
        
		indexView = new IndexView(cmtBank, labBank);
        
        frmOpr = new JFrameOperator(indexView);
        frmOpr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmOpr.setVisible(true);
        
	}

	@After
	public void tearDown() throws Exception {
		frmOpr.dispose();       
        super.tearDown();
	}

	@Test
	public void test() {
		
		 JListOperator listOpr = new JListOperator(frmOpr, new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof JList);
             }

             //@Override
             public String getDescription() {
                 return "JList";
             }

             @Override
             public String toString() {
                 return "JFileChooserOperator.getFileList.ComponentChooser{description = " + getDescription() + '}';
             }
         });
		 
		 assertEquals("无数据",listOpr.getModel().getElementAt(0));
		 
		 
		 JMenuBarOperator menuOpr = new JMenuBarOperator(frmOpr);
		 JMenuOperator fileMenuOpr = new JMenuOperator(menuOpr.getMenu(0));
		 fileMenuOpr.clickMouse();
		 new Timeout("pausing",1000).sleep();
		 JMenuItemOperator downLoadOpr = new JMenuItemOperator(fileMenuOpr.getItem(2));
		 downLoadOpr.pushNoBlock();
		 new Timeout("pausing",1000).sleep();
		 JDialog downDialog=JDialogOperator.waitJDialog("输入股票代码",false,false);
		 JDialogOperator downDialogOpr = new JDialogOperator(downDialog);
		 JTextFieldOperator textIDOpr = new JTextFieldOperator(downDialogOpr,0);
		 textIDOpr.setText("SZ002304");
		 new Timeout("pausing",1000).sleep();
		 JButtonOperator btnCancelOpr = new JButtonOperator(downDialogOpr, "确定");
		 btnCancelOpr.clickMouse();
		 JDialogOperator dowmBarOpr = new JDialogOperator(JDialogOperator.waitJDialog("下载窗口",false,false));
		 dowmBarOpr.setVisible(true);
		 new Timeout("pausing",2000).sleep();
		 JButtonOperator btnStopOpr = new JButtonOperator(dowmBarOpr, "终止");
		 btnStopOpr.clickMouse();
		 JDialogOperator dowmFinOpr = new JDialogOperator(JDialogOperator.waitJDialog("消息",false,false));
		 JButtonOperator btnFinOKOpr = new JButtonOperator(dowmFinOpr, "确定");
		 new Timeout("pausing",1000).sleep();
		 btnFinOKOpr.clickMouse();
		 
		 JButtonOperator btnLabelOpr = new JButtonOperator(frmOpr, 2);
		 new Timeout("pausing",1000).sleep();
		 btnLabelOpr.clickMouse();
		 
		 JFrameOperator showLabelViewOpr = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof ShowLabelView);
             }

             //@Override
             public String getDescription() {
                 return "ShowLabelView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JButtonOperator btnAddLabelOpr = new JButtonOperator(showLabelViewOpr, "+自定义标签");
		 new Timeout("pausing",1000).sleep();
		 btnAddLabelOpr.clickMouse();
		 
		 JFrameOperator createLabelViewOpr = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof CreateLabelView);
             }

             //@Override
             public String getDescription() {
                 return "CreateLabelView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JEditorPaneOperator edit1Opr = new JEditorPaneOperator(createLabelViewOpr,0);
		 edit1Opr.setText("评价");
		 JEditorPaneOperator edit2Opr = new JEditorPaneOperator(createLabelViewOpr,1);
		 edit2Opr.setText("好评");
		 JEditorPaneOperator edit3Opr = new JEditorPaneOperator(createLabelViewOpr,2);
		 edit3Opr.setText("差评");
		 new Timeout("pausing",1000).sleep();
		 JButtonOperator btnAddLabelOKOpr = new JButtonOperator(createLabelViewOpr, "确定");
		 btnAddLabelOKOpr.clickMouse();
		 
		 
		 
		 JFrameOperator showLabelViewOpr2 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof ShowLabelView);
             }

             //@Override
             public String getDescription() {
                 return "ShowLabelView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JButtonOperator btnAddLabelOpr2 = new JButtonOperator(showLabelViewOpr2, "+自定义标签");
		 new Timeout("pausing",1000).sleep();
		 btnAddLabelOpr2.clickMouse();
		 
		 JFrameOperator createLabelViewOpr2 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof CreateLabelView);
             }

             //@Override
             public String getDescription() {
                 return "CreateLabelView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 
		 JEditorPaneOperator edit4Opr = new JEditorPaneOperator(createLabelViewOpr2,0);
		 edit4Opr.setText("广告");
		 JEditorPaneOperator edit5Opr = new JEditorPaneOperator(createLabelViewOpr2,1);
		 edit5Opr.setText("是");
		 JEditorPaneOperator edit6Opr = new JEditorPaneOperator(createLabelViewOpr2,2);
		 edit6Opr.setText("否");
		 new Timeout("pausing",1000).sleep();
		 JButtonOperator btnAddLabelOKOpr2 = new JButtonOperator(createLabelViewOpr2, "确定");
		 btnAddLabelOKOpr2.clickMouse();
		 
		 JFrameOperator showLabelViewOpr3 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof ShowLabelView);
             }

             //@Override
             public String getDescription() {
                 return "ShowLabelView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JTableOperator labelTableOpr = new JTableOperator(showLabelViewOpr3);
		 Point p = labelTableOpr.getPointToClick(1, 1);
		 labelTableOpr.clickMouse(p.x, p.y, 1, InputEvent.BUTTON1_MASK);
		 new Timeout("pausing",1000).sleep();
		 labelTableOpr.clickForPopup(p.x, p.y, InputEvent.BUTTON3_MASK);
		 
		 JPopupMenuOperator popupMenuLabelOpr = new JPopupMenuOperator(showLabelViewOpr3);
		 JMenuItemOperator updateLabelOpr = new JMenuItemOperator(popupMenuLabelOpr, 0);
		 new Timeout("pausing",1000).sleep();
		 updateLabelOpr.clickMouse();
		 
		 JFrameOperator updateLabelViewOpr = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof UpdateLabelView);
             }

             //@Override
             public String getDescription() {
                 return "updateLabelViewOpr";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 
		 JEditorPaneOperator editTypeOpr = new JEditorPaneOperator(updateLabelViewOpr,0);
		 new Timeout("pausing",1000).sleep();
		 editTypeOpr.setText("是否广告");
		 new Timeout("pausing",1000).sleep();
		 JButtonOperator btnUpdateLabelOKOpr = new JButtonOperator(updateLabelViewOpr, "确定");
		 btnUpdateLabelOKOpr.clickMouse();
		 
		 JFrameOperator showLabelViewOpr4 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof ShowLabelView);
             }

             //@Override
             public String getDescription() {
                 return "ShowLabelView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JTableOperator labelTableOpr2 = new JTableOperator(showLabelViewOpr4);
		 Point p2 = labelTableOpr2.getPointToClick(1, 1);
		 labelTableOpr2.clickMouse(p2.x, p2.y, 1, InputEvent.BUTTON1_MASK);
		 new Timeout("pausing",1000).sleep();
		 labelTableOpr2.clickForPopup(p2.x, p2.y, InputEvent.BUTTON3_MASK);
		 
		 JPopupMenuOperator popupMenuLabelOpr2 = new JPopupMenuOperator(showLabelViewOpr4);
		 JMenuItemOperator deleteLabelOpr = new JMenuItemOperator(popupMenuLabelOpr2, 1);
		 new Timeout("pausing",1000).sleep();
		 deleteLabelOpr.clickMouse();
		 new Timeout("pausing",1000).sleep();
		 
		 JFrameOperator showLabelViewOpr5 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof ShowLabelView);
             }

             //@Override
             public String getDescription() {
                 return "ShowLabelView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JButtonOperator btnLabelOKOpr = new JButtonOperator(showLabelViewOpr5, "确定");
		 btnLabelOKOpr.clickMouse();
		 
		 JFrameOperator indexViewOpr = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof IndexView);
             }

             //@Override
             public String getDescription() {
                 return "IndexView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 
		 JListOperator listOpr2 = new JListOperator(indexViewOpr, new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof JList);
             }

             //@Override
             public String getDescription() {
                 return "JList";
             }

             @Override
             public String toString() {
                 return "JFileChooserOperator.getFileList.ComponentChooser{description = " + getDescription() + '}';
             }
         });
		 new Timeout("pausing",1000).sleep();
		 Point p3 = listOpr2.getClickPoint(0);
		 listOpr2.clickForPopup(p3.x, p3.y, InputEvent.BUTTON3_MASK);
		 
		 JPopupMenuOperator popupMenuCommentOpr = new JPopupMenuOperator(indexViewOpr);
		 JMenuItemOperator deleteCommentOpr = new JMenuItemOperator(popupMenuCommentOpr, 1);
		 new Timeout("pausing",1000).sleep();
		 deleteCommentOpr.clickMouse();
		 new Timeout("pausing",1000).sleep();
		 
		 Point comment1 = listOpr2.getClickPoint(0);
		 listOpr2.clickForPopup(comment1.x, comment1.y, InputEvent.BUTTON3_MASK);
		 JMenuItemOperator pasteCommentOpr = new JMenuItemOperator(popupMenuCommentOpr, 0);
		 new Timeout("pausing",1000).sleep();
		 pasteCommentOpr.clickMouse();
		 
		 
		 JFrameOperator pasteViewOpr = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof PasteView);
             }

             //@Override
             public String getDescription() {
                 return "PasteView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JRadioButtonOperator typeRadioOpr1 = new JRadioButtonOperator(pasteViewOpr, 1);
		 new Timeout("pausing",1000).sleep();
		 typeRadioOpr1.clickMouse();
		 JRadioButtonOperator choiceRadioOpr1 = new JRadioButtonOperator(pasteViewOpr, 3);
		 new Timeout("pausing",1000).sleep();
		 choiceRadioOpr1.clickMouse();
		 JButtonOperator btnPasteOKOpr1 = new JButtonOperator(pasteViewOpr, "确定");
		 new Timeout("pausing",1000).sleep();
		 btnPasteOKOpr1.clickMouse();
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 JFrameOperator indexViewOpr2 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof IndexView);
             }

             //@Override
             public String getDescription() {
                 return "IndexView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 
		 JListOperator listOpr3 = new JListOperator(indexViewOpr2, new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof JList);
             }

             //@Override
             public String getDescription() {
                 return "JList";
             }

             @Override
             public String toString() {
                 return "JFileChooserOperator.getFileList.ComponentChooser{description = " + getDescription() + '}';
             }
         });
		 new Timeout("pausing",1000).sleep();		 
		 Point comment2 = listOpr3.getClickPoint(1);
		 listOpr3.clickForPopup(comment2.x, comment2.y, InputEvent.BUTTON3_MASK);
		 JPopupMenuOperator popupMenuCommentOpr2 = new JPopupMenuOperator(indexViewOpr2);
		 JMenuItemOperator pasteCommentOpr2 = new JMenuItemOperator(popupMenuCommentOpr2, 0);
		 new Timeout("pausing",1000).sleep();
		 pasteCommentOpr2.clickMouse();
		 
		 
		 JFrameOperator pasteViewOpr2 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof PasteView);
             }

             //@Override
             public String getDescription() {
                 return "PasteView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JRadioButtonOperator typeRadioOpr2 = new JRadioButtonOperator(pasteViewOpr2, 1);
		 new Timeout("pausing",1000).sleep();
		 typeRadioOpr2.clickMouse();
		 JRadioButtonOperator choiceRadioOpr2 = new JRadioButtonOperator(pasteViewOpr2, 4);
		 new Timeout("pausing",1000).sleep();
		 choiceRadioOpr2.clickMouse();
		 JButtonOperator btnPasteOKOpr2 = new JButtonOperator(pasteViewOpr2, "确定");
		 new Timeout("pausing",1000).sleep();
		 btnPasteOKOpr2.clickMouse();
		 
		 
		 
		 
		 
		 
		 
		 JFrameOperator indexViewOpr3 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof IndexView);
             }

             //@Override
             public String getDescription() {
                 return "IndexView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 
		 JListOperator listOpr4 = new JListOperator(indexViewOpr3, new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof JList);
             }

             //@Override
             public String getDescription() {
                 return "JList";
             }

             @Override
             public String toString() {
                 return "JFileChooserOperator.getFileList.ComponentChooser{description = " + getDescription() + '}';
             }
         });
		 new Timeout("pausing",1000).sleep();		 
		 Point comment3 = listOpr4.getClickPoint(2);
		 listOpr4.clickForPopup(comment3.x, comment3.y, InputEvent.BUTTON3_MASK);
		 JPopupMenuOperator popupMenuCommentOpr3 = new JPopupMenuOperator(indexViewOpr3);
		 JMenuItemOperator pasteCommentOpr3 = new JMenuItemOperator(popupMenuCommentOpr3, 0);
		 new Timeout("pausing",1000).sleep();
		 pasteCommentOpr3.clickMouse();
		 
		 
		 JFrameOperator pasteViewOpr3 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof PasteView);
             }

             //@Override
             public String getDescription() {
                 return "PasteView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JRadioButtonOperator typeRadioOpr3 = new JRadioButtonOperator(pasteViewOpr3, 1);
		 new Timeout("pausing",1000).sleep();
		 typeRadioOpr3.clickMouse();
		 JRadioButtonOperator choiceRadioOpr3 = new JRadioButtonOperator(pasteViewOpr3, 3);
		 new Timeout("pausing",1000).sleep();
		 choiceRadioOpr3.clickMouse();
		 JButtonOperator btnPasteOKOpr3 = new JButtonOperator(pasteViewOpr3, "确定");
		 new Timeout("pausing",1000).sleep();
		 btnPasteOKOpr3.clickMouse();
		 
		 
		 
		 
		 
		 
		 
		 
		 JFrameOperator indexViewOpr4 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof IndexView);
             }

             //@Override
             public String getDescription() {
                 return "IndexView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JComboBoxOperator comboBoxOpr = new JComboBoxOperator(indexViewOpr4);
		 comboBoxOpr.clickMouse();
		 new Timeout("pausing",1000).sleep();
		 comboBoxOpr.setSelectedIndex(2);
		 new Timeout("pausing",1000).sleep();
		 comboBoxOpr.setSelectedIndex(3);
		 new Timeout("pausing",1000).sleep();
		 JButtonOperator btnChartOpr = new JButtonOperator(indexViewOpr4, 3);
		 btnChartOpr.clickMouse();
		 
		 
		 JFrameOperator chartViewOpr = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof ChartView);
             }

             //@Override
             public String getDescription() {
                 return "ChartView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JComboBoxOperator comboBoxOpr2 = new JComboBoxOperator(chartViewOpr);
		 comboBoxOpr2.clickMouse();
		 new Timeout("pausing",1000).sleep();
		 comboBoxOpr2.setSelectedIndex(1);
		 new Timeout("pausing",5000).sleep();
		 JButtonOperator btnBackOpr = new JButtonOperator(chartViewOpr, "返回主页");
		 btnBackOpr.clickMouse();
		 
		 
		 
		 JFrameOperator indexViewOpr5 = new JFrameOperator(JFrameOperator.findJFrame(new ComponentChooser() {
             //@Override
             public boolean checkComponent(Component comp) {
                 return (comp != null
                         && comp instanceof IndexView);
             }

             //@Override
             public String getDescription() {
                 return "IndexView";
             }

             @Override
             public String toString() {
                 return "JViewChooserOperator.getView.ComponentChooser{description = " + getDescription() + '}';
             }
         }));
		 JMenuBarOperator menuOpr2 = new JMenuBarOperator(indexViewOpr5);
		 JMenuOperator fileMenuOpr2 = new JMenuOperator(menuOpr2.getMenu(0));
		 fileMenuOpr2.clickMouse();
		 new Timeout("pausing",1000).sleep();
		 JMenuItemOperator exportOpr = new JMenuItemOperator(fileMenuOpr2.getItem(1));
		 exportOpr.pushNoBlock();
		 JDialogOperator exportFinOpr = new JDialogOperator(JDialogOperator.waitJDialog("消息",false,false));
		 JButtonOperator btnFinOKOpr2 = new JButtonOperator(exportFinOpr, "确定");
		 new Timeout("pausing",1000).sleep();
		 btnFinOKOpr2.clickMouse();
		 
		 
		 new Timeout("pausing",1000).sleep();
		 fileMenuOpr2.clickMouse();
		 JMenuItemOperator importOpr = new JMenuItemOperator(fileMenuOpr2.getItem(0));
		 new Timeout("pausing",1000).sleep();
		 importOpr.pushNoBlock();
		 JDialogOperator importFinOpr = new JDialogOperator(JDialogOperator.waitJDialog("消息",false,false));
		 JButtonOperator btnFinOKOpr3 = new JButtonOperator(importFinOpr, "确定");
		 new Timeout("pausing",1000).sleep();
		 btnFinOKOpr3.clickMouse();
		 
		 JComboBoxOperator newComboBoxOpr = new JComboBoxOperator(indexViewOpr5);
		 newComboBoxOpr.clickMouse();
		 new Timeout("pausing",1000).sleep();
		 newComboBoxOpr.setSelectedIndex(2);
		 new Timeout("pausing",1000).sleep();
		 newComboBoxOpr.setSelectedIndex(3);
		 new Timeout("pausing",1000).sleep();

		 new Timeout("pausing",3000).sleep();
		 
	}

}
