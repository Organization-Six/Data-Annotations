package View.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class MessageDialog extends JFrame {

	private Container cp;
	private JPanel myRootPane,secondPanel;
	private String message="......";
	private JButton confirmBut;
	private JLabel word;
	private ActionListener listener;
	
	public MessageDialog(int x,int y,final int width,final int height,String str) {	
		this.message=str;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, width, height);
		
		this.setUndecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("restriction")
			public void run() {
				//Բ��
				com.sun.awt.AWTUtilities.setWindowShape(MessageDialog.this,new RoundRectangle2D.Float(0,0,width,height,12f,12f));   
				com.sun.awt.AWTUtilities.setWindowOpacity(MessageDialog.this, 1f);
			}   
		});
		
		cp=this.getContentPane();
		myRootPane = new JPanel();
		myRootPane.setBackground(new Color(245,245,245));
		
		myRootPane.setBorder (BorderFactory.createRaisedBevelBorder());
		myRootPane.setBorder(new MatteBorder(4, 4, 4, 4, new Color(35,35,35)));
		
		secondPanel =new JPanel();
		secondPanel.setBounds(2, 2, width-4, height-4);
		secondPanel.setBackground(Color.white);
		
		myRootPane.add(secondPanel);
		myRootPane.setLayout(null);
		cp.add(myRootPane);
		
		secondPanel.setLayout(null);
		confirmBut=new JButton("ȷ��");
		confirmBut.setFont(new Font("����",Font.BOLD,17));
		confirmBut.setForeground(new Color(255,255,255));
		confirmBut.setBackground(new Color(22,155,213));
		confirmBut.setBounds(width/4,13*height/20,width/2,height/5);
		confirmBut.setBorder(null);
		confirmBut.addActionListener(listener=new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				display();
			}
			
		});
		secondPanel.add(confirmBut);
		
		word = new JLabel(message,JLabel.LEFT);
		word.setFont(new Font("����", Font.BOLD, 18));
		word.setBounds(width/6,height/10,4*width/5,9*height/20);
		word.setBorder(null);
		secondPanel.add(word);
	}

	public void setListener(ActionListener listener) {
		this.listener = listener;
		confirmBut.addActionListener(listener);
	}
	public void display() {
		dispose();
	}
}
