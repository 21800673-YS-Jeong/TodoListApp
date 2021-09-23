package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<ToDoList 관리 명령어 사용법>");
        System.out.println("1. add - 항목 추가");
        System.out.println("2. del - 항목 삭제");
        System.out.println("3. edit - 항목 변경");
        System.out.println("4. ls - 전체 목록");
        System.out.println("5. ls_name_asc - 항목 제목순 정렬");
        System.out.println("6. ls_name_desc - 항목 제목역순 정렬");
        System.out.println("7. ls_date - 항목 날짜순 정렬");
        System.out.println("8. exit - 종료");
    }
    
    public static void prompt() {
    	System.out.print("\n명령어 입력 > ");
    }
}
