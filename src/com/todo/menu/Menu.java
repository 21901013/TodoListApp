package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("Please choose one of the following factor");
        System.out.println("1) Adding a new item ( add )");
        System.out.println("2) Deleting an existing item ( del )");
        System.out.println("3) Updating an item  ( edit )");
        System.out.println("4) Listing all the items ( ls )");
        System.out.println("5) Sorting the list by name order ( ls_name_asc )");
        System.out.println("6) Sorting the list by reverse name order ( ls_name_desc )");
        System.out.println("7) Sorting the list by date order ( ls_date )");
        System.out.println("8) Finding the content ( find )");
        System.out.println("9) Sorting the list by reverse date order ( ls_date_desc )");
        System.out.println("10) Finding among part of the content ( find )");
        System.out.println("11) Finding among all the content ( find_cate )");
        System.out.println("12) Listing all the containing category ( ls_cate )");
        System.out.println("13) Exiting this program (Or press escape key to exit)");
    }

	public static void prompt() {
		System.out.print("\nFactor > ");
	}
}
