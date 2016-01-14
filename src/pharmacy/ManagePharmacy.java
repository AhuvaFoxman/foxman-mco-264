package pharmacy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ManagePharmacy {

	public static void main(String[] args) throws InvalidDataException,
			DuplicateDataException, IOException, NotFoundException {

		Scanner keyboard = new Scanner(System.in);
		PharmacyList pharm = new PharmacyList("./pharmacyCompanies.dat");

		System.out.print("Have you ever set up this application before? Y/N ");
		char answer = keyboard.next().charAt(0);

		if (answer == 'Y' || answer == 'y') { // data was stored on the disk
												// already
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"pharmacy.ser"));
			try {
				pharm = (PharmacyList) in.readObject();
				pharm.connectToData("pharmacyCompanies.dat");
				in.close();
			} catch (ClassNotFoundException ex1) {
				System.out
						.println("The data in the file does not match the class structure...contact IT.");
				System.exit(1);
			}

			catch (IOException ex2) {
				System.out.println("Couldn't locate the file...contact IT.");
				System.exit(1);
			}
		} else if (answer == 'N' || answer == 'n') {
			Scanner input = new Scanner(new File("./pharmacyCompanies.txt"));
			while (input.hasNext()) {
				PharmaceuticalCo pharmacy = new PharmaceuticalCo(input);
				pharm.addCompany(pharmacy.getCompanyCode(),
						pharmacy.getPhoneNumber(), pharmacy.getCompanyName());
			}
			input.close();
		} else
			;

		int choice;
		String code;
		String name;
		do {

			choice = menu(keyboard);

			switch (choice) {

			case 1: // Add a company
				System.out.print("Enter the company's Code: ");
				code = keyboard.next();
				System.out.print("Enter the company's name: ");
				name = keyboard.next();
				System.out.print("Enter the company's phone number: ");
				String phone = keyboard.next();

				pharm.addCompany(code, phone, name);
				System.out.println("Company: " + name + " has been added.");

				break;

			case 2: // remove a company
				keyboard.nextLine(); //swallow the buffer
				System.out.print("Enter the company code: ");
				code = keyboard.nextLine();
				
				System.out.print("Enter the company name: ");
				name = keyboard.nextLine();

				pharm.removeCompany(code,name);
				System.out.println("Company: " + name + " has been removed.");

				break;

			case 3: // modify a company phone number
				System.out.print("Enter the company code: ");
				code = keyboard.next();
				System.out.print("Enter the new phone number: ");
				phone = keyboard.next();

				pharm.modifyCompanyPhone(code, phone);
				System.out.println("Company: " + code
						+ " phone has been modified to " + phone);

				break;

			case 4: // display company information
				int in;
				do {
					System.out
							.print("Enter 1 if you would like to enter company code or enter 2 if you "
									+ "want to enter the company name: ");

					in = keyboard.nextInt();
				} while (in != 1 && in != 2);

				switch (in) {
				case 1:
					System.out.print("Please enter the company code: ");
					code = keyboard.next();
					System.out.println(pharm.findCompanyCode(code));
					break;

				case 2:
					keyboard.nextLine(); // flush the buffer
					System.out.print("Please enter the company name: ");
					name = keyboard.nextLine();
					System.out.println(pharm.findCompanyName(name));

				}

				break;

			case 5:// List information about each company on file

				System.out.println(pharm.getCompanyInfo());

				break;

			case 6: // end the application
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream("pharmacy.ser"));
				out.writeObject(pharm);
				out.close();
				System.out
						.println("Wrote information to file. Have a great day.");

				System.exit(0);
				break;

			}

		} while (choice > 0 && choice != 6);
		keyboard.close();
	}

	public static int menu(Scanner key) {

		System.out.println("Choose one of the following: ");
		System.out.println("1. Add a pharmaceutical company: \n"
				+ "2. Remove a company: \n"
				+ "3. Modify company phone number: \n"
				+ "4. Display company information: \n"
				+ "5. List information about each company on file: \n"
				+ "6. End the Application: ");
		int choice = key.nextInt();

		return choice;
	}

}
