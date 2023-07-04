package com.cts.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Property {
	@Id
	@GeneratedValue
	private int id;
	private String propertyType;
	private double rentAmt;
	private String location;
	private int owned;
	private int leaseDuration;
	private String imgURL;

	@OneToOne
	@JoinColumn(name = "tenant_fk") // renames the fk
	private Tenant tenantinprop;

	@ManyToOne
	@JoinColumn(name = "owner_fk")
	private Owner ownerinprop;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public double getRentAmt() {
		return rentAmt;
	}

	public void setRentAmt(double rentAmt) {
		this.rentAmt = rentAmt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getOwned() {
		return owned;
	}

	public void setOwned(int owned) {
		this.owned = owned;
	}

	public int getLeaseDuration() {
		return leaseDuration;
	}

	public void setLeaseDuration(int leaseDuration) {
		this.leaseDuration = leaseDuration;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public Tenant getTenantinprop() {
		return tenantinprop;
	}

	public void setTenantinprop(Tenant tenantinprop) {
		this.tenantinprop = tenantinprop;
	}

	public Owner getOwnerinprop() {
		return ownerinprop;
	}

	public void setOwnerinprop(Owner ownerinprop) {
		this.ownerinprop = ownerinprop;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", propertyType=" + propertyType + ", rentAmt=" + rentAmt + ", location="
				+ location + ", owned=" + owned + ", leaseDuration=" + leaseDuration + ", imgURL=" + imgURL + "]";
	}



}
