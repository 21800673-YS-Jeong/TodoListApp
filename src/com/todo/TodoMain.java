package com.todo;

import java.io.IOException;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() throws IOException {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "todolist.txt");
		
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			String keyWord;
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "find":
				keyWord = sc.next().trim();
				TodoUtil.findItem(l, keyWord);
				break;
				
			case "find_cate":
				keyWord = sc.next().trim();
				TodoUtil.findCate(l, keyWord);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				TodoUtil.listCate(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("제목순으로 정렬을 완료하였습니다!.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("제목역순으로 정렬을 완료하였습니다!.");
				isList = true;
				break;
				
			case "ls_date_asc":
				l.sortByDate();
				System.out.println("날짜순으로 정렬을 완료하였습니다!.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("날짜역순으로 정렬을 완료하였습니다!.");
				isList = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("정확한 명령어를 입력하세요. (도움말 - help)");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
