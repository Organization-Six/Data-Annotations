package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import View.custom.LoginJFrame;
import View.custom.MessageDialog;

public class RegisterView extends LoginJFrame {
	
	private int myHeight,myWidth,titleHeight;
	
	private JTextField userNameTf;
	private JPasswordField pwdTf;
	private JLabel userImg,pwdImg;
	private JPanel contentPane;
	private JButton registerBut;
	private String registerUser,registerPwd;
	
	private boolean isCompliance=true;	//�û����������Ƿ���ϱ�׼(��ʱֱ����Ϊtrue)

	public static void main(String[] args) {
		RegisterView a = new RegisterView();
		a.setVisible(true);
	}
	
	public RegisterView() {
		super();
		// TODO �Զ����ɵĹ��캯�����
		setTitle("���ݱ�ע"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShowLabelView.class.getResource("src/main/resources/image/img_dataannotation.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();		
		createInput();
		createButton();
	}

	private void init() {
		this.setTitleBar("src/main/resources/image/img_dataannotation.png","���ݱ�ע");
		myHeight=this.getMyHeight();
		myWidth=this.getMyWidth();
		titleHeight=this.getTitleHeight();
		contentPane = this.getMyPanel();
		contentPane.setLayout(null);
		//��Ļ�м�
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
		String placeHolder1="�û���";
		String placeHolder2="����";
		MatteBorder border = new MatteBorder(0, 0, 1, 0, new Color(121, 121,121));
		
		userImg = new JLabel(new ImageIcon("src/main/resources/image/img_user.png"));
		userImg.setBounds(myWidth/6,(myHeight-titleHeight)/5,lineHeight,lineHeight);
		contentPane.add(userImg);
		
		userNameTf = new JTextField(placeHolder1);
		userNameTf.setBounds(myWidth/6+lineHeight+10, (myHeight-titleHeight)/5, lineWidth, lineHeight);
		userNameTf.setFont(new Font("����",0,16));
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
				registerUser=userNameTf.getText();
				// TODO �Զ����ɵķ������
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
		registerBut=new JButton("����ע��");
		registerBut.setFont(new Font("����",Font.BOLD,17));
		registerBut.setForeground(new Color(255,255,255));
		registerBut.setBackground(new Color(22,155,213));
		registerBut.setBounds(myWidth/6,3*(myHeight-titleHeight)/4,4*myWidth/6,40);
		registerBut.setBorder(new MatteBorder(0, 0, 1, 0, new Color(22,155,213)));
		registerBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				if(isCompliance) {
					MessageDialog dialog=new MessageDialog(RegisterView.this.getLocation().x+65,RegisterView.this.getLocation().y+200
							,250,170,"ע��ɹ�");
					dialog.setListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO �Զ����ɵķ������
							RegisterView.this.dispose();
							LoginView loginView = new LoginView();
							loginView.setLocation(RegisterView.this.getLocation());
							loginView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							loginView.setVisible(true);
							dialog.display();
						}
					});
					dialog.setVisible(true);										
				}
				//�Ի���
				else {
					MessageDialog dialog=new MessageDialog(RegisterView.this.getLocation().x+65,RegisterView.this.getLocation().y+200
							,250,170,"ע��ʧ��");
					dialog.setVisible(true);
				}
			}
		});
		contentPane.add(registerBut);
	}
	
	public String getRegisterUser() {
		registerUser=userNameTf.getText();
		return registerUser;
	}
	
	public String getRegisterPwd() {
		registerPwd=String.valueOf(pwdTf.getPassword());
		return registerPwd;
	}

	public void setIsCompliance(boolean isCompliance) {
		this.isCompliance = isCompliance;
	}
}
