package billOrganizerApp;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import randomAccessStudentDataLLIndex.LLExtIterator;

public class SortFrame extends JFrame{

	private JScrollPane vertical;
	private JTextArea console;

	public SortFrame(LLExtIterator iter) {

		setTitle("BILL");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container container = getContentPane();
		setLayout((new BoxLayout(container, BoxLayout.Y_AXIS)));
		container.setBackground(Color.BLACK);

		setPreferredSize(new Dimension(500, 400));
		console = new JTextArea(15, 15);
		console.setBackground(Color.BLACK);
		console.setForeground(Color.BLUE);
		console.setFont(new Font("Serif", Font.BOLD, 15));

		vertical = new JScrollPane(console);
		vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(vertical);

		int i = 0;
		while (iter.hasNext()) {
			Bill bill = (Bill) iter.next();
			console.append(bill.toString());
			console.append("\n");
			console.append("\n");

			i++;
		}

	}
}
