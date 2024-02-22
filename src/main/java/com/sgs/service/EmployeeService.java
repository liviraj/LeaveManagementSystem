package com.sgs.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sgs.dbconnection.DbConnection;
import com.sgs.model.EmployeeDetailsModel;

public class EmployeeService {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public ArrayList<EmployeeDetailsModel> getAllList() throws ClassNotFoundException {
		ArrayList<EmployeeDetailsModel> studentList = new ArrayList<EmployeeDetailsModel>();
		try {
			con = DbConnection.getConnection();
			st = (Statement) con.createStatement();
			String query = "select * from employee";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				EmployeeDetailsModel model = new EmployeeDetailsModel();
				model.setEmployeeId(rs.getInt("employeeId"));
				model.setName(rs.getString("name"));
				model.setGender(rs.getString("gender"));
				model.setDob(rs.getDate("dob"));
				model.setDesignation(rs.getString("designation"));
				model.setExperiance(rs.getInt("experiance"));
				model.setContactNumber(rs.getString("contactNumber"));
				model.setEmployeeCode(rs.getString("employeeCode"));

				studentList.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentList;
	}

	public EmployeeDetailsModel getById(int id) throws ClassNotFoundException {
		EmployeeDetailsModel model = new EmployeeDetailsModel();
		try {
			con = DbConnection.getConnection();
			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("select * from employee where employeeId='" + id + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				model.setEmployeeId(rs.getInt("employeeId"));
				model.setName(rs.getString("name"));
				model.setGender(rs.getString("gender"));
				model.setDob(rs.getDate("dob"));
				model.setDesignation(rs.getString("designation"));
				model.setExperiance(rs.getInt("experiance"));
				model.setContactNumber(rs.getString("contactNumber"));
				model.setEmployeeCode(rs.getString("employeeCode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

	public int deleteById(int id) throws ClassNotFoundException, SQLException {
		int status = 0;
		String query = "delete from employee where employeeId=" + id + "";
		con = DbConnection.getConnection();
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
		status = ps.executeUpdate();
		return status;
	}

	public int insertDetails(EmployeeDetailsModel model) {
		int status = 0;
		try {
			con = DbConnection.getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(
					"insert into employee(employeeCode,name,dob,designation,experiance,contactNumber,gender) values(?,?,?,?,?,?,?)");
			ps.setString(1, model.getEmployeeCode());
			ps.setString(2, model.getName());
			ps.setDate(3, model.getDob());
			ps.setString(4, model.getDesignation());
			ps.setInt(5, model.getExperiance());
			ps.setString(6, model.getContactNumber());
			ps.setString(7, model.getGender());

			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public int update(EmployeeDetailsModel model) throws ClassNotFoundException {
		int status = 0;
		try {
			con = DbConnection.getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(
					"update employee set employeeCode=?,name=?,dob=?,designation=?,experiance=?,contactNumber=?,gender=?  where employeeId='"
							+ model.getEmployeeId() + "'");
			ps.setString(1, model.getEmployeeCode());
			ps.setString(2, model.getName());
			ps.setDate(3, model.getDob());
			ps.setString(4, model.getDesignation());
			ps.setInt(5, model.getExperiance());
			ps.setString(6, model.getContactNumber());
			ps.setString(7, model.getGender());
			
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public String check(String employeeCode) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DbConnection.getConnection();
			st = (Statement) con.createStatement();
			rs = st.executeQuery("select employeeCode from employee where  employeeCode = '" + employeeCode + "'");
			if (rs.next()) {
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "failed";
	}
}
