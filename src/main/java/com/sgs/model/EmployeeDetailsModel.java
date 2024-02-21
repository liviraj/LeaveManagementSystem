package com.sgs.model;

import java.sql.Date;

public class EmployeeDetailsModel {
	private int employeeId;
	private String name;
	private Date dob;
	private String gender;
	private String designation;
	private int experiance;
	private String contactNumber;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getExperiance() {
		return experiance;
	}

	public void setExperiance(int experiance) {
		this.experiance = experiance;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "EmployeeDetailsModel [employeeId=" + employeeId + ", name=" + name + ", dob=" + dob + ", gender="
				+ gender + ", designation=" + designation + ", experiance=" + experiance + ", contactNumber="
				+ contactNumber + "]";
	}

}
