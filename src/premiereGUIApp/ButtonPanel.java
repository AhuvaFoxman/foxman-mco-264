package premiereGUIApp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel{
	private JButton calcButton;
	private JButton exitButton;
	private OrderPanel orderPanel;
	private OrderLinePanel orderLinePanel;
    private Connection dbConnection;
    
	
	
  public ButtonPanel(Connection dbConnection, OrderPanel orderPanel, OrderLinePanel orderLinePanel) {
	  
	  
	  setLayout(new FlowLayout());
		calcButton = new JButton("Insert Order");
		exitButton = new JButton("Exit");
		//get references to text field on the user interface
	    this.orderPanel = orderPanel;
		this.orderLinePanel = orderLinePanel;
		
		//store reference to database at back end
		this.dbConnection = dbConnection;
		
		
		//add buttons to the panel
		add(calcButton);
		add(exitButton);
		System.out.println(this.getClass());
		
		calcButton.addActionListener(new ButtonListener());
		
		
	}

private class ButtonListener implements ActionListener{
	       private String orderID;
	       private String custID;
	       private Calendar orderDate;
	       private String partID;
	       private int qty;
	       private double price;
	       private double total;
	       
	       //private Date orderDate;
	       private PreparedStatement insertOrder;
	       private PreparedStatement insertOrderLine;
	       
	  public ButtonListener(){
		    orderDate =null;
	  }     
	  public void actionPerformed(ActionEvent e){
		   System.out.println("ready to process the button");
		 //get the frame in which the button panel was placed
		   
		  
	      insertOrder = null;   //query statement
	      insertOrderLine = null;
	      
	       try{
	    	   
	    	   //set up the insert statements that will be used to store the data
	    	   //in the database
	    	   insertOrder = dbConnection.prepareStatement("Insert into Orders (Order_Num, Order_Date,Cust_Num) "+
	    	        "VALUES (?,?,? )");
	           insertOrderLine = dbConnection.prepareStatement("Insert into Order_Line (Order_Num,Part_Num,QTY_Ordered,Quoted_Price)" +
	        		"VALUES (?,?,?,?)");   
	       }
	       catch(SQLException sqlException){
	    	   sqlException.printStackTrace();
	    	   
	       }
		
	      
	      
	      OrderPanel orderInfo = ButtonPanel.this.orderPanel;
	      //data about parts that were ordered
	      OrderLinePanel orderLineInfo = ButtonPanel.this.orderLinePanel;
	      
		  System.out.println("access to order panel");
		  String orderNum;
		  //get orderid that user enter in the JTextField on the order entry form
		  orderID = orderInfo.getOrderID().getText();
		  //orderID = Integer.parseInt(orderNum);
		  System.out.println("Order " + orderID);
		  //get date that user entered on order entry form
		  String date = orderInfo.getDate().getText();
		  java.sql.Date sqlDate= null;
		  String[] dateParts = date.split("/");
		  if (dateParts.length !=3){
			  //user made an error entering the date
			  JOptionPane.showMessageDialog(null,"incorrect date format");
			  return;
		  }
		  else
		  { int month;
		    int day;
		    int year;
			month = Integer.parseInt(dateParts[0]);
			day = Integer.parseInt(dateParts[1]);
			year = Integer.parseInt(dateParts[2]);
			System.out.println(month + " " + day + " " + year);
			orderDate = GregorianCalendar.getInstance();
			orderDate.set(year, month-1,day,0,0,0);
			
			sqlDate = new java.sql.Date(orderDate.getTimeInMillis());
			
		
			
			
			//get custid from order entry form
			custID = orderInfo.getCustID().getText();
			
			//now we have all the data necessary to add a new order record
			//create Statement for querying database
	    	  try{ 
			  insertOrder.setString(1, orderID);
			  
			  insertOrder.setDate(2,sqlDate);
			  insertOrder.setString(3,custID);
	    	  
	    	   //query database
	    	   int result = insertOrder.executeUpdate();
	    	   System.out.println("executed insert statement");
	    	   
	    	   
	    	  }
	    	  catch(SQLException sqlExcept){
	    		  sqlExcept.printStackTrace();
	    		  try{
	   	    	   dbConnection.rollback();}
	   	    	   catch(SQLException sqlEx){
	   	    		   sqlEx.printStackTrace();
	   	    		   
	   	    	   }
	   	    	   
	    		  
	    	  }
	    	  //now proceed to insert parts of the order
	    	  try{
	    		  System.out.println("enter part order to order line table ");
	    		 //loop through orderline information
	    		  for (int i=0;i<orderLineInfo.partData.length;i++) //number of rows in the grid
	    		   //retrieve data from one row at a time
	    		  { 
	    			  
	    			  OrderLineData rowData = orderLineInfo.getOrderLineData(i);
	    			 if (rowData==null && i ==0){  //no data was entered for the orderline
	    				                           //connected with this order
	    				 dbConnection.rollback();
	    			 }
	    			 else if (rowData == null){   //no additional lines in the orderline
	    				 break;
	    			 }
	    			   //now prepare record to insert 
	    			  System.out.println(" part order " +
	    					  orderID +
		    				  rowData.getPartID() + " " +
		    				  rowData.getQty() + " " +
		    				  rowData.getPrice());
	    			  
	    			insertOrderLine.setString(1, orderID);
	    		    insertOrderLine.setString(2,rowData.getPartID());
	    		    insertOrderLine.setInt(3, rowData.getQty());
	    		    insertOrderLine.setDouble(4, rowData.getPrice());
	    		    //now execute the statement to insert into database
	    		    int result = insertOrderLine.executeUpdate();
	    	               
	    		   }
	    			   
	    		   }
	    	 
	    	  catch(SQLException ex){
	    		  ex.printStackTrace();
	    		  try{
	    		   dbConnection.rollback();}
	    		  catch(SQLException sqlE){
	    			  sqlE.printStackTrace();
	    			  
	    		  }
	    	  }
	    	  try{
	    		  //commit the order - all records were inserted properly
	    	  dbConnection.commit();}
	    	  catch(SQLException except){
	    		  except.printStackTrace();
	    		  try{
	    			  dbConnection.rollback();
	    			  
	    		  }
	    		  catch(SQLException sqle){
	    			  sqle.printStackTrace();
	    			  
	    		  }
	    	  }
			  
		  }
	  }
	  
  }
}
