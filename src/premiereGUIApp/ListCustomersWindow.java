package premiereGUIApp;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ListCustomersWindow extends JFrame implements WindowListener{
	private Connection dbConnection;
	private ResultSetTableModel tableModel;
	
	
	
	public ListCustomersWindow(Connection dbConnection) {
		this.dbConnection = dbConnection;
		
		JOptionPane.showMessageDialog(null,this.dbConnection.toString());
		//describe what type of data is being displayed
		this.setTitle("Customers");
		String query = "Select * FROM Customer";
		try{
			//instantiate the model which will automatically fire off the 
			//execution of the query in String query.
		    tableModel = new ResultSetTableModel(this.dbConnection,query);
		    
		    
		  
		   //create JTable based on the tableModel
		    //the table holds the data that will be displayed on the screen
		   JTable resultTable = new JTable(tableModel);

            //place components in the window
		   //place table in a scrollpane so that user can scroll through the 
		   //contents of the table
		   this.add(new JScrollPane(resultTable),BorderLayout.CENTER);
		
		   //adding a row sorter will allow the user to click on any column heading
		   //in order to resort the rows by the data in that column
		   final TableRowSorter<TableModel> sorter =
				    new TableRowSorter<TableModel> (tableModel);
		   resultTable.setRowSorter(sorter);
	
		   //set window size and make it and its components visible on the screen
		   setSize(500,250);
		   setVisible(true);

         }
		catch(SQLException sqlException){
			JOptionPane.showMessageDialog(null, sqlException.getMessage());
			tableModel.disconnectFromDatabase();
			
		}
	}



	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowClosed(WindowEvent e) {
		tableModel.disconnectFromDatabase();
		
	}



	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
