package com.sgs.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sgs.model.LeaveDetailsModel;
import com.sgs.model.MarkViewModel;
import com.sgs.model.StudentDetailsModel;
import com.sgs.model.SubjectDetails;
import com.sgs.service.LeaveService;
import com.sgs.service.EmployeeService;

@WebServlet("/StudentMarkController")
public class LeaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String addMarksPage = "AddMarks.jsp";
	private static final String vieMarksPage = "ViewMarks.jsp";
	private static final String viewStudent = "view_sgs.jsp";

	RequestDispatcher requestDispatcher = null;
	private EmployeeService studentService;
	private LeaveService marksService;

	public LeaveController() {
		super();
		this.studentService = new EmployeeService();
		this.marksService = new LeaveService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String check = (String) session.getAttribute("username");
		String action = request.getParameter("action");
		String navigation = vieMarksPage;

		if (action.equals("addMark")) {
			int studentId = Integer.parseInt(request.getParameter("studentId"));
			try {
				StudentDetailsModel student = studentService.getById(studentId);
				ArrayList<SubjectDetails> subjectDetails = marksService.getSubjectByDepartment(student.getDepartment());
				Map<Integer, List<SubjectDetails>> subjectsBySemester = subjectDetails.stream()
						.collect(Collectors.groupingBy(SubjectDetails::getSemesterId));

				request.setAttribute("student", student);
				request.setAttribute("subjectDetails", subjectsBySemester);
				navigation = addMarksPage;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("viewMark")) {
			int studentId = Integer.parseInt(request.getParameter("studentId"));
			List<MarkViewModel> studentMarks = marksService.findMarksByStudentId(studentId);

			int sumOfMarks = studentMarks.stream().mapToInt(MarkViewModel::getMark).sum();
			float percentage = sumOfMarks/studentMarks.size();
			float cgpa = (float) (percentage/ 9.5);
			String formattedCgpa = String.format("%.2f", cgpa);
			
			navigation = vieMarksPage;
			request.setAttribute("percentage", percentage);
			request.setAttribute("cgpa", formattedCgpa);
			request.setAttribute("marks", studentMarks);

		} else if (action.equals("cancel")) {
			ArrayList<StudentDetailsModel> studentModel = new ArrayList<StudentDetailsModel>();
			EmployeeService studentService = new EmployeeService();
			try {
				studentModel = studentService.getAllList();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			request.setAttribute("details", studentModel);
			navigation = viewStudent;
		}

		requestDispatcher = request.getRequestDispatcher(navigation);
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String check = (String) session.getAttribute("username");
		String action = request.getParameter("submit");
		String navigation = vieMarksPage;

		if (action.equals("cancel")) {
			ArrayList<StudentDetailsModel> studentModel = new ArrayList<StudentDetailsModel>();
			EmployeeService studentService = new EmployeeService();
			try {
				studentModel = studentService.getAllList();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			request.setAttribute("details", studentModel);
			navigation = viewStudent;
		} else if (action.equals("submit")) {
			String[] subjectInfoArray = request.getParameterValues("subjectInfo");

			List<LeaveDetailsModel> markDetails = new ArrayList<LeaveDetailsModel>();
			for (String subject : subjectInfoArray) {
				LeaveDetailsModel mark = new LeaveDetailsModel();
				String[] markSplit = subject.split(",");

				mark.setMark(Integer.parseInt(markSplit[6]));
				mark.setDepartment(markSplit[5]);
				mark.setSemesterId(Integer.parseInt(markSplit[1]));
				mark.setStudentId(Integer.parseInt(markSplit[4]));
				mark.setSubjectId(Integer.parseInt(markSplit[0]));

				markDetails.add(mark);
			}
			int result = marksService.saveMarkDetails(markDetails);
			if (result == 1) {
				navigation = viewStudent;
			}
			ArrayList<StudentDetailsModel> studentList = null;
			try {
				studentList = studentService.getAllList();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("details", studentList);
			request.setAttribute("msg", "record saved successfully");
		}

		requestDispatcher = request.getRequestDispatcher(navigation);
		requestDispatcher.forward(request, response);
	}
}
