package com.hg.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hg.controller.UserController;
import com.hg.dao.FileUtil;
import com.hg.model.vo.User;
import com.hg.view.frame.UserFrame;

public class SignUp extends JPanel implements ActionListener {
	
	UserController uc = new UserController();

	private JLabel lblTitle, lblId, lblPwd, lblName, lblEmail;
	private JTextField txtId, txtPwd, txtName, txtEmail;
	private JButton resetBtn, signUpBtn;
	private Font title = new Font("빙그레체", Font.BOLD, 30), label = new Font("빙그레체",Font.BOLD,15);
	
	UserFrame userFrame = null;
	
	public SignUp(UserFrame userFrame) {
		this.userFrame = userFrame;
		userFrame.setTitle("Sign Up");
		try {
			userFrame.setIconImage(ImageIO.read(new File("images/sign-in.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBackground(Color.WHITE);
		setLayout(null);
		
		lblTitle = new JLabel("회원가입");
		lblTitle.setBounds(200, 100, 300, 40);
		lblTitle.setFont(title);
		lblId = new JLabel("아이디");
		lblId.setBounds(250, 150, 100, 20);
		lblId.setFont(label);
		lblPwd = new JLabel("비밀번호");
		lblPwd.setBounds(250, 180, 100, 20);
		lblPwd.setFont(label);
		lblName = new JLabel("이름");
		lblName.setBounds(250, 210, 100, 20);
		lblName.setFont(label);
		lblEmail = new JLabel("이메일");
		lblEmail.setBounds(250, 240, 100, 20);
		lblEmail.setFont(label);
		
		txtId = new JTextField(10);
		txtId.setBounds(400, 150, 100, 20);
		txtPwd = new JTextField(10);
		txtPwd.setBounds(400, 180, 100, 20);
		txtName = new JTextField(10);
		txtName.setBounds(400, 210, 100, 20);
		txtEmail = new JTextField(10);
		txtEmail.setBounds(400, 240, 100, 20);
		
		resetBtn = new JButton("초기화");
		resetBtn.setBounds(250, 300, 50, 20);
		resetBtn.setBorder(null);
		resetBtn.setBackground(Color.GRAY);
		signUpBtn = new JButton("회원가입");
		signUpBtn.setBounds(310, 300, 50, 20);
		signUpBtn.setBorder(null);
		signUpBtn.setBackground(Color.GRAY);
		
		resetBtn.addActionListener(this);
		signUpBtn.addActionListener(this);
		
		add(lblTitle);
		add(lblId);
		add(lblPwd);
		add(lblName);
		add(lblEmail);
		add(txtId);
		add(txtPwd);
		add(txtName);
		add(txtEmail);
		add(resetBtn);
		add(signUpBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == resetBtn) {
			txtId.setText("");
			txtPwd.setText("");
			txtName.setText("");
			txtEmail.setText("");
		} else if(e.getSource() == signUpBtn) {
			
			boolean result = uc.checkValue(txtId.getText());
			
			if(result == true) {
				User user = new User(txtId.getText(),txtPwd.getText(),txtName.getText(),txtEmail.getText());
				uc.saveUserData(user);
				removeAll();
				add(new Login(userFrame));
				repaint();
				
			} else {
				JOptionPane.showMessageDialog(null, "회원정보가 존재합니다");
			}
		}
	}
}
