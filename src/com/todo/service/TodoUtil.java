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
		
		String title, desc, cate, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[�׸� �߰�]\n" + "ī�װ� > ");
		cate = sc.next().trim();
		
		System.out.print("���� > ");
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.println("�̹� ��� ���� �����Դϴ�.");
			return;
		}
		
		sc.nextLine();
		System.out.print("���� > ");
		desc = sc.nextLine().trim();
		
		System.out.print("���� ��¥ > ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(cate, title, desc, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		listAll(l);
		
		System.out.print("\n[�׸� ����]\n" + "������ �׸��� �Ϸù�ȣ�� �Է��ϼ��� > ");
		int num = sc.nextInt();
		
		if (num <= l.getList().size() && num > 0 ) {
			String check;
			System.out.println(num + ". " + l.getList().get(num - 1).toString());
			System.out.print("�� �׸��� �����Ͻðڽ��ϱ�? (y/n) > ");
			
			do {
				check = sc.next();
				
				switch(check) {
					case "y":
						l.deleteItem(num - 1);
						System.out.println("�����Ǿ����ϴ�.");
						break;
					
					case "n":
						break;
				
					default:
						System.out.print("�����Ͻ÷��� \"y\", �������� �����÷��� \"n\"�� �Է��ϼ���. > ");
						break;
				}
			}while(!(check.equals("y") || check.equals("n")));
		}
		
		else {
			System.out.println("�ش� �Ϸù�ȣ�� �׸��� ã�� �� �����ϴ�.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		listAll(l);
		
		System.out.print("\n[�׺� ����]\n" + "������ �׸��� �Ϸù�ȣ�� �Է��ϼ��� > ");
		int num = sc.nextInt();
		
		if (num <= l.getList().size() && num > 0 ) {
			System.out.println(num + ". " + l.getList().get(num - 1).toString());
			l.deleteItem(num - 1);
		}
		else {
			System.out.println("�ش� �Ϸù�ȣ�� �׸��� ã�� �� �����ϴ�.");
			return;
		}
		
		System.out.print("���ο� ī�װ� > ");
		String new_cate = sc.next().trim();
		
		System.out.print("���ο� ���� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� ��� ���� �����Դϴ�.");
			return;
		}
		
		sc.nextLine();
		System.out.print("���ο� ���� > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("���ο� ���� ��¥ > ");
		String new_due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(new_cate, new_title, new_description, new_due_date);
		l.addItem(t);
		
		System.out.println("�׸��� ����Ǿ����ϴ�.");
	}
	
	public static void findItem(TodoList l, String keyWord) {
		int i = 0;
		
		for(TodoItem item : l.getList()) {
			if(item.getTitle().contains(keyWord) || item.getDesc().contains(keyWord)) {
				System.out.println((l.indexOf(item) + 1) + ". " + item.toString());
				i++;
			}
		}
		
		if(i == 0) System.out.println("�ش� Ű���带 �����ϴ� �׸��� ã�� �� �����ϴ�.");
		else System.out.println("�� " + i + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void findCate(TodoList l, String keyWord) {
		int i = 0;
		
		for(TodoItem item : l.getList()) {			
			if(item.getCate().contains(keyWord)) {
				System.out.println((l.indexOf(item) + 1) + ". " + item.toString());
				i++;
			}
		}
		
		if(i == 0) System.out.println("�ش� Ű���带 �����ϴ� �׸��� ã�� �� �����ϴ�.");
		else System.out.println("�� " + i + "���� �׸��� ã�ҽ��ϴ�.");
	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���, �� " + l.getList().size() + "��]");
		for (TodoItem item : l.getList()) {
			System.out.println((l.indexOf(item) + 1) + ". " + item.toString());
		}
	}
	
	public static void listCate(TodoList l) {
		HashSet<String> hs = new HashSet<String>();
		int i = 0;
		
		for (TodoItem item : l.getList()) {
			hs.add(item.getCate());
		}
		
		for (String cate : hs) {
		    System.out.print(cate);
		    i++;
		    
		    if(i != hs.size()) {
		    	System.out.print(" / ");
		    }
		}
		
		System.out.println(" �� " + i + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
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
			TodoItem t = new TodoItem(token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken());
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
