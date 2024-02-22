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

@WebServlet("/EmployeeController")
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
				EmployeeDetailsModel model = new EmployeeDetailsModel();
				int employeeId = Integer.parseInt(request.getParameter("employeeId"));
				try {
					model = serverService.getById(employeeId);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("name", "update");
				request.setAttribute("details", model);
				navigation = REGISTER_EMPLOYEE_PAGE;
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
		} else if (action.equals("delete")) {
			String confirm = request.getParameter("confirm");
			if (check != null) {
				if (!"false".equals(confirm)) {
					EmployeeService service = new EmployeeService();
					int employeeId = Integer.parseInt(request.getParameter("employeeId"));
					try {
						int reult = service.deleteById(employeeId);
						if (reult == 1) {
							ArrayList<EmployeeDetailsModel> dataList = new ArrayList<EmployeeDetailsModel>();
							EmployeeService service2 = new EmployeeService();
							try {
								dataList = service2.getAllList();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
							request.setAttribute("details", dataList);
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
				ArrayList<EmployeeDetailsModel> dataList = new ArrayList<EmployeeDetailsModel>();
				EmployeeService service = new EmployeeService();
				try {
					dataList = service.getAllList();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("details", dataList);
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
				EmployeeDetailsModel model = new EmployeeDetailsModel();
				EmployeeService service = new EmployeeService();
				String employeeCode = request.getParameter("employeeCode");
				String employeeName = request.getParameter("name");

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dobReq = null;
				try {
					String reqDate = request.getParameter("dob");
					dobReq = dateFormat.parse(reqDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date dobDate = new Date(dobReq.getTime());

				String designation = request.getParameter("designation");
				String gender = request.getParameter("gender");

				int experiance = Integer.parseInt(request.getParameter("experiance"));
				String contactNumber = request.getParameter("contactNumber");

				model.setEmployeeCode(employeeCode);
				model.setDob(dobDate);
				model.setName(employeeName);
				model.setGender(gender);
				model.setDesignation(designation);
				model.setExperiance(experiance);
				model.setContactNumber(contactNumber);

				String result = service.check(employeeCode);
				arrayList.add(model);
				if (result.equals("success")) {
					request.setAttribute("name", "save");
					request.setAttribute("msg", "Employee Code Already Exist");
					request.setAttribute("details", model);
					requestDispatcher = request.getRequestDispatcher(REGISTER_EMPLOYEE_PAGE);
				} else {
					int status = service.insertDetails(model);
					if (status > 0) {
						ArrayList<EmployeeDetailsModel> dataList = new ArrayList<EmployeeDetailsModel>();
						EmployeeService service2 = new EmployeeService();
						try {
							dataList = service2.getAllList();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						request.setAttribute("details", dataList);
						requestDispatcher = request.getRequestDispatcher(VIEW_EMPLOYEE_PAGE);
					}
				}
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
		} else if (action.equals("Cancel")) {
			if (check != null) {
				ArrayList<EmployeeDetailsModel> model = new ArrayList<EmployeeDetailsModel>();
				EmployeeService service = new EmployeeService();
				try {
					model = service.getAllList();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("details", model);
				// navigation=viewemp;
				requestDispatcher = request.getRequestDispatcher(VIEW_EMPLOYEE_PAGE);
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
		} else {
			if (check != null) {
				EmployeeDetailsModel model = new EmployeeDetailsModel();
				EmployeeService service = new EmployeeService();
				ArrayList<EmployeeDetailsModel> dataList = new ArrayList<EmployeeDetailsModel>();

				int id = Integer.parseInt(request.getParameter("employeeId"));
				String employeeCode = request.getParameter("employeeCode");
				String employeeName = request.getParameter("name");

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dobReq = null;
				try {
					String reqDate = request.getParameter("dob");
					dobReq = dateFormat.parse(reqDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date dobDate = new Date(dobReq.getTime());

				String designation = request.getParameter("designation");
				String gender = request.getParameter("gender");

				int experiance = Integer.parseInt(request.getParameter("experiance"));
				String contactNumber = request.getParameter("contactNumber");

				model.setEmployeeCode(employeeCode);
				model.setDob(dobDate);
				model.setName(employeeName);
				model.setGender(gender);
				model.setDesignation(designation);
				model.setExperiance(experiance);
				model.setContactNumber(contactNumber);
				model.setEmployeeId(id);
				try {
					int status = service.update(model);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				try {
					dataList = service.getAllList();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				request.setAttribute("details", dataList);
				request.setAttribute("msg", "record updated successfully");
				requestDispatcher = request.getRequestDispatcher(VIEW_EMPLOYEE_PAGE);
			} else {
				requestDispatcher = request.getRequestDispatcher(LOGIN);
			}
		}
		requestDispatcher.forward(request, response);
	}
}
