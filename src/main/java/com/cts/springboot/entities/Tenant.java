
package com.cts.springboot.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Tenant {
	@Id
	@GeneratedValue
	private int Id;
	private String first;
	private String last;
	private String password;
	private String phone;
	private String alertMsg;
	private String pancard;
	private String gender;
	private String imgURL;
	private int propertybought;

	@OneToOne(mappedBy = "tenantinprop")
	private Property property;

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

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "Tenant [Id=" + Id + ", first=" + first + ", last=" + last + ", password=" + password + ", phone="
				+ phone + ", alertMsg=" + alertMsg + ", pancard=" + pancard + ", gender=" + gender + ", imgURL="
				+ imgURL + ", propertybought=" + propertybought + ", property=" + property + "]";
	}

}