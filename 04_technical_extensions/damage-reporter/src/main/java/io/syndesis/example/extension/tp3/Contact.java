package io.syndesis.example.extension.tp3;

public class Contact {

	private String name;
	private String phoneNumber;
	private String company;

	public Contact(String name, String phoneNumber, String company) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
