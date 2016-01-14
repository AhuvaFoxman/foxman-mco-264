package billOrganizerApp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

import summerHomework.InvalidDataException;

public class Bill implements Serializable, Comparable<Bill>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vendorName;
	private Double amount;
	private GregorianCalendar dueDate;
	private BillType type;
	private Integer ID;

	public static Integer lastID = 0;

	public Bill(String name, Double amount, String date, BillType type) {

		this.ID = ++lastID;

		if (name == null || amount == null || date == null || type == null) {
			throw new NullPointerException();
		}

		this.vendorName = name;
		this.amount = amount;

		String[] dateArray = date.split("/");
		int month = Integer.parseInt(dateArray[0]);
		int day = Integer.parseInt(dateArray[1]);
		int year = Integer.parseInt(dateArray[2]);

		this.dueDate = new GregorianCalendar(year, month - 1, day);

		this.type = type;

	}

	public Bill(Scanner filename) {

		this.ID = ++lastID; // set the next available ID

		this.vendorName = filename.next();
		this.amount = filename.nextDouble();
		String date = filename.next();
		this.type = BillType.valueOf(filename.next());
		
		String[] dateArray = date.split("/");
		int month = Integer.parseInt(dateArray[0]);
		int day = Integer.parseInt(dateArray[1]);
		int year = Integer.parseInt(dateArray[2]);

		this.dueDate = new GregorianCalendar(year, month - 1, day);
		
	}

	public String getVendorName() {
		return vendorName;
	}

	public Double getAmount() {
		return amount;
	}

	public String getDueDate() {

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return format.format(this.dueDate.getTime());
	}

	public BillType getType() {
		return type;
	}

	public Integer getID() {
		return ID;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setType(BillType type) {
		this.type = type;
	}

	public int compareTo(Bill other) {
		return this.ID.compareTo(other.ID);
	}
	
	public String toString(){
		StringBuffer info = new StringBuffer();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		info.append("Bill: ");
		info.append("ID: " + this.ID);
		info.append("\nVendor Name: " + this.vendorName);
		info.append("\nAmount " + this.amount);
		info.append("\nDue Date " + format.format(this.dueDate.getTime()));
		info.append("\nBill Type " + this.type);
		
		return info.toString();
	
		
	}

}
