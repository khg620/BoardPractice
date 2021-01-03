package com.hg.controller;

import java.util.ArrayList;

import com.hg.dao.FileUtil;
import com.hg.model.vo.User;

public class UserController {
	FileUtil fu = new FileUtil();
	String path = ".\\data\\";
	String fileName = "userData";
	
	//파일에서 읽기, User타입으로 변환
	public ArrayList<User> readUserData() {
		//1. 형변환 해서 담을 ArrayList
		ArrayList<User> list = new ArrayList<User>();
		//2. 파일 읽어오기
		ArrayList<Object> readList = fu.readData(fileName);
	
		//3. 반복문 통해 전체 형변환
		if (readList != null) {
			for (int i = 0; i < readList.size(); i++) {
				list.add(i, (User) readList.get(i));
			}
		}
		return list;
	}

	//화면단에서 유저 정보를 얻어올 때
	public User getUserInfo(String text) {
		//1. 반환할 유저 객체
		User user = null;
		//2. 읽어온+형변환 된 유저 목록
		ArrayList<User> list = readUserData();
		
		//3. 반복문 통해 해당 유저 꺼내기
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(text) || text.equals("")) {
				user = list.get(i);
			}
		}
		return user;
	}

	//회원가입 시 빈칸 체크
	public boolean checkValue(String id, String pwd, String name, String email) {
		boolean result = false;
		if(id.equals("") || pwd.equals("") || name.equals("") || email.equals("")) {
			result = false;
		} else
			result = true;
		return result;
	}
	
	//회원가입 시 중복 검사
	public boolean checkUser(String txtId) {
		//1. 반환할 빈 유저타입 변수
		boolean result = false;
		//2. 파일 읽어오기
		ArrayList<User> list = readUserData();
		
		if (!list.isEmpty()) { //리스트에 저장된 게 있을 때
			// 3. for문으로 확인
			for (User ul : list) {
				if(ul.getId().equals(txtId))
					return result = false; //중복 있으면 바로 리턴
				else
					result = true;
			}
		} else if(list.isEmpty()) //비었을 때
			result = true;
		return result;
	}

	//회원가입 실행(파일 저장)
	public void saveUserData(User user) {
		//1.  전체목록 읽어오기
		ArrayList<User> list = readUserData();
		//2. 추가하기
		list.add(user);
		//3.Object형으로 형변환
		ArrayList<Object> saveList = arrayUserToObject(list);
		//4. 회원 추가
		fu.saveData(saveList, path + fileName);
	}

	//파일로 저장 위해 Object로 형변환
	private ArrayList<Object> arrayUserToObject(ArrayList<User> user) {
		//1. Object형 리스트 준비
		ArrayList<Object> saveList = new ArrayList<Object>();
		//2. for문 돌며 형변환
		for(User u : user) {
			saveList.add(u);
		}
		return saveList;
	}

	
	
}
