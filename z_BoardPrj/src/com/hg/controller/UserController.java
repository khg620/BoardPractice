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

	//회원가입 시 중복 검사
	public boolean checkValue(String txtId) {
		System.out.println("checkValue가 받은 아이디 : " + txtId);
		//1. 반환할 빈 유저타입 변수
		boolean result = false;
		//2. 파일 읽어오기
		ArrayList<User> list = readUserData();
		System.out.println("읽어온 내용 : " + list);
		
		if (!list.isEmpty()) { //리스트에 저장된 게 있을 때
			// 3. for문으로 확인
			for (User ul : list) {
				if(ul.getId().equals(txtId)) {
					System.out.println("읽어온 파일에서 꺼낸 id, 입력받은 id 중복 " + ul.getId().equals(txtId) + ", result : " + result);
					return result = false;}
				else {
					result = true;
					System.out.println("읽어온 파일에서 꺼낸 id, 입력받은 id 중복 X, result : " + result);
				}
			}
		} else if(list.isEmpty()) //비었을 때
			result = true;
		System.out.println("checkValue 에서 읽어온 파일과 확인 결과 " + result);
		return result;
	}

	//회원가입 실행(파일 저장)
	public void saveUserData(User user) {
		//1.  전체목록 읽어오기
		ArrayList<User> list = readUserData();
		//2. 추가하기
		list.add(user);
		System.out.println("회원가입 리스트 업뎃 : " + list);
		//3.Object형으로 형변환
		ArrayList<Object> saveList = arrayUserToObject(list);
		System.out.println("회원가입 실행에서 저장된 목록 + 입력받은 user 추가한 리스트" + saveList);
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
