package View.custom;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import View.LoginView;


public class LoginJFrame extends JFrame{
	
	private int myHeight,myWidth;
	
	private  TitleJButton closeBut,minBut;
	private JPanel myRootPane;
	private MatteBorder border;
	private JLabel titleImg;
	private JLabel titleWord;
	private  JPanel secondPanel;
	private int titleHeight = 150;
	
	private Container cp;
	
	@SuppressWarnings("static-access")
	public LoginJFrame() {
		this.setBounds(100,100,400,500);
		this.myHeight=getBounds().height;
		this.myWidth=getBounds().width;
		
		status();
		cp=this.getContentPane();
		setBorder();
		titleBar("src/main/resources/image/img_dataannotation.png","数据标注");
		createButton();
		setDragable();
		
	}

	private void status(){
		this.setUndecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("restriction")
			public void run() {
				//圆角
				com.sun.awt.AWTUtilities.setWindowShape(LoginJFrame.this,new RoundRectangle2D.Float(0,0,myWidth,myHeight,12f,12f));   
				com.sun.awt.AWTUtilities.setWindowOpacity(LoginJFrame.this, 1f);   
			}   
		});
		
	}
	
	private void setBorder(){
		myRootPane = new JPanel();
		myRootPane.setBackground(Color.red);

		myRootPane.setBorder (BorderFactory.createRaisedBevelBorder());
		border = new MatteBorder(titleHeight,0,0,0,new Color(245,245,245));
		myRootPane.setBorder(border);

		secondPanel =new JPanel();
		secondPanel.setBounds(0, titleHeight, myWidth, myHeight-titleHeight);
		secondPanel.setBackground(Color.white);
		
		myRootPane.add(secondPanel);
		myRootPane.setLayout(null);
		cp.add(myRootPane);
	}
	
	public void changPanel(int w,int h){
		secondPanel.setBounds(0, titleHeight, w, h-titleHeight);
	}
	
	private void titleBar(String imgPath,String title) {
		int imgSize = 75;
		int wordWidth = 150;
		int wordHeight = 75;
		titleImg = new JLabel(new ImageIcon(imgPath));
		titleImg.setBounds(myWidth/6,titleHeight/2-38,imgSize,imgSize);
		myRootPane.add(titleImg);
		
		titleWord = new JLabel(title,JLabel.LEFT);
		titleWord.setBounds(myWidth/6+imgSize+20,titleHeight/2-38,wordWidth,wordHeight);
		titleWord.setFont(new Font("宋体", Font.BOLD, 30));
		myRootPane.add(titleWord);
	}
	
	private void createButton() {
		closeBut = new TitleJButton("close");
		closeBut.setBounds(this.getWidth()-35,10, 20, 20);
		myRootPane.add(closeBut);
		closeBut.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {		
				System.exit(0); //关闭 
			}
		});
		
		minBut = new TitleJButton("mini");
		minBut.setBounds(this.getWidth()-70,10, 20, 20);
		myRootPane.add(minBut);
		minBut.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				LoginJFrame.this.setExtendedState(JFrame.ICONIFIED); //最小化 
			}
		});
	}
	
	private boolean isDragged = false;
	private  Point tmp ;		//记录点击的坐标
	private Point loc ;			//点击位置
	private int borderStype=0;
	private int delay=0;
	
	private void setDragable(){
		this.addMouseListener(new MouseAdapter() {           
			public void mouseReleased(java.awt.event.MouseEvent e) {              
				isDragged = false;             
				LoginJFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));   				     
			}          
			public void mousePressed(java.awt.event.MouseEvent e) {             
				int y = e.getY();
				int x =  e.getX(); 

				quicklyPressed();
				tmp = new Point(x, y);
				if( y>-1 && y<titleHeight ) {
					borderStype=Cursor.MOVE_CURSOR;
					LoginJFrame.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));			
				}
				else {
					borderStype=0;
				}
	           	
			}    
		});       
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {           
			public void mouseDragged(java.awt.event.MouseEvent e) {
				int y = e.getY();
				int x =  e.getX(); 
				if(isDragged){               
					loc = new Point(LoginJFrame.this.getLocation().x + x - tmp.x,LoginJFrame.this.getLocation().y + y - tmp.y);              
					if( Cursor.MOVE_CURSOR == borderStype ){		
						LoginJFrame.this.setLocation(loc);
					}
				}           
			}       
			public void mouseMoved(MouseEvent e){}
		});
	}
	
	private void quicklyPressed(){
		delay++;
		Timer t = new Timer();
		TimerTask task = new TimerTask(){
			public void run(){
				delay=0;
			}
		};
		if(delay<2){
			isDragged = true;
		} 
		t.schedule(task, 100);
	}
	
	public void setTitleHeight(int h) {
		titleHeight=h;
	}	
	public JPanel getMyPanel(){
		return secondPanel;
	}
	public void setTitleBar(String imgPath,String title) {
		titleImg.setIcon(new ImageIcon(imgPath));
		titleWord.setText(title);
	}
	public int getMyHeight() {
		return myHeight;
	}
	public int getMyWidth() {
		return myWidth;
	}
	public int getTitleHeight() {
		return titleHeight;
	}
}

@SuppressWarnings("serial")
class TitleJButton extends JButton{
	public TitleJButton(String str){
		this.setOpaque(false);
		this.setBorderPainted(false);
		setFocusPainted(false);
		this.setBackground(null);
		this.setIcon(new ImageIcon("src/main/resources/button_titlebar/btn_"+str+".png"));
	}
}