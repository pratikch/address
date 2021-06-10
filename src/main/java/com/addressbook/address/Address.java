package com.addressbook.address;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
public class Address {

	@Id
	public String id;
	
	@TextIndexed(weight=4)
	public String line1;
	@TextIndexed(weight=3)
	public String line2;
	@TextIndexed(weight=2)
	public String city;
	@TextIndexed(weight=1)
	public String state;
	@TextIndexed(weight=2)
	public String zip;

	public Address() {
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Address(String line1, String line2, String city, String state, String zip) {
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	@Override
	public String toString() {
		return String.format("Address[id=%s, line1='%s', line2='%s', city='%s', state='%s', zip='%s']", id, line1,
				line2, city, state, zip);
	}

}
