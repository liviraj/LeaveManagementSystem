package com.sgs.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sgs.dbconnection.DbConnection;
import com.sgs.model.LeaveDetailsModel;
import com.sgs.model.MarkViewModel;
import com.sgs.model.EmployeeDetailsModel;
import com.sgs.model.SubjectDetails;

public class LeaveService {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public ArrayList<SubjectDetails> getSubjectByDepartment(String subject) throws ClassNotFoundException {
		ArrayList<SubjectDetails> studentDetailsList = new ArrayList<SubjectDetails>();
		try {
			con = DbConnection.getConnection();
			st = (Statement) con.createStatement();
			String query = "select * from subjectdetails where departmnet='" + subject + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				SubjectDetails subjectDetails = new SubjectDetails();
				subjectDetails.setSubjectId(rs.getInt("subjectId"));
				subjectDetails.setSubjectCode(rs.getString("subjectCode"));
				subjectDetails.setSubjectName(rs.getString("subjectName"));
				subjectDetails.setDepartment(rs.getString("departmnet"));
				subjectDetails.setSemesterId(rs.getInt("semesterId"));

				studentDetailsList.add(subjectDetails);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentDetailsList;
	}
	
	public int saveMarkDetails(List<LeaveDetailsModel> markDetails) {
		int status = 0;
		try {
			con = DbConnection.getConnection();
			for (LeaveDetailsModel mark : markDetails) {
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(
						"insert into markdetails(studentId,semesterId,department,subjectId,mark) values(?,?,?,?,?)");
				ps.setInt(1, mark.getStudentId());
				ps.setInt(2, mark.getSemesterId());
				ps.setString(3, mark.getDepartment());
				ps.setInt(4, mark.getSubjectId());
				ps.setInt(5, mark.getMark());

				status = ps.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public List<MarkViewModel> findMarksByStudentId(int studentId) {
		List<MarkViewModel> markViewList = new ArrayList<MarkViewModel>();
		try {
			String sql = "select distinct stdD.rollNo, stdD.name, stdD.department, subD.semesterId, md.mark, subD.subjectName\r\n"
					+ " from markdetails md, subjectdetails subD, studentdetails stdD \r\n"
					+ " where md.studentId = stdD.studentId and\r\n"
					+ " subD.subjectId = md.subjectId and\r\n"
					+ " stdD.studentid = '" + studentId + "';";
			
			con = DbConnection.getConnection();
			st = (Statement) con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				MarkViewModel markViewModel = new MarkViewModel();
				markViewModel.setRollNo(rs.getString("rollNo"));
				markViewModel.setName(rs.getString("name"));
				markViewModel.setDepartment(rs.getString("department"));
				markViewModel.setSemesterId(rs.getInt("semesterId"));
				markViewModel.setMark(rs.getInt("mark"));
				markViewModel.setSubjectName(rs.getString("subjectName"));
				
				markViewList.add(markViewModel);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return markViewList;
	}

}
