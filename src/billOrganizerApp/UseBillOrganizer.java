package billOrganizerApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import doubleLinkedList.ListEmptyException;
import randomAccessStudentDataLLIndex.LLExtIterator;
import linkedLists.DuplicateDataException;
import linkedLists.NotFoundException;

public class UseBillOrganizer {

	public static void main(String[] args) throws DuplicateDataException,
			FileNotFoundException, IOException, ListEmptyException, randomAccessExceptions.NotFoundException {

		BillOrganizer organizer = new BillOrganizer();
		Scanner keyboard = new Scanner(System.in);
		
		Scanner file = new Scanner(new File("billOrganizer.txt"));
		try {
			organizer = new BillOrganizer(file);
		} catch (DuplicateDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (randomAccessExceptions.NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Bill Organizer created");
	
	

		int choice;
		String name;
		double amount;
		String date;
		String type;
		BillType billType = null;

		do {
			choice = menu();

			switch (choice) {

			case 1: // add a bill
				System.out.println("Enter the vendor name: ");
				name = keyboard.nextLine();
				System.out.println("Enter the amount: ");
				amount = keyboard.nextDouble();

				keyboard.nextLine(); // swallow the buffer

				System.out.println("Enter the date: (MM/DD/YYYY) ");
				date = keyboard.next();
				boolean correct;
				do {
					correct = false;
					System.out.println("Enter a valid bill type: ");
					type = keyboard.next();
					keyboard.nextLine();

					for (BillType b : BillType.values()) {
						if (b.name().equalsIgnoreCase(type)) {
							correct = true;
							billType = b;
							break;
						}
					}
				} while (!correct);

				Bill bill = new Bill(name, amount, date, billType);
				organizer.insert(bill);

				System.out.println("Bill " + bill.getID()
						+ " has been created.");

				break;

			case 2: // total bills
				System.out.println("The total of your bills is $"
						+ organizer.totalBills());

				break;

			case 3: // sorted by date

				LLExtIterator<Bill> iter = organizer.iteratorByDate();

				while (iter.hasNext()) {
					System.out.println(iter.next());
				}

				break;

			case 4: // sort by type
				LLExtIterator<Bill> iterType = organizer.iteratorByType();

				while (iterType.hasNext()) {
					System.out.println(iterType.next());
				}

				break;

			case 5: // sort by amount
				LLExtIterator<Bill> iterAmount = organizer.iteratorByAmount();

				while (iterAmount.hasNext()) {
					System.out.println(iterAmount.next());
				}

				break;

			case 6: // pay next bill by date
				Bill billDate = organizer.payNextBill(
						BillCriteria.BILLDUEDATE);
				System.out.println("Bill " + billDate.getID()
					+ " has been paid.");

				break;

			case 7: // pay next bill by type
				Bill billT = organizer.payNextBill(BillCriteria.BILLTYPE);
				System.out.println("Bill " + billT.getID() + " has been paid.");
				break;

			case 8: // pay next bill by Amount
				Bill billAmt = organizer.payNextBill(
						BillCriteria.BILLAMOUNT);
				System.out.println("Bill " + billAmt.getID()
						+ " has been paid.");
				break;

			case 9: // pay bill by ID

				System.out.println("Enter the Bill's ID: ");
				Integer ID = keyboard.nextInt();

				Bill billID = null;
				try {
					billID = organizer.payNextBillByID(ID);
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Bill " + billID.getID()
						+ " has been paid.");
				break;
		

			case 10:
				try {
					organizer.closeOrganizer("BillOrganizer.ser");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Bill Organizer written out.");
				break;
			case 11:
				
				try {
					organizer = new BillOrganizer("BillOrganizer.ser");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out
						.println("Your Bill Organizer has been brought into this program. ");
				break;
			case 12:
				System.out.println("Have a great day!");
				System.exit(0);
				break;
			
			
			case 13: 
			break;
			}
			
		} while (choice != 12);

		

	}

	public static int menu() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		System.out.println("Choose from the following: ");
		System.out.println("\n1. Add a bill" + "\n2. Get total of Bills"
				+ "\n3. View Bills sorted by date"
				+ "\n4. View Bills sorted by type."
				+ "\n5. View Bills sorted by amount"
				+ "\n6. Pay the next bill by date."
				+ "\n7. Pay the next bill by type."
				+ "\n8. Pay the next bill by amount."
				+ "\n9. Pay a bill by bill ID"
				+ "\n10. Close the operation and save the organizer."
				+ "\n11. Open a preexiting Bill Organizer"
				+ "\n12. Close the program" );
		int choice = input.nextInt();
		return choice;
	}

}
