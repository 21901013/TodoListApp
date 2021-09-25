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
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nCreate item has been selected\n"
				+ "Please enter the title");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("Title can't be duplicate");
			return;
		}
		sc.nextLine();
		System.out.println("\nPlease enter the category");
		category = sc.next();
		sc.nextLine();
		System.out.println("\nPlease enter the description");
		desc = sc.nextLine().trim();
		System.out.println("\nPlease enter the due date");
		due_date = sc.next();

		
		TodoItem t = new TodoItem(category,title, desc, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nDelete item has been selected\n"
				+ "Please enter the index of item you want to remove");
		
		int index = sc.nextInt();
		l.deleteItem(index);
		System.out.println("Item has been deleted");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String new_title, new_category, new_description, new_due_date;
		
		System.out.println("\n"
				+ "Edit item has been selected\n"
				+ "Please enter the index of the item you want to update\n");
		
		int index = sc.nextInt();
		if (index>l.size()) {
			System.out.println("Index doesn't exist\n");
			System.out.println("Enter the new index of the item\n");
			int new_index = sc.nextInt();
			System.out.println(l.get((new_index-1)));
			index = new_index;
		}
		
		System.out.println(l.get((index-1)));
		
		System.out.println("\nEnter the new title of the item\n");
		new_title = sc.next();
		if (l.isDuplicate(new_title)) {
			System.out.println("Title can't be duplicated\n");
			return;
		}
		
		System.out.println("Enter the new category of the item\n");
		new_category = sc.next();
		
		System.out.println("Enter the new description\n");
		new_description = sc.next().trim();
		
		String now = sc.nextLine();
		System.out.println("Enter the new due date\n");
		new_due_date = sc.next();
		l.deleteItem(index);
		TodoItem t = new TodoItem(new_category, new_title, (new_description+now), new_due_date);
		l.addItem(t);
		System.out.println("Item has been updated\n");
	}

	public static void listAll(TodoList l) {
		System.out.print(l.size()+" items ]"+"\n");
		int index=1;
		for (TodoItem item : l.getList()) {
			System.out.println(index+". "+item.toString());
			index++;
		}
	}
	
	public static void findItem(TodoList l, String key_word) {
		for(TodoItem item : l.getList()) {
			if(item.toCompare().contains(key_word)) {
				System.out.println(item);
			}	
		}
	}
	
	public static void find_cateItem(TodoList l, String key_word) {
		for(TodoItem item : l.getList()) {
			if(item.toString().contains(key_word)) {
				System.out.println(item.toString());
			}
		}
	}
	
	public static void listCategory(TodoList l) {
		HashSet<String> category = new HashSet<String>(l.size());
		for(TodoItem item : l.getList()) {
			category.add(item.getCategory());
		}
		
		Iterator<String> iter = category.iterator();
		int count=0;
		while(iter.hasNext()) {
			System.out.print(iter.next());
			count++;
			if(iter.hasNext()) {
				System.out.print("/");
			}
		}
		System.out.println("\n"+count+" of category have been registered");
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item:l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("All data has been saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader(filename));
			String oneline;
			int number=0;
			while((oneline=bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline,"##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				TodoItem it = new TodoItem(category,title,desc,due_date);
				l.addItem(it);
				number++;
			}
			bf.close();
			System.out.println("In total "+number+" file(s) has been read.");
		} catch (FileNotFoundException e) {
			System.out.println(filename+" file doesn't exist.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
