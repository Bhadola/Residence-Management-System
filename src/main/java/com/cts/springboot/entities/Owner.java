package com.cts.springboot.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Owner {

	@Id
	@GeneratedValue
	private int Id;
	private String first;
	private String last;
	private String password;
	private String phone;
	private String alertMsg;
	private String imgURL;
	private int propertybought;

	@OneToMany(mappedBy = "ownerinprop")
	private List<Property> properties;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public int getPropertybought() {
		return propertybought;
	}

	public void setPropertybought(int propertybought) {
		this.propertybought = propertybought;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Owner [Id=" + Id + ", first=" + first + ", last=" + last + ", password=" + password + ", phone=" + phone
				+ ", alertMsg=" + alertMsg + ", imgURL=" + imgURL + ", propertybought=" + propertybought
				+ ", properties=" + properties + "]";
	}

}
