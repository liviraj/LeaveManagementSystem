package com.sgs.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sgs.dbconnection.DbConnection;
import com.sgs.model.LeaveDetailsModel;
import com.sgs.model.LeaveHistoryModel;

public class LeaveService {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public int saveLeaveDetails(LeaveDetailsModel details) {
		int status = 0;
		try {
			con = DbConnection.getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(
					"insert into leavedetails(reason,leaveFrom,leaveTo,leaveType,leaveStatus,employeeId) values(?,?,?,?,?,?)");
			ps.setString(1, details.getReason());
			ps.setDate(2, details.getLeaveFrom());
			ps.setDate(3, details.getLeaveTo());
			ps.setString(4, details.getLeaveType());
			ps.setString(5, details.getLeaveStatus());
			ps.setInt(6, details.getEmployeeId());

			status = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<LeaveHistoryModel> findLeaveHistoryById(int empId) {
		List<LeaveHistoryModel> dataList = new ArrayList<LeaveHistoryModel>();
		try {
			String sql = "select * from leavedetails where employeeId = '" + empId + "';";

			con = DbConnection.getConnection();
			st = (Statement) con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				LeaveHistoryModel viewModel = new LeaveHistoryModel();
				viewModel.setLeaveId(rs.getInt("leaveId"));
				viewModel.setReason(rs.getString("reason"));
				viewModel.setLeaveFrom(rs.getDate("leaveFrom"));
				viewModel.setLeaveTo(rs.getDate("leaveTo"));
				viewModel.setLeaveType(rs.getString("leaveType"));
				viewModel.setLeaveStatus(rs.getString("leaveStatus"));
				viewModel.setEmployeeId(rs.getInt("employeeId"));

				dataList.add(viewModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public int updateLeaveType(int leaveId, String leaveType) {
		int status = 0;
		try {
			con = DbConnection.getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(
					"update leavedetails set leaveType=? where leaveId='"
							+ leaveId + "'");
			ps.setString(1, leaveType);
			
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
