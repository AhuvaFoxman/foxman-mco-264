package billOrganizerApp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import linkedLists.DuplicateDataException;
import randomAccessExceptions.NotFoundException;

public class PayBillFrame extends JFrame{
	
	private BillOrganizer org;
	private JLabel label;
	private JTextField text;
	private JButton button;
	
	public PayBillFrame(BillOrganizer org){
		
		setTitle("BILL");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container container = getContentPane();
		setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(Color.GREEN);
		
		Font font = new Font("Serif", Font.CENTER_BASELINE, 18);
		this.org = org;
		label = new JLabel("Enter the ID: ");
		label.setFont(font);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setForeground(Color.GRAY);
		
		text = new JTextField();
		text.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
		text.setBackground(Color.DARK_GRAY);
		text.setForeground(Color.LIGHT_GRAY);
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		text.setFont(font);
		
		button = new JButton("Button");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setBackground(Color.YELLOW);
		button.setFont(font);
		
		add(label);
		add(text);
		add(button);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Integer ID = Integer.parseInt(text.getText());

				Bill billID = null;
				try {
					try {
						billID = org.payNextBillByID(ID);
					} catch (linkedLists.NotFoundException e) {
						JOptionPane.showMessageDialog(null,"The Bill could not be found. Try again");
					}
				} catch (NotFoundException e) {
					JOptionPane.showMessageDialog(null,"The bill was not found. Try again.");
				}
					
				
					
					dispose();
					}
		
			
		});

		
		
	}
	

}
