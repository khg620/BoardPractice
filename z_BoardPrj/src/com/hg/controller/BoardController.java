package com.hg.controller;

import java.util.ArrayList;

import com.hg.dao.FileUtil;
import com.hg.model.vo.Board;

public class BoardController {
	String filePath = ".\\data\\";
	String fileName = "boards.dat";

	FileUtil fu = new FileUtil();
	
	//DAO에서 파일 읽어오기
	public ArrayList<Board> readData() {
		ArrayList<Board> list = new ArrayList<Board>();
		ArrayList<Object> readData = fu.readData(fileName);
		
		// 1. Board로 형변환
		if (readData != null) {
			for (Object o : readData) {
				if (o instanceof Board) {
					Board tmp = (Board) o;
					list.add(tmp);
				}
			}
		}
		//가데이터 생성
		//list.add(new Board("a","aaa","aa","20210101"));
		return list;
	}
	
	//view에 이차원 배열 리턴
	public String[][] getBoardData(String searchWord) {
		
		return listToArray(readData());
	}

  //이차원 배열에 옮겨닮기
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
		return new String[]{"글번호","제목","내용", "작성자","작성날짜"};
	}

	public boolean savePost(Board Post) {
		//1. 전체 읽어오기
		ArrayList<Board> list = readData();
		//2. 객체 추가하기
		list.add(Post);
		//3. 저장하기
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
