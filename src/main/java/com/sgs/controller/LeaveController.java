package com.sgs.controller;

import java.io.IOException;
import java.sql.Date;
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

import com.sgs.model.EmployeeDetailsModel;
import com.sgs.model.LeaveDetailsModel;
import com.sgs.model.LeaveHistoryModel;
import com.sgs.service.EmployeeService;
import com.sgs.service.LeaveService;

@WebServlet("/LeaveController")
public class LeaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String APPLY_LEAVE_PAGE = "ApplyLeave.jsp";
	private static final String LEAVE_HISTORY_PAGE = "LeaveHistory.jsp";
	private static final String VIEW_EMPLOYEE_PAGE = "ViewEmployee.jsp";

	RequestDispatcher requestDispatcher = null;
	private EmployeeService empService;
	private LeaveService leaveService;

	public LeaveController() {
		super();
		this.empService = new EmployeeService();
		this.leaveService = new LeaveService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String check = (String) session.getAttribute("username");
		String action = request.getParameter("action");
		String navigation = LEAVE_HISTORY_PAGE;

		if (action.equals("applyLeave")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			try {
				EmployeeDetailsModel employee = empService.getById(employeeId);
				request.setAttribute("employee", employee);
				navigation = APPLY_LEAVE_PAGE;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("leaveHistory")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			List<LeaveHistoryModel> dataList = leaveService.findLeaveHistoryById(employeeId);
			navigation = LEAVE_HISTORY_PAGE;
			request.setAttribute("leaveList", dataList);

		} else if (action.equals("cancel")) {
			ArrayList<EmployeeDetailsModel> dataList = new ArrayList<EmployeeDetailsModel>();
			EmployeeService studentService = new EmployeeService();
			try {
				dataList = studentService.getAllList();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			request.setAttribute("details", dataList);
			navigation = VIEW_EMPLOYEE_PAGE;
		} else if (action.equals("leaveAction")) {
			int leaveId = Integer.parseInt(request.getParameter("leaveId"));
			String leaveType = request.getParameter("type");
			
			int status = leaveService.updateLeaveType(leaveId, leaveType);
			
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			List<LeaveHistoryModel> dataList = leaveService.findLeaveHistoryById(employeeId);
			navigation = LEAVE_HISTORY_PAGE;
			
			request.setAttribute("msg", "Leave action done");
			request.setAttribute("leaveList", dataList);
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
		String navigation = LEAVE_HISTORY_PAGE;

		if (action.equals("cancel")) {
			ArrayList<EmployeeDetailsModel> model = new ArrayList<EmployeeDetailsModel>();
			EmployeeService service = new EmployeeService();
			try {
				model = service.getAllList();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			request.setAttribute("details", model);
			navigation = VIEW_EMPLOYEE_PAGE;
		} else if (action.equals("Submit")) {
			int employeeId = Integer.parseInt(request.getParameter("employeeId"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dobLeaveFromReq = null;
			java.util.Date dobLeaveToReq = null;
			try {
				String reqLaveFromDate = request.getParameter("leaveFrom");
				String reqLaveToDate = request.getParameter("leaveTo");
				
				dobLeaveFromReq = dateFormat.parse(reqLaveFromDate);
				dobLeaveToReq = dateFormat.parse(reqLaveToDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date leaveFromDate = new Date(dobLeaveFromReq.getTime());
			Date leaveToDate = new Date(dobLeaveToReq.getTime());
			
			String leaveType = request.getParameter("leaveType");
			String leaveStatus = request.getParameter("leaveStatus");
			String reason = request.getParameter("reason");
			
			LeaveDetailsModel leaveDetails = new LeaveDetailsModel();
			leaveDetails.setLeaveFrom(leaveFromDate);
			leaveDetails.setLeaveTo(leaveToDate);
			leaveDetails.setLeaveType(leaveType);
			leaveDetails.setLeaveStatus(leaveStatus);
			leaveDetails.setReason(reason);
			leaveDetails.setEmployeeId(employeeId);

			int result = leaveService.saveLeaveDetails(leaveDetails);
			if (result == 1) {
				navigation = VIEW_EMPLOYEE_PAGE;
			}
			ArrayList<EmployeeDetailsModel> dataList = null;
			try {
				dataList = empService.getAllList();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("details", dataList);
			request.setAttribute("msg", "record saved successfully");
		}

		requestDispatcher = request.getRequestDispatcher(navigation);
		requestDispatcher.forward(request, response);
	}
}
