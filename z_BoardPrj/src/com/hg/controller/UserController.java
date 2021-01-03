package com.hg.controller;

import java.util.ArrayList;

import com.hg.dao.FileUtil;
import com.hg.model.vo.User;

public class UserController {
	FileUtil fu = new FileUtil();
	String path = ".\\data\\";
	String fileName = "userData";
	
	//���Ͽ��� �б�, UserŸ������ ��ȯ
	public ArrayList<User> readUserData() {
		//1. ����ȯ �ؼ� ���� ArrayList
		ArrayList<User> list = new ArrayList<User>();
		//2. ���� �о����
		ArrayList<Object> readList = fu.readData(fileName);
	
		//3. �ݺ��� ���� ��ü ����ȯ
		if (readList != null) {
			for (int i = 0; i < readList.size(); i++) {
				list.add(i, (User) readList.get(i));
			}
		}
		return list;
	}

	//ȭ��ܿ��� ���� ������ ���� ��
	public User getUserInfo(String text) {
		//1. ��ȯ�� ���� ��ü
		User user = null;
		//2. �о��+����ȯ �� ���� ���
		ArrayList<User> list = readUserData();
		
		//3. �ݺ��� ���� �ش� ���� ������
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(text) || text.equals("")) {
				user = list.get(i);
			}
		}
		return user;
	}

	//ȸ������ �� ��ĭ üũ
	public boolean checkValue(String id, String pwd, String name, String email) {
		boolean result = false;
		if(id.equals("") || pwd.equals("") || name.equals("") || email.equals("")) {
			result = false;
		} else
			result = true;
		return result;
	}
	
	//ȸ������ �� �ߺ� �˻�
	public boolean checkUser(String txtId) {
		//1. ��ȯ�� �� ����Ÿ�� ����
		boolean result = false;
		//2. ���� �о����
		ArrayList<User> list = readUserData();
		
		if (!list.isEmpty()) { //����Ʈ�� ����� �� ���� ��
			// 3. for������ Ȯ��
			for (User ul : list) {
				if(ul.getId().equals(txtId))
					return result = false; //�ߺ� ������ �ٷ� ����
				else
					result = true;
			}
		} else if(list.isEmpty()) //����� ��
			result = true;
		return result;
	}

	//ȸ������ ����(���� ����)
	public void saveUserData(User user) {
		//1.  ��ü��� �о����
		ArrayList<User> list = readUserData();
		//2. �߰��ϱ�
		list.add(user);
		//3.Object������ ����ȯ
		ArrayList<Object> saveList = arrayUserToObject(list);
		//4. ȸ�� �߰�
		fu.saveData(saveList, path + fileName);
	}

	//���Ϸ� ���� ���� Object�� ����ȯ
	private ArrayList<Object> arrayUserToObject(ArrayList<User> user) {
		//1. Object�� ����Ʈ �غ�
		ArrayList<Object> saveList = new ArrayList<Object>();
		//2. for�� ���� ����ȯ
		for(User u : user) {
			saveList.add(u);
		}
		return saveList;
	}

	
	
}
