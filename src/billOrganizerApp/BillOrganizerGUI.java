package billOrganizerApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import linkedLists.DuplicateDataException;
import randomAccessExceptions.NotFoundException;
import randomAccessStudentDataLLIndex.LLExtIterator;
import doubleLinkedList.ListEmptyException;

public class BillOrganizerGUI extends JFrame {

	private JList<String> list;
	private String[] menu;
	private BillOrganizer org;
	private JLabel choose;
	private JLabel label;

	public BillOrganizerGUI() throws FileNotFoundException {

		setTitle("Bill Organizer");
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = getContentPane();
		setLayout(new BorderLayout());

		Font font = new Font("Serif", Font.BOLD, 20);

		org = new BillOrganizer();

		label = new JLabel();

		choose = new JLabel(
				"Double click to choose one of the following options: ");
		choose.setForeground(Color.BLUE);
		choose.setAlignmentX(Component.CENTER_ALIGNMENT);
		choose.setFont(font);
		add(choose, BorderLayout.NORTH);

		DefaultListModel dlm = new DefaultListModel();
		menu = new String[12];
		menu[0] = ("Add a bill");
		menu[1] = ("Get total of Bills");
		menu[2] = ("View Bills sorted by date");
		menu[3] = ("View Bills sorted by type.");
		menu[4] = ("View Bills sorted by amount.");
		menu[5] = ("Pay the next bill by date.");
		menu[6] = ("Pay the next bill by amount.");
		menu[7] = ("Pay the next bill by type.");
		menu[8] = ("Pay the next bill by ID.");
		menu[9] = ("Close the operation and save the organizer.");
		menu[10] = ("Open a preexiting Bill Organizer");
		menu[11] = ("Close the program");

		list = new JList<String>(dlm);
		list.setListData(menu);
		list.setBackground(Color.YELLOW);
		list.setForeground(Color.BLUE);
		list.setFont(font);

		add(list, BorderLayout.CENTER);

		Scanner file = new Scanner(new File("billOrganizer.txt"));
		try {
			org = new BillOrganizer(file);
		} catch (DuplicateDataException e) {
			JOptionPane.showMessageDialog(null, "Duplicate Data");

		} catch (randomAccessExceptions.NotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"File was not Found. Shutting the program");
			dispose();
		}

		MouseListener mouseListener = new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				boolean caught;
				if (e.getClickCount() == 2) {

					int index = list.locationToIndex(e.getPoint());
					switch (index + 1) {

					case 1:
						try {
							new AddBillGui(org).setVisible(true);
						} catch (DuplicateDataException e2) {
							JOptionPane.showMessageDialog(null,
									"Duplicate Data. Try again.");
						}

						break;

					case 2:
						new TotalBillsJFrame(org.totalBills()).setVisible(true);
						break;

					case 3:
						LLExtIterator<Bill> iter = org.iteratorByDate();
						new SortFrame(iter).setVisible(true);
						break;

					case 4: // sort by type
						LLExtIterator<Bill> iterType = org.iteratorByType();
						new SortFrame(iterType).setVisible(true);

						break;

					case 5: // sort by amount
						LLExtIterator<Bill> iterAmount = org.iteratorByAmount();
						new SortFrame(iterAmount).setVisible(true);

						break;

					case 6: // pay next bill by date
						caught = false;
						Bill billDate = null;
						try {
							billDate = org
									.payNextBill(BillCriteria.BILLDUEDATE);
						} catch (ListEmptyException e5) {
							JOptionPane
									.showMessageDialog(null,
											"There are no bills to pay. Choose a different option.");
							caught = true;

						} catch (NotFoundException e5) {
							JOptionPane.showMessageDialog(null,
									"The bill was not found");
							caught = true;
							dispose();
						}
						if (!caught) {
							Integer ID = billDate.getID();
							new PayFrame(ID).setVisible(true);
						}

						break;

					case 7: // pay next bill by type
						caught = false;

						Bill billT = null;
						try {
							billT = org.payNextBill(BillCriteria.BILLTYPE);
						} catch (ListEmptyException e4) {
							JOptionPane
									.showMessageDialog(null,
											"There are no bills to pay. Choose a different option.");
							caught = true;

						} catch (NotFoundException e4) {
							JOptionPane.showMessageDialog(null,
									"The bill was not found");
							caught = true;
							dispose();
						}
						if (!caught) {
							Integer ID = billT.getID();
							new PayFrame(ID).setVisible(true);
						}
						break;

					case 8: // pay next bill by Amount
						caught = false;
						Bill billAmt = null;

						try {
							billAmt = org.payNextBill(BillCriteria.BILLAMOUNT);
						} catch (ListEmptyException e3) {
							JOptionPane
									.showMessageDialog(null,
											"There are no bills to pay. Choose a different option.");
							caught = true;

						} catch (NotFoundException e3) {
							JOptionPane.showMessageDialog(null,
									"The bill was not found");
							caught = true;
							dispose();
						}
						if (!caught) {
							Integer ID = billAmt.getID();

							new PayFrame(ID).setVisible(true);
						}

						break;

					case 9: // pay bill by ID

						new PayBillFrame(org).setVisible(true);
						break;

					case 10:
						try {
							org.closeOrganizer("BillOrganizer.ser");
						} catch (IOException e1) {
							JOptionPane
									.showMessageDialog(null,
											"The BillOrganizer File could not be found");
							dispose();
						}

						// write this out
						JOptionPane
								.showMessageDialog(null,
										"Your Bill Organizer has been written out to a file.");
						break;
					case 11:

						try {
							org = new BillOrganizer("BillOrganizer.ser");
						} catch (ClassNotFoundException e2) {
							JOptionPane
									.showMessageDialog(null,
											"Information has been changed since last time. Can not open file.");
							dispose();
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null,
									"Could not locate file");
							dispose();
						} catch (DuplicateDataException e1) {
							JOptionPane.showMessageDialog(null,
									"Duplicate Data");
							dispose();
						}

						JOptionPane
								.showMessageDialog(null,
										"Your Bill Organizer has been successfully imported to the program.");
						break;
					case 12:
						// write this out
						JOptionPane
								.showMessageDialog(null, "Have a great day!");

						System.exit(0);
						break;
					}
				}
			}

		};
		list.addMouseListener(mouseListener);

	}

	public static void main(String[] args) throws FileNotFoundException {
		new BillOrganizerGUI().setVisible(true);
	}

}
