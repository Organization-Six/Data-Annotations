package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import View.custom.LoginJFrame;
import View.custom.MessageDialog;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class LoginView extends LoginJFrame {
	
	private int myHeight,myWidth,titleHeight;
	
	private JTextField userNameTf;
	private JPasswordField pwdTf;
	private JLabel userImg,pwdImg;
	private JPanel contentPane;
	private JButton registerBut,loginBut;
	
	private String loginUser,loginPwd;
	
	private boolean isCorrect;	//用户名与密码是否正确
	
	public LoginView() {
		super();
		setTitle("数据标注"); 
		setIconImage(new ImageIcon("src/main/resources/image/img_dataannotation.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();		
		createInput();
		createButton();
	}

	private void init() {
		this.setTitleBar("src/main/resources/image/img_dataannotation.png","数据标注");
		
		myHeight=this.getMyHeight();
		myWidth=this.getMyWidth();
		titleHeight=this.getTitleHeight();
		contentPane = getMyPanel();
		contentPane.setLayout(null);
		//屏幕中间
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension frameSize = getSize();
	    if (frameSize.height > screenSize.height) {
	        frameSize.height = screenSize.height;
	    }
	    if (frameSize.width > screenSize.width) {
	        frameSize.width = screenSize.width;
	    }
	    setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);	   
	}
	
	private void createInput() {
		int lineHeight=32;
		int lineWidth=4*myWidth/6-lineHeight-10;
		String placeHolder1="用户名";
		String placeHolder2="密码";
		MatteBorder border = new MatteBorder(0, 0, 1, 0, new Color(121, 121,121));
		
		userImg = new JLabel(new ImageIcon("src/main/resources/image/img_user.png"));
		userImg.setBounds(myWidth/6,(myHeight-titleHeight)/5,lineHeight,lineHeight);
		contentPane.add(userImg);
		
		userNameTf = new JTextField(placeHolder1);
		userNameTf.setBounds(myWidth/6+lineHeight+10, (myHeight-titleHeight)/5, lineWidth, lineHeight);
		userNameTf.setFont(new Font("宋体",0,16));
		userNameTf.setForeground(Color.GRAY);
		userNameTf.setBorder(border);
		userNameTf.addFocusListener(new FocusListener (){
			 @Override
			 public void focusGained(FocusEvent e) {
				 userNameTf.setForeground(Color.BLACK);
				 if(placeHolder1.equals(userNameTf.getText())) {
					 userNameTf.setText("");					 
				 }
			 }
			@Override
			public void focusLost(FocusEvent arg0) {
				loginUser=userNameTf.getText();
				// TODO 自动生成的方法存根
				if(0 == userNameTf.getText().length()) {
					userNameTf.setForeground(Color.GRAY);
					userNameTf.setText(placeHolder1);				
				}				
			}
		});
		contentPane.add(userNameTf);
				
		pwdImg = new JLabel(new ImageIcon("src/main/resources/image/img_pwd.png"));
		pwdImg.setBounds(myWidth/6,(myHeight-titleHeight)/3+20,lineHeight,lineHeight);
		contentPane.add(pwdImg);
		
		pwdTf = new JPasswordField();
		pwdTf.setToolTipText("\u5BC6\u7801");
		pwdTf.setBounds(myWidth/6+lineHeight+10, (myHeight-titleHeight)/3+20, lineWidth, lineHeight);
		pwdTf.setForeground(Color.BLACK); 
		pwdTf.setBorder(border);
		contentPane.add(pwdTf);
	}
	
	private void createButton() {
		registerBut=new JButton("注册账号");
		registerBut.setFont(new Font("宋体",0,15));
		registerBut.setForeground(new Color(22,155,213));
		registerBut.setBackground(Color.white);
		registerBut.setBounds(3*myWidth/4-50,(myHeight-titleHeight)/3+70,100,30);
		registerBut.setBorder(null);
		
		registerBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				dispose();
				RegisterView registerView = new RegisterView();
				registerView.setLocation(LoginView.this.getLocation());
				registerView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				registerView.setVisible(true);
			}
		});
		contentPane.add(registerBut);

		loginBut=new JButton("登录");
		loginBut.setFont(new Font("宋体",Font.BOLD,17));
		loginBut.setForeground(new Color(255,255,255));
		loginBut.setBackground(new Color(22,155,213));
		loginBut.setBounds(myWidth/6,3*(myHeight-titleHeight)/4,4*myWidth/6,40);
		loginBut.setBorder(new MatteBorder(0, 0, 1, 0, new Color(22,155,213)));
		loginBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				//判断用户名密码正确否
				if(isCorrect) {
					dispose();
					IndexView indexView = new IndexView();
					Point point=new Point(LoginView.this.getLocation().x-Math.abs(getBounds().width/2-indexView.getBounds().width/2),
							LoginView.this.getLocation().y+Math.abs(getBounds().height/2-indexView.getBounds().height/2));
					indexView.setLocation(point);
					indexView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					indexView.setVisible(true);
				}
				else {
					MessageDialog dialog=new MessageDialog(LoginView.this.getLocation().x+65,LoginView.this.getLocation().y+200
							,250,170,"用户名或密码不正确");
					dialog.setVisible(true);
				}
			}
		});
		contentPane.add(loginBut);
	}
	
	public String getLoginUser() {
		loginUser=userNameTf.getText();
		return loginUser;
	}
	
	public String getLoginPwd() {
		loginPwd=String.valueOf(pwdTf.getPassword());
		return loginPwd;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}
