package pharmacy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collections;

public class PharmacyList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CompanyCodeIndex> codes;
	private ArrayList<CompanyNameIndex> names;
	transient private RandomAccessFile companyDataFile;
	

	public PharmacyList(String dataFile) throws FileNotFoundException {
		this.codes = new ArrayList<CompanyCodeIndex>();
		this.names = new ArrayList<CompanyNameIndex>();
		this.companyDataFile = new RandomAccessFile(dataFile, "rw");

	}

	//connects to the random access file
	public void connectToData(String dataFile) throws FileNotFoundException {
		this.companyDataFile = new RandomAccessFile(dataFile, "rw");
	}

	//add a company
	public void addCompany(String companyCode, String phone, String name)
			throws InvalidDataException, DuplicateDataException, IOException {

		PharmaceuticalCo p = new PharmaceuticalCo(companyCode, phone, name);

		Long recLocation = this.companyDataFile.length();

		p.writeToFile(companyDataFile, recLocation);

		for (CompanyCodeIndex c : this.codes) {
			for (CompanyNameIndex co : this.names) {
				if (c.getCompanyCode().equalsIgnoreCase(companyCode)
						|| co.getCompanyName().equalsIgnoreCase(name)) {
					throw new DuplicateDataException();
				}
			}
		}
		//add the company to the code index, setting isActive to true
		this.codes.add(new CompanyCodeIndex(companyCode, recLocation, true));
		sortCodeIndex(); // sort the code index for more effecient sorting
		//add the company to the name index, setting isActive() to true
		this.names.add(new CompanyNameIndex(name, recLocation, true));
		sortNameIndex(); // sort the name index
	}

	//remove a company
	public void removeCompany(String companyCode, String name)
			throws NotFoundException, IOException {

		for (CompanyCodeIndex c : codes) {
			if (c.getCompanyCode().equalsIgnoreCase(companyCode)) {
				if (c.isActive()) {
					c.setIsActive(false);
				} else {
					throw new NotFoundException();
				}
			}

		}

		for (CompanyNameIndex co : names) {
			if (co.getCompanyName().equalsIgnoreCase(name)) {
				if (co.isActive()) {
					co.setIsActive(false);
				} else {
					throw new NotFoundException();
				}

			}
		}

	}

	//find a company based on a company code
	public PharmaceuticalCo findCompanyCode(String companyCode)
			throws NotFoundException, IOException {

		for (CompanyCodeIndex c : codes) {
			if (c.getCompanyCode().equalsIgnoreCase(companyCode)) {
				if (c.isActive()) {
					PharmaceuticalCo p = new PharmaceuticalCo(companyDataFile,
							c.getRecLocation());
					return p;
				} else {
					throw new NotFoundException();
				}
			}
		}
		throw new NotFoundException();

	}
	
	
	//find a company based on a name
	public PharmaceuticalCo findCompanyName(String name) throws IOException,
			NotFoundException {
		for (CompanyNameIndex c : names) {
			if (c.getCompanyName().equalsIgnoreCase(name)) {
				if (c.isActive()) {
					PharmaceuticalCo p = new PharmaceuticalCo(companyDataFile,
							c.getRecLocation());
					return p;
				} else {
					throw new NotFoundException();
				}
			}
		}
		throw new NotFoundException();
	}

	//modify a company phone number
	public void modifyCompanyPhone(String code, String newPhone)
			throws IOException, InvalidDataException, NotFoundException {
		Long location;
		for (CompanyCodeIndex c : codes) {

			if (c.getCompanyCode().equalsIgnoreCase(code)) {
				if (c.isActive()) {
					location = c.getRecLocation();
					PharmaceuticalCo p = new PharmaceuticalCo(companyDataFile,
							location);
					p.setPhoneNumber(newPhone);
					p.writeToFile(companyDataFile, location);
				}
				else{
					throw new NotFoundException();
				}
			}
		}
	}

	//get company information
	public String getCompanyInfo() throws IOException, NotFoundException {
		ArrayList<PharmaceuticalCo> ph = new ArrayList<PharmaceuticalCo>();
		for (CompanyNameIndex c : this.names) {
			if (c.isActive()) {
				PharmaceuticalCo p = new PharmaceuticalCo(companyDataFile,
						c.getRecLocation());
				ph.add(p);
			}
		}
		return ph.toString();
	}

	public void sortCodeIndex() {
		Collections.sort(codes);
	}

	public void sortNameIndex() {
		Collections.sort(names);
	}

	public void closeFiles() throws IOException {
		this.companyDataFile.close();
	}

}
