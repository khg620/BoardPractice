package com.hg.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.hg.controller.BoardController;
import com.hg.model.vo.Board;

public class Post extends JPanel implements ActionListener {

	private JPanel crudPanel;
	private JLabel lblTitle, lblName, lblDate;
	private JTextField txtTitle, txtName, txtDate;
	private JTextArea txtPost;
	private JButton btnUpload, btnModify, btnDelete;
	
	private Font lblFont = new Font("빙그레체",Font.BOLD,14);
	private Font txtFont = new Font("빙그레체",Font.PLAIN,14);
	
	private Color green = new Color(106, 202, 222), pink = new Color(250, 186, 185);
	
	BoardController bc = new BoardController();
	
	public Post(JFrame userFrame) {
		userFrame.setTitle("글쓰기");
		Toolkit icon = Toolkit.getDefaultToolkit();
		Image img = icon.getImage("Images/post.png");
		userFrame.setIconImage(img);
		setBackground(pink);
		setLayout(null);
		
		lblTitle = new JLabel("제목");
		lblTitle.setBounds(20, 20, 30, 20);
		lblTitle.setFont(lblFont);
		
		txtTitle = new JTextField(10);
		txtTitle.setBounds(60, 20, 150, 20);
		txtTitle.setFont(txtFont);
		
		lblName = new JLabel("작성자");
		lblName.setBounds(220, 20, 30, 20);
		lblName.setFont(lblFont);
		
		txtName = new JTextField(10);
		txtName.setBounds(260, 20, 100, 20);
		txtName.setFont(txtFont);
		
		lblDate = new JLabel("날짜");
		lblDate.setBounds(370, 20, 30, 20);
		lblDate.setFont(lblFont);
		
		txtDate = new JTextField(10);
		txtDate.setBounds(410, 20, 100, 20);
		txtDate.setFont(txtFont);
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		txtDate.setText(sf.format(date));
		
		txtPost = new JTextArea();
		txtPost.setBounds(20, 60, 750, 350);
		txtPost.setFont(txtFont);	
		
		add(lblTitle);
		add(txtTitle);
		add(lblName);
		add(txtName);
		add(lblDate);
		add(txtDate);
		add(txtPost);
		userFrame.add(getCrudPanel(),"South");
	}
	

	private JPanel getCrudPanel() { //사용자 구분하여 수정/삭제 전환되도록
		crudPanel = new JPanel();
		crudPanel.setSize(800,50);
		crudPanel.setBackground(pink);
		btnUpload = new JButton("등록");
		btnUpload.setSize(50,40);
		btnUpload.setBackground(green);
		btnUpload.setBorder(null);
		btnUpload.addActionListener(this);
		
		crudPanel.add(btnUpload);
		return crudPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUpload || e.getSource() == btnModify) {

			int result = JOptionPane.showConfirmDialog(null, "등록하시겠습니까?", "등록", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				bc.savePost(new Board(txtTitle.getText(),txtName.getText(),txtPost.getText(),txtDate.getText()));
			}
		}
	}
}
