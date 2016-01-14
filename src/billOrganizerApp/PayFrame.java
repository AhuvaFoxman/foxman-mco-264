package billOrganizerApp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PayFrame extends JFrame{
	
	private JLabel label;
	public PayFrame(Integer ID){
		
		setTitle("BILL");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container container = getContentPane();
		setLayout((new BoxLayout(container, BoxLayout.Y_AXIS)));
		container.setBackground(Color.GREEN);
		
		label = new JLabel();
		add(label);
		
		label.setText(("Bill " + ID
				+ " has been paid."));
		label.setFont(new Font("Serif", Font.CENTER_BASELINE, 35));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setForeground(Color.GRAY);
		}
	
	
		
	}

