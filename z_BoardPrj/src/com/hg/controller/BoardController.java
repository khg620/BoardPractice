package com.hg.controller;

import java.util.ArrayList;

import com.hg.dao.FileUtil;
import com.hg.model.vo.Board;

public class BoardController {
	String filePath = ".\\data\\";
	String fileName = "boards.dat";

	FileUtil fu = new FileUtil();
	
	//DAO���� ���� �о����
	public ArrayList<Board> readData() {
		ArrayList<Board> list = new ArrayList<Board>();
		ArrayList<Object> readData = fu.readData(fileName);
		
		// 1. Board�� ����ȯ
		if (readData != null) {
			for (Object o : readData) {
				if (o instanceof Board) {
					Board tmp = (Board) o;
					list.add(tmp);
				}
			}
		}
		//�������� ����
		//list.add(new Board("a","aaa","aa","20210101"));
		return list;
	}
	
	//view�� ������ �迭 ����
	public String[][] getBoardData(String searchWord) {
		
		return listToArray(readData());
	}

  //������ �迭�� �Űܴ��
	private String[][] listToArray(ArrayList<Board> readData) {
		
		String[][] boardData = new String[readData.size()][getBoardTitle().length];
		
		for(int i = 0; i < boardData.length; i++) {
			for(int j = 0; j < boardData[i].length; j++) {
				if(j == 0)
					boardData[i][j] = String.valueOf(readData.get(i).getPostNo());
				else if(j == 1)
					boardData[i][j] = readData.get(i).getTitle();
				else if(j == 2)
					boardData[i][j] = readData.get(i).getContent();
				else if(j == 3)
					boardData[i][j] = readData.get(i).getName();
				else if(j == 4)
					boardData[i][j] = readData.get(i).getPostingDate();
			}
		}
		return boardData;
	}

	public String[] getBoardTitle() {
		return new String[]{"�۹�ȣ","����","����", "�ۼ���","�ۼ���¥"};
	}

	public boolean savePost(Board Post) {
		//1. ��ü �о����
		ArrayList<Board> list = readData();
		//2. ��ü �߰��ϱ�
		list.add(Post);
		//3. �����ϱ�
		fu.saveData(BoardToObject(list), fileName);
		return false;
	}

	private ArrayList<Object> BoardToObject(ArrayList<Board> list) {
		ArrayList<Object> resultList = new ArrayList<Object>();
		for(Board b : list) {
			resultList.add(b);
		}
		return resultList;
	}

}
