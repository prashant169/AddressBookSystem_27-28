package com.bridgelabz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class AddressBook {
	HashMap<String, LinkedList<Contact>> addressBooks = new HashMap<>();
	Scanner scanner = new Scanner(System.in);
	public static final String FILE_PATH = "C:\\Users\\MY PC\\Desktop//addressBook.txt";

	public Contact addContact() {
		Contact contact = new Contact();
		LinkedList<Contact> allContacts = new LinkedList<Contact>();
		System.out.println("Enter First Name");
		contact.setFirstname(scanner.next());
		System.out.println("Enter last Name");
		contact.setLastname(scanner.next());
		System.out.println("Enter City");
		contact.setCity(scanner.next());
		System.out.println("Enter State");
		contact.setState(scanner.next());
		System.out.println("Enter Pincode");
		contact.setZipcode(scanner.nextInt());
		System.out.println("Enter Phone Number");
		contact.setPhonenumber(scanner.next());
		System.out.println("Enter Email");
		contact.setEmail(scanner.next());
		System.out.println("Enter Book name to which you have to add contact");
		String bookName = scanner.next();

		if (addressBooks.containsKey(bookName)) {
			LinkedList<Contact> contactList = addressBooks.get(bookName);
			addContactToExsistingBook(contact, bookName, contactList);
		} else {
			allContacts.add(contact);
			addressBooks.put(bookName, allContacts);
			System.out.println("New book created and Contact Added Sucessfully");
		}

		return contact;
	}

	public boolean deleteContact(String phoneNumber, String bookName) {
		if (addressBooks.containsKey(bookName)) {
			LinkedList<Contact> contactList = addressBooks.get(bookName);
			for (Contact contact : contactList) {
				if (contact.getPhonenumber() == phoneNumber) {
					contactList.remove(contact);
					return operationStatus(true);
				}
			}
		}
		return operationStatus(false);
	}

	public boolean editContact(String phoneNumber, String bookName) {
		if (addressBooks.containsKey(bookName)) {
			LinkedList<Contact> contactList = addressBooks.get(bookName);
			for (Contact contact : contactList) {
				if (contact.getPhonenumber() == phoneNumber) {
					System.out.println("Enter First Name");
					String firstName = scanner.next();
					System.out.println("Enter last Name");
					String lastName = scanner.next();
					System.out.println("Enter address");
					String address = scanner.next();
					System.out.println("Enter City");
					String city = scanner.next();
					System.out.println("Enter State");
					String state = scanner.next();
					System.out.println("Enter zip");
					String zip = scanner.next();
					contact.setFirstname(firstName);
					contact.setLastname(lastName);
					contact.setCity(city);
					contact.setState(state);
					contact.setState(zip);
					return operationStatus(true);
				}
			}
		}
		return operationStatus(false);
	}

	public void displayContact() {
		addressBooks.entrySet().stream().map(books -> books.getKey()).map(bookNames -> {
			System.out.println(bookNames);
			return addressBooks.get(bookNames);
		}).forEach(contactInBook -> System.out.println(contactInBook));
	}

	public int searchPerson(String searchKey) {
		int count = 0;
		for (String bookName : addressBooks.keySet()) {
			LinkedList<Contact> contactList = addressBooks.get(bookName);
			contactList.stream().filter(n -> n.getState() == searchKey || n.getCity() == searchKey)
					.forEach(n -> System.out.println(n.getFirstname() + " " + n.getLastname()));
		}
		return count;
	}

	private static boolean operationStatus(boolean status) {
		if (status) {
			System.out.println("Operation  Successfully");
		} else {
			System.out.println("Contact not found");
		}
		return status;
	}

	private void addContactToExsistingBook(Contact contact, String bookName, LinkedList<Contact> contactList) {
		boolean isAlreadyExsist = contactList.stream()
				.anyMatch(contactsInlist -> contactsInlist.getFirstname() == contact.getFirstname());
		if (!(isAlreadyExsist)) {
			contactList.add(contact);
			addressBooks.put(bookName, contactList);
			System.out.println("New Contact Added Sucessfully");
		} else {
			System.out.println("Contact already exsists");
		}
	}

	public void viewPerson(String viewKey) {
		for (String bookName : addressBooks.keySet()) {
			LinkedList<Contact> contactList = addressBooks.get(bookName);
			contactList.stream().filter(contact -> contact.getState() == viewKey || contact.getCity() == viewKey)
					.forEach(contact -> System.out.println(contact));
		}

	}

	public void sortContacts() {
        for (String bookName : addressBooks.keySet()) {
            LinkedList<Contact> contatct = addressBooks.get(bookName);
            contatct.stream().sorted(Comparator.comparing(Contact::getFirstname)).forEach(n -> System.out.println(n));
        }
    }

	public void sortBY(int sortByWhich) {
        switch (sortByWhich) {
            case 1:
                for (String bookName : addressBooks.keySet()) {
                    LinkedList<Contact> contatct = addressBooks.get(bookName);
                    contatct.stream().sorted(Comparator.comparing(Contact::getCity)).forEach(n -> System.out.println(n));
                }
                break;
            case 2:
                for (String bookName : addressBooks.keySet()) {
                    LinkedList<Contact> contatct = addressBooks.get(bookName);
                    contatct.stream().sorted(Comparator.comparing(Contact::getState)).forEach(n -> System.out.println(n));
                }
                break;
            case 3:
                for (String bookName : addressBooks.keySet()) {
                    LinkedList<Contact> contatct = addressBooks.get(bookName);
                    contatct.stream().sorted(Comparator.comparing(Contact::getZipcode)).forEach(n -> System.out.println(n));
                }
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }

    }

    public void writingToFile() {
        checkFile();

        StringBuffer addressBookBuffer = new StringBuffer();
        addressBooks.entrySet().stream()
                .map(books -> books.getKey())
                .map(bookNames -> {
                    addressBookBuffer.append(bookNames + "\n");
                    return addressBooks.get(bookNames);
                })
                .forEach(contactInBook -> addressBookBuffer.append(contactInBook + "\n"));

        try {
            Files.write(Paths.get(FILE_PATH), addressBookBuffer.toString().getBytes());
            System.out.println("Written in the file \n\n");
        } catch (IOException e) {
            System.err.println("Problem encountered while writing into file");
        }
    }

    public void readFile() {
        try {
            String contentOfFile = Files.readString(Paths.get(FILE_PATH));
            System.out.println(contentOfFile);
        } catch (IOException e) {
            System.err.println("Faced some problem while reading the file ");
        }
    }

    private void checkFile() {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Created a file at " + FILE_PATH);
            }
        } catch (IOException e1) {
            System.err.println("Problem encountered while creating a file");
        }
    }
}