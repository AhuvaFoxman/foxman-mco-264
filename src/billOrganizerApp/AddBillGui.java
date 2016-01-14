package billOrganizerApp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import linkedLists.DuplicateDataException;
import randomAccessExceptions.NotFoundException;

public class AddBillGui extends JFrame {

	private JLabel labelName;
	private JLabel labelAmount;
	private JLabel labelDate;
	private JLabel labelType;
	private JLabel choose;
	private JTextField name;
	private JTextField amount;
	private JTextField date;
	private JButton button;
	private BillOrganizer org;
	private BillType billType;

	public AddBillGui(BillOrganizer organizer) throws DuplicateDataException {

		setTitle("BILL");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container container = getContentPane();
		setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(Color.GRAY);

		Font font = new Font("Serif", Font.BOLD, 19);


		this.labelName = new JLabel("Enter the vendor name: ");
		this.labelName.setFont(font);
		this.labelName.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.labelAmount = new JLabel("Enter the amount: ");
		this.labelAmount.setFont(font);
		this.labelAmount.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.labelDate = new JLabel("Enter the date: (MM/DD/YYYY) ");
		this.labelDate.setFont(font);
		this.labelDate.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.labelType = new JLabel("Enter a valid bill type: ");
		this.labelType.setFont(font);
		this.labelType.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.choose = new JLabel("Choose one of the following: ");
		this.choose.setFont(new Font("Serif", Font.BOLD, 15));
		this.choose.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.org = organizer;

		name = new JTextField();
		name.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
		name.setBackground(Color.CYAN);
		name.setForeground(Color.GRAY);
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		name.setFont(font);

		amount = new JTextField();
		amount.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
		amount.setBackground(Color.CYAN);
		amount.setForeground(Color.GRAY);
		amount.setAlignmentX(Component.CENTER_ALIGNMENT);
		amount.setFont(font);

		date = new JTextField();
		date.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
		date.setBackground(Color.CYAN);
		date.setForeground(Color.GRAY);
		date.setAlignmentX(Component.CENTER_ALIGNMENT);
		date.setFont(font);

		this.button = new JButton("Submit");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setBackground(Color.BLUE);
		button.setFont(font);
		String typeOptions[] = { "CLOTHING", "EDUCATION", "FOOD", "GROCERIES",
				"PHONE", "TRAVEL", "UTILITIES" };
		// add data
		JComboBox box = new JComboBox(typeOptions);
		box.setBackground(Color.CYAN);
		box.setForeground(Color.GRAY);
		box.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		box.setSelectedIndex(6);

		add(labelName);
		add(name);
		add(labelAmount);
		add(amount);
		add(labelDate);
		add(date);
		add(choose);
		add(box);

		add(button);

		billType = null;
		box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String type = (String) cb.getSelectedItem();
				boolean correct;

				correct = false;

				for (BillType b : BillType.values()) {
					if (b.name().equalsIgnoreCase(type)) {
						correct = true;
						billType = b;
						break;
					}
				}
				if (!correct) {
					JOptionPane
							.showConfirmDialog(
									null,
									"The wrong bill type was entered. \n Enter a valid type.",
									"Error", JOptionPane.DEFAULT_OPTION,
									JOptionPane.PLAIN_MESSAGE);

				}

			}
		});

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String vendor = name.getText();

				String amt = amount.getText();

				String d = date.getText();

				Bill bill = new Bill(vendor, Double.parseDouble(amt), d,
						billType);

				try {
					org.insert(bill);
				} catch (DuplicateDataException e) {

				}

				JOptionPane.showMessageDialog(null, "Bill has been created");
				dispose();

			}
		});

	}


}
