package billOrganizerApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;

import randomAccessStudentDataLLIndex.LLExtIterator;
import doubleLinkedList.ListEmptyException;
import linkedLists.DuplicateDataException;
import linkedLists.NotFoundException;

public class BillOrganizer implements Serializable {

	private SortedLinkedList<Bill> sortedLL = new SortedLinkedList<Bill>();
	private PriorityQueue<Bill> dateQueue;
	private PriorityQueue<Bill> amountQueue;
	private PriorityQueue<Bill> typeQueue;

	public BillOrganizer() {
		this.dateQueue = new PriorityQueue<Bill>(new BillDateComparator());
		this.amountQueue = new PriorityQueue<Bill>(new BillAmountComparator());
		this.typeQueue = new PriorityQueue<Bill>(new BillTypeComparator());
	}

	public BillOrganizer(Scanner filename) throws DuplicateDataException,
			randomAccessExceptions.NotFoundException {

		this.dateQueue = new PriorityQueue<Bill>(new BillDateComparator());
		this.amountQueue = new PriorityQueue<Bill>(new BillAmountComparator());
		this.typeQueue = new PriorityQueue<Bill>(new BillTypeComparator());

		while (filename.hasNext()) {
			insert(new Bill(filename));
		}
	}

	@SuppressWarnings("unchecked")
	public BillOrganizer(String filename) throws ClassNotFoundException,
			IOException, DuplicateDataException
			 {

		

		ObjectInputStream input = new ObjectInputStream(new FileInputStream(
				filename));
		this.sortedLL = (SortedLinkedList<Bill>) input.readObject();
		input.close();
		
		this.dateQueue = new PriorityQueue<Bill>(new BillDateComparator());
		this.amountQueue = new PriorityQueue<Bill>(new BillAmountComparator());
		this.typeQueue = new PriorityQueue<Bill>(new BillTypeComparator());
		
		LLExtIterator<Bill> iter = sortedLL.iterator();

		while (iter.hasNext()) {
			Bill b = iter.next();
			dateQueue.enqueue(b);
			amountQueue.enqueue(b);
			typeQueue.enqueue(b);
		}

	}

	public void insert(Bill bill) throws DuplicateDataException{
		sortedLL.insert(bill);
		dateQueue.enqueue(bill);
		amountQueue.enqueue(bill);
		typeQueue.enqueue(bill);

	}

	public Bill payNextBill(BillCriteria criteria) throws ListEmptyException,
			randomAccessExceptions.NotFoundException {
		Bill payBill = null;
		if (criteria == BillCriteria.BILLDUEDATE) {
			payBill = dateQueue.dequeue();
			amountQueue.remove(payBill);
			typeQueue.remove(payBill);
		} else if (criteria == BillCriteria.BILLAMOUNT) {
			payBill = amountQueue.dequeue();
			dateQueue.remove(payBill);
			typeQueue.remove(payBill);
		} else if (criteria == BillCriteria.BILLTYPE) {
			payBill = typeQueue.dequeue();
			dateQueue.remove(payBill);
			amountQueue.remove(payBill);
		}

		sortedLL.remove(payBill);

		return payBill;

	}

	public Bill payNextBillByID(Integer ID) throws NotFoundException,
			randomAccessExceptions.NotFoundException {

		LLExtIterator<Bill> iter = sortedLL.iterator();
		while (iter.hasNext()) {
			Bill bill = iter.next();
			if (bill.getID().equals(ID)) {
				dateQueue.remove(bill);
				amountQueue.remove(bill);
				typeQueue.remove(bill);
				sortedLL.remove(bill);
				return bill;
			}
		}

		throw new NotFoundException();

	}

	public void closeOrganizer(String filename) throws FileNotFoundException,
			IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				filename));
		out.writeObject(sortedLL);
		out.close();

	}

	public double totalBills() {

		double total = 0.0;
		LLExtIterator<Bill> iter = sortedLL.iterator();
		while (iter.hasNext()) {
			total += iter.next().getAmount();
		}

		return total;
	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		LLExtIterator<Bill> iter = sortedLL.iterator();

		while (iter.hasNext()) {
			info.append("\n");
			info.append(iter.next().toString());
		}

		return info.toString();
	}

	public LLExtIterator<Bill> iteratorByDate() {
		LLExtIterator<Bill> iter = dateQueue.iterator();
		return iter;

	}

	public LLExtIterator<Bill> iteratorByAmount() {
		LLExtIterator<Bill> iter = amountQueue.iterator();
		return iter;
	}

	public LLExtIterator<Bill> iteratorByType() {
		LLExtIterator<Bill> iter = typeQueue.iterator();
		return iter;
	}

}
