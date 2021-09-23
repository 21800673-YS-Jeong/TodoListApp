package com.todo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[항목 추가]\n" + "제목 > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.println("이미 사용 중인 제목입니다.");
			return;
		}
		
		sc.nextLine();
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[항목 삭제]\n" + "제목 > ");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("삭제되었습니다.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[항복 변경]\n" + "제목 > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("해당 제목이 존재하지 않습니다.");
			return;
		}

		System.out.print("새로운 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 사용 중인 제목입니다.");
			return;
		}
		
		sc.nextLine();
		System.out.print("새로운 내용 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("항목이 변경되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());;
		}
	}

	public static void loadList(TodoList l, String file) throws IOException {
		String s;
		int i = 0;
		
		File f = new File(file);
		if(!f.exists()) {
			f.createNewFile();
			System.out.println(file + " 파일이 없습니다.");
			return;
		}
		
		BufferedReader bf = new BufferedReader(new FileReader(f));
		
		while ((s = bf.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(s, "##");
			TodoItem t = new TodoItem(token.nextToken(), token.nextToken());
			t.setCurrent_date(token.nextToken());
			l.addItem(t);
			i++;
		}
		
		System.out.println(i + "개의 항목을 읽었습니다.");
	}

	public static void saveList(TodoList l, String file) throws IOException {
		FileWriter fw = new FileWriter(file);
		
		for(TodoItem item : l.getList()) {
			fw.write(item.toSaveString());
		}
		System.out.println("모든 데이터가 저장되었습니다.");
		fw.close();
	}
}
