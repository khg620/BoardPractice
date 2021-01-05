package com.hg.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.hg.model.vo.User;

public class FileUtil {

	String path = ".\\data\\";

	// 1. 데이터 읽기
	public ArrayList<Object> readData(String fileName) {
		// 담을 ArrayList 준비
		ArrayList<Object> list = null;

		// 가데이터//
		/*
		ArrayList<Object> tmplist = new ArrayList<Object>();
		tmplist.add(0, (Object) new User("aa", "aa", "aa", "aa"));
		tmplist.add(1, (Object) new User("bb", "bb", "bb", "bb"));
		tmplist.add(2, (Object) new User("cc", "cc", "cc", "cc"));
		System.out.println(tmplist);*/

		// 파일에서 읽어오기
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + fileName))) {

			if ((list = (ArrayList<Object>) ois.readObject()) != null) {
				return list;
			}

		} catch (FileNotFoundException e) {

			File folder = new File(path);
			folder.mkdirs();
			File file = new File(path + fileName);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 2. 데이터 저장
	public void saveData(ArrayList<Object> list, String text) {
		System.out.println("FileUtil : 받아온 어레이리스트 : " + list);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + text))) {

			oos.writeObject(list);

		} catch (FileNotFoundException e) {
			File folder = new File(path);
			folder.mkdirs();
			File file = new File(path + text);
			try {
				file.createNewFile();
			} catch (IOException e1) {

				System.out.println("파일낫파운드");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
