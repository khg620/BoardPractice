package com.hg.view.frame;

import javax.swing.JFrame;

import com.hg.view.SignUp;

public class UserFrame extends JFrame {

	public UserFrame() {
		setTitle("�Խ���");
		setSize(800,500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.getContentPane().add(new SignUp(this));
		
		setVisible(true);
	}


}
