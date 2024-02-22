package com.sgs.model;

import java.sql.Date;

public class LeaveHistoryModel {
	private int leaveId;
	private String reason;
	private Date leaveFrom;
	private Date leaveTo;
	private String leaveType;
	private String leaveStatus;
	private int employeeId;

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getLeaveFrom() {
		return leaveFrom;
	}

	public void setLeaveFrom(Date leaveFrom) {
		this.leaveFrom = leaveFrom;
	}

	public Date getLeaveTo() {
		return leaveTo;
	}

	public void setLeaveTo(Date leaveTo) {
		this.leaveTo = leaveTo;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "LeaveHistoryModel [leaveId=" + leaveId + ", reason=" + reason + ", leaveFrom=" + leaveFrom
				+ ", leaveTo=" + leaveTo + ", leaveType=" + leaveType + ", leaveStatus=" + leaveStatus + ", employeeId="
				+ employeeId + "]";
	}

}
