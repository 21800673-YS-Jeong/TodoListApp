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
		
		System.out.print("\n[�׸� �߰�]\n" + "���� > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.println("�̹� ��� ���� �����Դϴ�.");
			return;
		}
		
		sc.nextLine();
		System.out.print("���� > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[�׸� ����]\n" + "���� > ");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("�����Ǿ����ϴ�.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[�׺� ����]\n" + "���� > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�ش� ������ �������� �ʽ��ϴ�.");
			return;
		}

		System.out.print("���ο� ���� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� ��� ���� �����Դϴ�.");
			return;
		}
		
		sc.nextLine();
		System.out.print("���ο� ���� > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�׸��� ����Ǿ����ϴ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���]");
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
			System.out.println(file + " ������ �����ϴ�.");
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
		
		System.out.println(i + "���� �׸��� �о����ϴ�.");
	}

	public static void saveList(TodoList l, String file) throws IOException {
		FileWriter fw = new FileWriter(file);
		
		for(TodoItem item : l.getList()) {
			fw.write(item.toSaveString());
		}
		System.out.println("��� �����Ͱ� ����Ǿ����ϴ�.");
		fw.close();
	}
}
