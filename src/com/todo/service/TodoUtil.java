package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "Create item has been selected\n"
				+ "Please enter the title\n");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("Title can't be duplicate");
			return;
		}
		sc.nextLine();
		System.out.println("Please enter description");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.println("\n"
				+ "Delete item has been selected\n"
				+ "Please enter the title of item you want to remove\n"
				+ "\n");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "Edit item has been selected\n"
				+ "Please enter the title of the item you want to update\n"
				+ "\n");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("Title doesn't exist");
			return;
		}
		
		sc.nextLine();
		System.out.println("Enter the new title of the item");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("Title can't be duplicate");
			return;
		}
		
		System.out.println("Enter the new description ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("Item has been updated");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			TodoItem in = new TodoItem(filename, filename);
			for(int i=0;i<l.size();i++) {
				w.write(in.toSaveString());
			}
			w.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader("todolist.txt"));
			try {
				bf.readLine();
			} catch (IOException e) {
				System.out.println("todolist.txt 파일 존재하지 않습니다.");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("todolist.txt 파일 존재하지 않습니다.");
		}
		
	}
}
