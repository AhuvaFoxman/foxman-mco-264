package premiereGUIApp;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class AddOrderWindow extends JFrame{

	 private final int WINDOW_WIDTH = 800;
	 private final int WINDOW_HEIGHT = 800;
	 
	 private OrderPanel orderPanel;
	 private OrderLinePanel orderLinePanel;
	 private ButtonPanel buttonPanel;
	 
	 private Connection dbConnection;
	 
	 public AddOrderWindow(Connection dbConnection){
		   //store connection to database at back end
		    this.dbConnection = dbConnection;
		 
		    //display message in the title bar   
	    	setTitle("Premiere Order Entry Form");
	    	//set window size
	    	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

	        // Specify an action for the close button.
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //set up layout of the window 
	        setLayout(new BorderLayout());
	    	
	        orderPanel = new OrderPanel();
	        add(orderPanel,BorderLayout.WEST);
	        orderLinePanel = new OrderLinePanel();
	        add(orderLinePanel,BorderLayout.CENTER);
	        buttonPanel = new ButtonPanel(dbConnection, orderPanel, orderLinePanel);
	       
	        add(buttonPanel,BorderLayout.SOUTH);
	        pack();
	        setVisible(true);
		 
		 
		 
		 
		 
		 
	 }
}
