package com.hg.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.hg.controller.UserController;
import com.hg.model.vo.User;
import com.hg.view.frame.BoardListFrame;

public class Login extends JPanel {

	// private JPanel loginPanel;
	private JLabel lblTitle, lblId;
	private JTextField txtId;
	private JButton loginBtn, signUpBtn;

	private Font titleFont = new Font("���׷�ü", Font.BOLD, 30), lblFont = new Font("���׷�ü", Font.BOLD, 15);
	private Color pink = new Color(250, 186, 185), green = new Color(106, 202, 222);

	UserController uc = new UserController();

	public Login(JFrame userFrame) {
		userFrame.setTitle("Log in");
		//setSize(800, 500);
		setLayout(null);
		setBackground(pink);

		Toolkit icon = Toolkit.getDefaultToolkit();
		Image iconImg = icon.getImage("Images/signin.png");
		userFrame.setIconImage(iconImg);

		lblTitle = new JLabel("�ݰ����ϴ�");
		lblTitle.setBounds(100, 300, 300, 40);
		lblTitle.setFont(titleFont);

		lblId = new JLabel("���̵�");
		lblId.setBounds(330, 380, 60, 20);
		lblId.setFont(lblFont);

		txtId = new JTextField(10);
		txtId.setBounds(400, 380, 200, 20);

		loginBtn = new JButton("Log in");
		loginBtn.setBounds(400, 410, 200, 20);
		loginBtn.setBorder(null);
		loginBtn.setBackground(green);

		signUpBtn = new JButton("Sign Up");
		signUpBtn.setBounds(610, 410, 200, 20);
		signUpBtn.setBorder(null);
		signUpBtn.setBackground(green);

		add(lblTitle);
		add(lblId);
		add(txtId);
		add(loginBtn);
		add(signUpBtn);

		loginBtn.addActionListener(new ActionListener() { // �͸�Ŭ���� ���

			@Override
			public void actionPerformed(ActionEvent e) {

				User user = uc.getUserInfo(txtId.getText());

				if (user != null) {
					userFrame.getContentPane().removeAll();
					userFrame.getContentPane().add(new BoardList(userFrame));
					userFrame.repaint();
					userFrame.setVisible(true);
				} else
					JOptionPane.showMessageDialog(null, "�α��� ������ Ȯ���ϼ���");

			}
		});

		signUpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				userFrame.getContentPane().removeAll();
				userFrame.getContentPane().add(new SignUp(userFrame));
				userFrame.repaint();
				userFrame.setVisible(true);

			}
		});
	}
}
