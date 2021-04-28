package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import View.LoginView;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView login = new LoginView();
					login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					login.setVisible(true);
					
					login.setIsCorrect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
