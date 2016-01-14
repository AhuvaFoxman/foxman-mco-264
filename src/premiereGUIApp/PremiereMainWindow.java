package premiereGUIApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JRadioButtonMenuItem;



public class PremiereMainWindow extends JFrame implements WindowListener{
	   //connection to database at back end
	   private Connection dbConnection = null;
	   
	   //set up window size
	   private final int WINDOW_WIDTH = 800;
	   private final int WINDOW_HEIGHT = 800;
	   
	    //provide access to choices via a menu 
	    private JMenuBar premiereMenuBar;
		
		private JMenuItem addCustomerMenuItem;
		private JMenuItem addPartMenuItem;
		private JMenuItem addOrderMenuItem;
		private JMenuItem viewCustomersMenuItem;
		private JMenuItem viewOrdersMenuItem;
		private JRadioButtonMenuItem rbMenuItem;
	   
	   
	   
      
       
	   public PremiereMainWindow(){
		//display message in the title bar   
    	setTitle("Premiere Application ");
    	//set window size
    	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Specify an action for the close button.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //invoke the method that sets up the menu
        setUpMenuBar();
       
        //connect to database
        
        connectToDatabase();
       
        //allow window to appear on the console screen       
        setVisible(true);
        
        
    	
    }
	   
	   private void setUpMenuBar(){
			 JMenu addMenu;   //local variable, just used for this method

			//Create the menu bar.
			this.premiereMenuBar = new JMenuBar();

			//Build the first menu.
			addMenu = new JMenu("Add Data");
			addMenu.setMnemonic(KeyEvent.VK_A);
			addMenu.getAccessibleContext().setAccessibleDescription(
			        "Add Data to Premiere Database");
			this.premiereMenuBar.add(addMenu);
			
			
			//build the submenu - a group of JMenuItems
			this.addCustomerMenuItem = new JMenuItem("Add Customer",
			                         KeyEvent.VK_C);
			
			this.addCustomerMenuItem.getAccessibleContext().setAccessibleDescription(
			        "Add A Customer");
			addCustomerMenuItem.addActionListener(new AddCustomerListener());
			addMenu.add(addCustomerMenuItem);

			addPartMenuItem = new JMenuItem("Add Part to Inventory");
			addPartMenuItem.setMnemonic(KeyEvent.VK_P);
			addPartMenuItem.addActionListener(new AddPartListener());
			addMenu.add(addPartMenuItem);

			
			addOrderMenuItem = new JMenuItem ("Add New Order");
			addOrderMenuItem.setMnemonic(KeyEvent.VK_O);
			addOrderMenuItem.addActionListener(new AddOrderListener());
			addMenu.add(addOrderMenuItem);
			
			//Build second menu item
			addMenu = new JMenu("View Data");
			addMenu.setMnemonic(KeyEvent.VK_V);
			addMenu.getAccessibleContext().setAccessibleDescription("View Available Data");
			premiereMenuBar.add(addMenu);
			
			//Build second menu item's submenu
			viewCustomersMenuItem = new JMenuItem("List Customers");
			viewCustomersMenuItem.setMnemonic(KeyEvent.VK_C);
			viewCustomersMenuItem.addActionListener(new ListCustomersListener());
			addMenu.add(viewCustomersMenuItem);
			
			viewOrdersMenuItem= new JMenuItem("List Orders");
			viewOrdersMenuItem.setMnemonic(KeyEvent.VK_R);
			viewOrdersMenuItem.addActionListener(new ListOrdersListener());
			addMenu.add(viewOrdersMenuItem);
			

				
			this.setJMenuBar(premiereMenuBar);
			
			
	    }
       
	   private void connectToDatabase(){
		   //to run at touro on the virtual server
		   
		   //final String DATABASE_URL = "jdbc:sqlserver://127.0.0.1;" +
			//	     "databaseName=Premiere";
		   
		   //to run sql server express using windows authentication
		   //final String DATABASE_URL= "jdbc:sqlserver://127.0.0.1;" +
		        //   "databaseName=Premiere;integratedSecurity=true";
		   
		   //to run at home on my laptop
		   final String DATABASE_URL = "jdbc:sqlserver://127.0.0.1;" +
			  	     "databaseName=Premiere;";

						
			try{
		    	   
		    	   //establish connection to database
		    	   
				   //using sql server authentication 
		    	   System.out.println("connecting to database");
		    	   //to connect using sql server authentication
		    	   dbConnection = DriverManager.getConnection(DATABASE_URL,"internetuser","internetUs3r");
		    	  
		    	   //to connect using windows authentication on my laptop
		    	   //dbConnection = DriverManager.getConnection(DATABASE_URL);

		    	   
		    	   //to enable transaction processing do not
		    	   //automatically commit
		    	   dbConnection.setAutoCommit(false);
		    	   
		    	  
		       }
		       catch(SQLException sqlException){
		    	   sqlException.printStackTrace();
		    	   
		       }
			
		      System.out.println("connected to database");
		      
			 
			  //collect data from the user interface
		      
	   }
	   
	   private class AddCustomerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null,"Add Customer Window - under construction");
			
		}
		   
	   }
	   
	   
	   private class AddPartListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Add Part Window - under construction");
			
		}
		   
		   
	   }
	   
	   
	   private class AddOrderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			 new AddOrderWindow(dbConnection);
			
		}
		   
	   }
	   

      private class ListCustomersListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new ListCustomersWindow(dbConnection);
			
		}
    	  
      }
      
      private class ListOrdersListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "list orders window - under construction");
			
		}
    	  
      }

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		//when user closes the window , disconnect from the database
		try{
		dbConnection.close();
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		
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
