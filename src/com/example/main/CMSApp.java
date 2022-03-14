package com.example.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import com.example.Model.Customer;
import com.example.db.CustomerDAO;
import com.example.db.DAO;

public class CMSApp {
	Scanner sc = new Scanner(System.in);
	
	DAO<Customer> dao;
	
	public CMSApp() {
		System.out.println("~~~~~~~~~~~~~~~~~~");
		System.out.println("Welcome to CMS App.");
		System.out.println("~~~~~~~~~~~~~~~~~~");
		
		dao = new CustomerDAO();
	}
	
	public void showMenu() {
		do {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("1. Registered Customer");
			System.out.println("2. Update Customer");
			System.out.println("3. Delete Customer");
			System.out.println("4. Fetch All Customer");
			System.out.println("5. Quit");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			System.out.println("Select Your Option.");
			
			int choice = sc.nextInt();
			if(choice == 1) registerCustomer();
			else if(choice == 2) updateCustomer();
			else if(choice == 3) deleteCustomer();
			else if(choice == 4) fetchAllCustomer();
			else if(choice == 5) {
				System.out.println("Thank you for using CMS App.");
				sc.close();
				break;
			}else {
				System.out.println("Invalid Option! Enter a valid option.");
			}
		}
		while(true);
	}
	
	private void fetchAllCustomer() {
		System.out.println("1. Sort with cid.");
		System.out.println("2. Sort with name.");
		System.out.println("3. Sort with reward points.");
		int option = sc.nextInt();
		if(option == 1) {
			ArrayList<Customer> customers = dao.query();
			customers.forEach((customer) -> System.out.println(customer));
		}
		else if(option == 2) sortName();
		else if(option == 3) {
			System.out.println("1. Low to High");
			System.out.println("2. High to Low");
			int optionRP = sc.nextInt();
			if(optionRP == 1) sortRewardPLH();
			else if(optionRP == 2) sortRewardPHL();
		}
	}

	private void sortRewardPHL() {
		ArrayList<Customer> customers = dao.query();
//		Comparator<Customer> Comparator = (o1, o2) -> o2.rewardPoints - o1.rewardPoints; //using lambda expression
		Collections.sort(customers, (o1, o2) -> o2.rewardPoints - o1.rewardPoints); // Direct Lambda Expression
		customers.forEach((customer) -> System.out.println(customer));		
	}

	private void sortRewardPLH() {
		ArrayList<Customer> customers = dao.query();
		Comparator<Customer> Comparator = (o1, o2) -> o1.rewardPoints - o2.rewardPoints; //using lambda expression
		Collections.sort(customers, Comparator);
		customers.forEach((customer) -> System.out.println(customer));		
	}

	private void deleteCustomer() {
		System.out.println("Enter Customer ID to delete: ");
		int cid = sc.nextInt();
		int result = dao.delete(cid);
		String message = result > 0 ? cid+" Deleted." : cid+" Deletion Failed.";
		System.out.println(message);		
	}

	private void updateCustomer() {
		System.out.println("Enter Customer ID to Update: ");
		int cid = sc.nextInt();
		Customer customer = new Customer();
		customer.inputCustomerDetails();
		customer.cid = cid;
		int result = dao.update(customer);
		String message = result > 0 ? customer.name+" Updated." : customer.name+" Update Failed.";
		System.out.println(message);		
	}

	private void registerCustomer() {
		Customer customer = new Customer();
		customer.inputCustomerDetails();
		int result = dao.insert(customer);
		String message = result > 0 ? customer.name+" Registered." : customer.name+" Registeration Failed.";
		System.out.println(message);
	}
	
	private void sortName() {
		ArrayList<Customer> customers = dao.query();
		//Anonymous Class
//		Comparator<Customer> Comparator = new Comparator<Customer>() {
//			
//			@Override
//			public int compare(Customer o1, Customer o2) {
//				return o1.name.compareToIgnoreCase(o2.name);
//			}
//		};
		Comparator<Customer> Comparator = (o1, o2) -> o1.name.compareToIgnoreCase(o2.name); //using lambda expression
		Collections.sort(customers, Comparator);
		customers.forEach((customer) -> System.out.println(customer));
	}
	

	public static void main(String[] args) {
		CMSApp app = new CMSApp();
		app.showMenu();
	}

}
