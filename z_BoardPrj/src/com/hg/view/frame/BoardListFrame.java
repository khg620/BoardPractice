package com.hg.view.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.hg.controller.BoardController;

public class BoardListFrame extends JPanel implements ActionListener {
	
	JPanel titlePanel, btnPanel;
	JLabel lblTitle, lblSearch;
	JTextField txtSearchWord;
	JButton searchBtn, postBtn;
	DefaultTableModel model;
	JTable table;
	JScrollPane scroll;
	
	Font font = new Font("빙그레체", Font.BOLD, 15);
	Color green = new Color(106, 202, 222), pink = new Color(250, 186, 185);
	
	BoardController bc = new BoardController();
	
	public JPanel getTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setSize(800,50);
		titlePanel.setBackground(green);
		
		lblTitle = new JLabel("자유게시판");
		lblTitle.setFont(font);
		lblTitle.setBounds(10, 10, 100, 20);
		
		lblSearch = new JLabel("제목");
		lblSearch.setFont(font);
		lblSearch.setBounds(500,10,30,20);
		
		txtSearchWord = new JTextField(10);
		txtSearchWord.setFont(font);
		txtSearchWord.setBounds(550, 10, 100, 20);
		
		searchBtn = new JButton("검색");
		searchBtn.setFont(font);
		searchBtn.setBounds(660, 10, 50, 20);
		searchBtn.setBorder(null);
		searchBtn.setBackground(pink);
		searchBtn.addActionListener(this);
		
		titlePanel.add(lblTitle);
		titlePanel.add(lblSearch);
		titlePanel.add(txtSearchWord);
		titlePanel.add(searchBtn);
		
		return titlePanel;
	}
	
	public JScrollPane getTablePanel() {
		
		String[] title = bc.getBoardTitle();
		String[][] data = bc.getBoardData("");
		model = new DefaultTableModel(data, title);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		return scroll;
	}
	
	public JPanel getBtnPanel() {
		btnPanel = new JPanel();
		
		btnPanel.setSize(800,50);
		btnPanel.setBackground(green);
		
		postBtn = new JButton("글쓰기");
		postBtn.setBorder(null);
		postBtn.setBackground(pink);
		postBtn.addActionListener(this);
		
		btnPanel.add(postBtn);
		
		return btnPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchBtn) {
			searchData(txtSearchWord.getText());
		} else if(e.getSource() == postBtn) {
			
		}
	}

	private void searchData(String searchWord) {
		String[][] searchResult = bc.getBoardData(searchWord);
		if(model.getRowCount() > 0) {
			model.removeRow(0);
		}
		for(int i = 0; i < searchResult.length; i++) {
			model.insertRow(0, searchResult[i]);
		}
	}

}
