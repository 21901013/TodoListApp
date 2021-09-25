package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		Menu.displaymenu();
		TodoUtil.loadList(l,"todolist.txt");
		
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			
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
				
			case "ls":
				System.out.print("[ Entire List, Total ");
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("Sorting complete");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("Sorting complete");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
		 		System.out.println("Sorting complete");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("Sorting complete");
				isList = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "find":
				String key_word1 = sc.next();
				TodoUtil.findItem(l, key_word1);
				break;
				
			case "find_cate":
				String key_word2 = sc.next();
				TodoUtil.find_cateItem(l,key_word2);
				break;
				
			case "ls_cate":
				TodoUtil.listCategory(l);
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("Please enter one of the command mentioned above (see menu - help)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l,"todolist.txt");
	}
}