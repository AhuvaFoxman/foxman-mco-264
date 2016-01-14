package pharmacy;

import java.io.Serializable;

public class CompanyNameIndex implements Comparable<CompanyNameIndex>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyName;
	private Long recLocation;
	private boolean isActive;
	
	public CompanyNameIndex(String name, Long location) throws InvalidDataException{
		this(name,location,true);
	}
	
	public CompanyNameIndex(String name, Long location, Boolean active) throws InvalidDataException{
		if(name == null || location == null || active == null){
			throw new InvalidDataException();
		}
		
		this.companyName = name;
		this.recLocation = location;
		this.isActive = active;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Long getRecLocation() {
		return recLocation;
	}

	public boolean isActive() {
		return isActive;
	}
	
	public void setIsActive(boolean active){
		this.isActive = active;
	}
	public int compareTo(CompanyNameIndex other){
		return this.companyName.compareTo(other.companyName);
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyNameIndex other = (CompanyNameIndex) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		return true;
	}
	
	public String toString(){
		StringBuffer info = new StringBuffer();
			info.append("Company Name Index: ");
			info.append("\n Company Name: " + this.companyName);
			info.append("\n Record Location: " + this.recLocation);
			
			return info.toString();
		}
	}
	
	


