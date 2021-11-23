package com.bridgelabz;

import java.util.Scanner;

public class AddressBookMain {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book");
		AddressBook addressBook = new AddressBook();
		while (true) {
			System.out.println("Enter what you have to do");
			System.out.println("" + " 1 Add Contact " + "\n 2 Edit Contact " + "\n 3 Delete Contact"
					+ " \n 4 Display Contact " + "\n 5 Search Person on basis Of State or City "
					+ "\n 6 View Person on basis Of State or City " + "\n 7 Count of Person on basis Of State or City"
					+ "\n 8 Sort contact on basis of Person name " + "\n 9 Sort On the basis of city,state or Zip"
					+ "\n 10 write data to file" + "\n 11 read file" + "\n 12 Quit");

			int userChoice = scanner.nextInt();
			switch (userChoice) {
			case 1:
				addressBook.addContact();
				break;

			case 2:
				System.out.println("Enter the Phone Number");
				String phoneNumber = scanner.next();
				System.out.println("Enter the Address Book name");
				String bookName = scanner.next();
				addressBook.editContact(bookName, phoneNumber);
				break;

			case 3:
				System.out.println("Enter the Phone Number");
				String phoneNumberToDelete = scanner.next();
				System.out.println("Enter the Address Book name");
				String bookNameToUapdate = scanner.next();
				addressBook.deleteContact(phoneNumberToDelete, bookNameToUapdate);
				break;

			case 4:
				addressBook.displayContact();
				break;

			case 5:
				System.out.println("Enter the city or state which to be searched");
				String searchKey = scanner.next();
				addressBook.searchPerson(searchKey);
				break;

			case 6:
				System.out.println("Enter the city or state which to be searched");
				String viewKey = scanner.next();
				addressBook.viewPerson(viewKey);
				break;

			case 7:
				System.out.println("Enter the city or state which to be searched");
				String searchKeyForCount = scanner.next();
				int count = addressBook.searchPerson(searchKeyForCount);
				System.out.println("Total Contacts are : " + count + " in " + searchKeyForCount);
				break;

			case 8:
				addressBook.sortContacts();
				break;

			case 9:
				System.out.println("How you want to sort\n1 City \n2 State \n3 Zip");
				int sortByWhich = scanner.nextInt();
				addressBook.sortBY(sortByWhich);
				break;

			case 10:
				addressBook.writingToFile();
				break;

			case 11:
				addressBook.readFile();
				break;

			case 12:
				System.out.println("Thanks For Using Us");
				System.exit(0);
				break;

			default:
				System.out.println("Please Enter Proper Option");
			}
		}
	}

}