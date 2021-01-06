package com.hg.view.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.hg.controller.BoardController;
import com.hg.view.Post;

public class BoardListFrame extends JPanel implements ActionListener, MouseListener {
	
	JFrame userFrame;
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
	
	public BoardListFrame(JFrame userFrame) {
		this.userFrame = userFrame;
	}
	
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
		model = new DefaultTableModel(data, title) {//익명클래스
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; //편집 불가
			}
		};
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		table.addMouseListener(this);
		
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
			userFrame.getContentPane().removeAll();
			userFrame.getContentPane().add(new Post(userFrame));
			userFrame.repaint();
			userFrame.setVisible(true);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() > 1) {
			int col = table.getSelectedColumn(); // 제목열 클릭 시에만 화면전환 할 용도
			int[] row = table.getSelectedRows(); // 나중에 삭제 기능 추가 시 이용
			
			//1열 클릭 시에만 포스팅 화면 열리게
			if(col == 1) {
				new Post(userFrame);
				userFrame.getContentPane().removeAll();
				userFrame.getContentPane().repaint();
				userFrame.setVisible(true);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
