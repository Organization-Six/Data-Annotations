package Main;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import Model.Chart;
import Model.Comment;
import Model.CommentBank;
import Model.Label;
import Model.LabelBank;
import Model.LogAspect;
import Model.Spider;
import View.IndexView;
//import View.LoginView;

@Component
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ArrayList<String> comment;
//		comment = Spider.Load("SZ002304");
//		for(String temp:comment) {
//			System.out.println(temp);
//		}
		
		
		final CommentBank cmtBank = new CommentBank();
		final LabelBank labBank = new LabelBank();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LogAspect.Log("Main.Main",  "Main trycatch");
				
				try {
					//cmtBank.getComment().add(new Comment("无数据"," "," "));
					IndexView index = new IndexView(cmtBank, labBank);
					index.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					index.setVisible(true);
					
					//index.setIsCorrect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}

}
