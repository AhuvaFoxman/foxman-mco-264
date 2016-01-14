package pharmacy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class PharmaceuticalCo {

	private String companyCode;
	private String companyName;
	private String phoneNumber;

	//constructor that accepts all three peices of data to set it up
	public PharmaceuticalCo(String companyCode, String phone, String name)
			throws InvalidDataException {
		if (companyCode == null || name == null || phone == null) {
			throw new InvalidDataException();
		}
		this.companyCode = companyCode;
		this.companyName = name;
		setPhoneNumber(phone);
	}

	//constructor that accepts a Scanner file as a parameter
	public PharmaceuticalCo(Scanner filename)  {
		companyCode = filename.next();
		phoneNumber = filename.next();
		companyName = filename.nextLine().trim();
		
	}

	//constructor that accepts a random access file and a location
	public PharmaceuticalCo(RandomAccessFile random, Long location)
			throws IOException {
		random.seek(location);

		companyCode = random.readUTF();
		phoneNumber = random.readUTF();
		companyName = random.readUTF();

	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	//do we need to verify that it is valid
	public void setPhoneNumber(String phone) throws InvalidDataException{
	if (phone.length() != 10){
		throw new InvalidDataException();
	}
	
	this.phoneNumber = phone;
		
	}
	
	//write to the file
	public Long writeToFile(RandomAccessFile random, Long location) throws IOException{
		random.seek(location);
		random.writeUTF(String.format("%-4s", companyCode));
		random.writeUTF(String.format("%-10s", phoneNumber));
		random.writeUTF(String.format("%-25s", companyName));
		
		
		
		return location;
	}
	
	public int compareTo(PharmaceuticalCo pharmacy){
		return this.companyCode.compareTo(pharmacy.companyCode);
		
	}
	
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		else{
			if (obj instanceof PharmaceuticalCo){
				return false;
			}
			PharmaceuticalCo other = (PharmaceuticalCo)obj;
			return companyCode.equals(other.companyCode);
			
			}
		}

	
	
	public String toString(){
		StringBuffer info = new StringBuffer();
		info.append("\n Pharmacy: \n");
		info.append(" Company code: " + this.companyCode);
		info.append("\n Company Name: " + this.companyName);
		info.append("\n Phone Number: " + this.phoneNumber);
		
		return info.toString();
	}

}
