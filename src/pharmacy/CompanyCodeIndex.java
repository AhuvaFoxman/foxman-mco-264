package pharmacy;

import java.io.Serializable;

public class CompanyCodeIndex implements Comparable<CompanyCodeIndex>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyCode;
	private Long recLocation;
	private boolean isActive;
	
	public CompanyCodeIndex(String companyCode, Long location) throws InvalidDataException{
		this(companyCode, location, true); //when we set it up it is active
		
	}
	
	public CompanyCodeIndex(String companyCode, Long location, Boolean isActive) throws InvalidDataException{
		if(companyCode == null || location == null || isActive == null){
			throw new InvalidDataException();
		}
		this.companyCode = companyCode;
		this.recLocation = location;
		this.isActive = isActive;
	}

	public String getCompanyCode() {
		return companyCode;
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
	public int compareTo(CompanyCodeIndex company){
		return this.companyCode.compareTo(company.companyCode);
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyCodeIndex other = (CompanyCodeIndex) obj;
		if (companyCode == null) {
			if (other.companyCode != null)
				return false;
		} else if (!companyCode.equals(other.companyCode))
			return false;
		return true;
	}
	
	public String toString(){
		StringBuffer info = new StringBuffer();
		info.append("Company Code Index: ");
		info.append("\n Company Code: " + this.companyCode);
		info.append("\n Record Location: " + this.recLocation);
		
		return info.toString();
	}
	
	
	
	

}
