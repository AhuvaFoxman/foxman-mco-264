package premiereGUIApp;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OrderLinePanel extends JPanel{
	JLabel label1 ;
	JLabel label2;
	JLabel label3;
	JTextField[][]  partData;
    
	public OrderLinePanel(){
		setLayout(new GridLayout(6,3));
		label1 = new JLabel("Part ID");
		label2 = new JLabel("Quantity");
		label3 = new JLabel("Price");
		add(label1);
		add(label2);
		add(label3);
		partData = new JTextField[5][3];
		//set up a grid of text fields
		for (int i =0;i< 5;i++){
			for (int j=0; j<3;j++){
			partData[i][j] = new JTextField(5);
			add(partData[i][j]);
			}
		}
		
	}
	public OrderLineData getOrderLineData(int row){
		
		String partID;
		int qty;
		double price;
		partID = partData[row][0].getText();
		if (partID.equals("")){  //part id wasnt entered, assume row is empty
			return null;
		}
		String data;
		data = partData[row][1].getText();
		if (data.equals("")){
			return null;
		}
		qty = Integer.parseInt(data);
		data = partData[row][2].getText();
		if (data.equals("")){
			return null;
		}
		price = Double.parseDouble(data);
		OrderLineData temp = new OrderLineData(partID,qty,price);
		return temp;
		
		
		
	}
}
