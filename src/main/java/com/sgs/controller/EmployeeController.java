package com.sgs.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sgs.model.EmployeeDetailsModel;
import com.sgs.service.EmployeeService;

@WebServlet("/StudentController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REGISTER_EMPLOYEE_PAGE = "RegisterEmployee.jsp";
	private static final String VIEW_EMPLOYEE_PAGE = "ViewEmployee.jsp";
	private static final String LOGIN = "login.jsp";
	RequestDispatcher requestDispatcher = null;

	public EmployeeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String check = (String) session.getAttribute("username");
		String action = request.getParameter("action");
		String navigation = "";
		if (action.equals("register")) {
			if (check != null) {
				request.setAttribute("name", "save");
				navigation = REGISTER_EMPLOYEE_PAGE;
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
		} else if (action.equals("cancel")) {
			navigation = "home.jsp";
		} else if (action.equals("update")) {
			if (check != null) {
				EmployeeService serverService = new EmployeeService();
				EmployeeDetailsModel studentModel = new EmployeeDetailsModel();
				int studentId = Integer.parseInt(request.getParameter("studentId"));
				try {
					studentModel = serverService.getById(studentId);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("name", "update");
				request.setAttribute("details", studentModel);
				navigation = REGISTER_EMPLOYEE_PAGE;
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
		} else if (action.equals("delete")) {
			String confirm = request.getParameter("confirm");
			if (check != null) {
				if (!"false".equals(confirm)) {
					EmployeeService studentService = new EmployeeService();
					int studentId = Integer.parseInt(request.getParameter("studentId"));
					try {
						int reult = studentService.deleteServer(studentId);
						if (reult == 1) {
							ArrayList<EmployeeDetailsModel> studentList = new ArrayList<EmployeeDetailsModel>();
							EmployeeService studentService2 = new EmployeeService();
							try {
								studentList = studentService2.getStudentList2();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
							request.setAttribute("details", studentList);
							navigation = VIEW_EMPLOYEE_PAGE;
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					requestDispatcher = request.getRequestDispatcher(LOGIN);
				}
			}
		} else if (action.equals("add")) {
			if (check != null) {
				request.setAttribute("name", "save");
				navigation = REGISTER_EMPLOYEE_PAGE;
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
		} else {
			if (check != null) {
				ArrayList<EmployeeDetailsModel> studentList = new ArrayList<EmployeeDetailsModel>();
				EmployeeService studentService = new EmployeeService();
				try {
					studentList = studentService.getAllList();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("details", studentList);
				navigation = VIEW_EMPLOYEE_PAGE;
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
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
		ArrayList<EmployeeDetailsModel> arrayList = new ArrayList<EmployeeDetailsModel>();
		if (action.equals("save")) {
			if (check != null) {
				EmployeeDetailsModel studentModel = new EmployeeDetailsModel();
				EmployeeService studentService = new EmployeeService();
				String rollNo = request.getParameter("rollNo");
				String serverName = request.getParameter("name");
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dobReq = null;
				try {
					String reqDate = request.getParameter("dob");
					dobReq = dateFormat.parse(reqDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date dobSql = new Date(dobReq.getTime());
				
				String gender = request.getParameter("gender");
				String fatherName = request.getParameter("fatherName");


				int semester = Integer.parseInt(request.getParameter("semester"));
				String department = request.getParameter("department");

				studentModel.setName(serverName);
				studentModel.setGender(gender);
				studentModel.setFatherName(fatherName);
				studentModel.setDob(dobSql);
				studentModel.setSemester(semester);
				studentModel.setDepartment(department);
				studentModel.setRollNo(rollNo);

				String result = studentService.rollNoCheck(rollNo);
				arrayList.add(studentModel);
				if (result.equals("success")) {
					request.setAttribute("name", "save");
					request.setAttribute("msg", "Roll No Already Exist");
					request.setAttribute("details", studentModel);
					requestDispatcher = request.getRequestDispatcher(REGISTER_EMPLOYEE_PAGE);
				} else {
					int status = studentService.insertDetails(studentModel);
					if (status > 0) {
						ArrayList<EmployeeDetailsModel> studentList = new ArrayList<EmployeeDetailsModel>();
						EmployeeService employeService2 = new EmployeeService();
						try {
							studentList = employeService2.getStudentList2();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						request.setAttribute("details", studentList);
						requestDispatcher = request.getRequestDispatcher(VIEW_EMPLOYEE_PAGE);
					}
				}
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
		} else if (action.equals("Cancel")) {
			if (check != null) {
				ArrayList<EmployeeDetailsModel> studentModel = new ArrayList<EmployeeDetailsModel>();
				EmployeeService studentService = new EmployeeService();
				try {
					studentModel = studentService.getAllList();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("details", studentModel);
				// navigation=viewemp;
				requestDispatcher = request.getRequestDispatcher(VIEW_EMPLOYEE_PAGE);
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
		} else {
			if (check != null) {
				EmployeeDetailsModel studentModel = new EmployeeDetailsModel();
				EmployeeService studentService = new EmployeeService();
				ArrayList<EmployeeDetailsModel> studentList = new ArrayList<EmployeeDetailsModel>();
				
				int id = Integer.parseInt(request.getParameter("studentId"));
				String rollNo = request.getParameter("rollNo");
				String serverName = request.getParameter("name");
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dobReq = null;
				try {
					String reqDate = request.getParameter("dob");
					dobReq = dateFormat.parse(reqDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date dobSql = new Date(dobReq.getTime());
				
				String gender = request.getParameter("gender");
				String fatherName = request.getParameter("fatherName");


				int semester = Integer.parseInt(request.getParameter("semester"));
				String department = request.getParameter("department");
				
				studentModel.setStudentId(id);
				studentModel.setName(serverName);
				studentModel.setGender(gender);
				studentModel.setFatherName(fatherName);
				studentModel.setDob(dobSql);
				studentModel.setSemester(semester);
				studentModel.setDepartment(department);
				studentModel.setRollNo(rollNo);
				try {
					int status = studentService.updateServer(studentModel);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				try {
					studentList = studentService.getStudentList2();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("details", studentList);
				request.setAttribute("msg", "record updated successfully");
				requestDispatcher = request.getRequestDispatcher(VIEW_EMPLOYEE_PAGE);
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
		}
		requestDispatcher.forward(request, response);
	}
}
