package uta_facility_maintenance_system.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_facility_maintenance_system.data.FacilityDAO;
import uta_facility_maintenance_system.data.MarReportDAO;
import uta_facility_maintenance_system.model.CreateMARErrorMsgs;
import uta_facility_maintenance_system.model.Facility;
import uta_facility_maintenance_system.model.MarReport;

@WebServlet("/MarReportController")
public class MarReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MarReportController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private void getMARParam(HttpServletRequest request, MarReport createMar) {
		createMar.setMarDetails(request.getParameter("facilityType"),request.getParameter("facilityName"),request.getParameter("Urgency"),request.getParameter("description"));
	}   

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("listEachMar")) {
			ArrayList<MarReport> marNumberList = MarReportDAO.getMarList();
			session.setAttribute("MAC", marNumberList);
			getServletContext().getRequestDispatcher("/viewSpecificMar.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("ReportIssue")) {
			ArrayList<Facility> facilityNameList = FacilityDAO.getFacilityNameList();
			session.setAttribute("facilityNameList", facilityNameList);
			ArrayList<Facility> facilityTypeList = FacilityDAO.getFacilityTypeList();
			session.setAttribute("facilityTypeList", facilityTypeList);		
			getServletContext().getRequestDispatcher("/formCreateMAR.jsp").forward(request, response);
		}
		else if (action.equalsIgnoreCase("listMar")) {
			ArrayList<MarReport> searchInDB = new ArrayList<MarReport>();
			searchInDB = MarReportDAO.listUnassignedMAR();
			session.setAttribute("MAC", searchInDB);
			getServletContext().getRequestDispatcher("/searchMar.jsp").forward(request, response);
		} else if (action.equalsIgnoreCase("listallMar")) {
			ArrayList<MarReport> searchInDB = new ArrayList<MarReport>();
			searchInDB = MarReportDAO.listAllMAR();
			session.setAttribute("MAC", searchInDB);
			getServletContext().getRequestDispatcher("/searchMar.jsp").forward(request, response);
		} else if (action.equalsIgnoreCase("viewScheduleRepairer")) {
			System.out.println("Enter here");
			ArrayList<MarReport> userList = MarReportDAO.getUserNameList();
			session.setAttribute("userNameList", userList);
			getServletContext().getRequestDispatcher("/SearchRepairerSchedule.jsp").forward(request, response);
		} else if (action.equalsIgnoreCase("viewRepairerSchedule")) {
			ArrayList<MarReport> repairerScheduleList = new ArrayList<MarReport>();
			System.out.println("Working till here");
			String uname = request.getParameter("user_name");
			String date = request.getParameter("Search_Date");
			repairerScheduleList = MarReportDAO.viewRepairerSchedule(uname, date);
			session.setAttribute("repairerScheduleList", repairerScheduleList);
			getServletContext().getRequestDispatcher("/repairerScheduleList.jsp").forward(request, response);
		} else
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		String url = "";
		MarReport macForm = new MarReport();
		String assignedto = null;
		session.removeAttribute("MAC");
		ArrayList<MarReport> userInDB = new ArrayList<MarReport>();
		MarReport createMar = new MarReport();
		CreateMARErrorMsgs CerrorMsgs = new CreateMARErrorMsgs();		
		session.removeAttribute("errorMsgs");
		session.removeAttribute("formtext");
		session.removeAttribute("createMAR");
		String userName = (String) session.getAttribute("uname");
		createMar.setUserName(userName);
		MarReport marReport = new MarReport();
		CreateMARErrorMsgs marReportErrorMsgs = new CreateMARErrorMsgs();
		ArrayList<MarReport> userList = MarReportDAO.getUserNameList();
		session.setAttribute("userNameList", userList);

		// My Repairs List
		if (action.equalsIgnoreCase("listViewMyRepairs")) {
			ArrayList<MarReport> myRepairListInDB = new ArrayList<MarReport>();
			myRepairListInDB = MarReportDAO.myRepairlist((String) session.getAttribute("uname"));
			session.setAttribute("MyRepairList", myRepairListInDB);
			url = "/viewMyRepairList.jsp";
		}

		// View Specific Repair
		if (action.equalsIgnoreCase("ViewSpecificRepair")) {

			ArrayList<MarReport> marsInDB = new ArrayList<MarReport>();
			MarReport selectedRepair = new MarReport();
			int selectedRepairIndex = 0;
			marsInDB = MarReportDAO.viewMySpecificRepair((String) session.getAttribute("uname"), id);
			selectedRepair.setMarReport(marsInDB.get(selectedRepairIndex).getMarNumber(),
					marsInDB.get(selectedRepairIndex).getFacilityName(),
					marsInDB.get(selectedRepairIndex).getFacilityType(), marsInDB.get(selectedRepairIndex).getUrgency(),
					marsInDB.get(selectedRepairIndex).getDescription(),
					marsInDB.get(selectedRepairIndex).getReportedBy(),
					marsInDB.get(selectedRepairIndex).getCreatedDate(), marsInDB.get(selectedRepairIndex).getAssignTo(),
					marsInDB.get(selectedRepairIndex).getAssignDate(),
					marsInDB.get(selectedRepairIndex).getAssignTime(),
					marsInDB.get(selectedRepairIndex).getEstimateOfRepair());
			session.setAttribute("MARS", selectedRepair);
			url = "/ViewSpecificRepair.jsp";
		}
		
		//Create MAR
		if (action.equalsIgnoreCase("saveMAR")) {  
			getMARParam(request, createMar);
			createMar.validateMARReport(action, createMar, CerrorMsgs);
			if (!CerrorMsgs.getErrorMsg().equals("")) {// if error messages
				getMARParam(request, createMar);
				session.setAttribute("errorMsgs", CerrorMsgs);
				url = "/formCreateMAR.jsp";
			}
			else {// if no error messages
				session.setAttribute("createMAR",createMar);
				boolean result = MarReportDAO.insertMAR(createMar);
				System.out.println(result);
				if (result) {
					createMar.setGreetingText("Report has been successfully received. Your Maintenance number is " + createMar.getMarNumber());
					session.setAttribute("formtext", createMar);
					url= "/formCreateMAR.jsp";
				}
				else {
					createMar.setGreetingText("Issue adding the Report " + createMar.getMarNumber());
					session.setAttribute("formtext", createMar);
					url= "/formCreateMAR.jsp";
				}
				
			}
		}


		// Search Unassigned MARs
		if (action.equalsIgnoreCase("searchAssignedMARs")) {
//			MarReport marReport = new MarReport();
//			marReport.setSearchDate(request.getParameter("Search_Date"));			
			ArrayList<MarReport> assignedMarsInDb = new ArrayList<MarReport>();
			String searchDate = request.getParameter("Search_Date");
			assignedMarsInDb = MarReportDAO.assignedMARByDate(searchDate);
			session.setAttribute("MyAssignedMARbyList", assignedMarsInDb);
			url = "/assignedMARsByDateList.jsp";
		}

		// Modify Repair time
		if (action.equalsIgnoreCase("modifytime")) {
			int result = 0;
//			String marNumber = id;
			String assignTime = request.getParameter("repairTime");
			marReport.setAssignTime(assignTime);
			marReport.setMarNumber(id);
			MarReportDAO marReportDAO = new MarReportDAO();
			marReport.validateTime(marReport, marReportErrorMsgs);
			session.setAttribute("MARReport", marReport);
			if(!marReportErrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("errorMsgs", marReportErrorMsgs);
				url = "modifyRepairTime.jsp";
			}
			else {
				try {
					result = marReportDAO.updateRepairTime(marReport);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (result != 0) {
					session.setAttribute("formtext", "Repair Time Updated Successfully");
//					url = "/Success.jsp";
				} else {
					session.setAttribute("formtext", "Unable to update Repair Time");
//					url = "/Failure.jsp";
				}
				url = "modifyRepairTime.jsp";
			}
		}

		// Cancel My Repair
		if (action.equalsIgnoreCase("cancelMyRepair")) {
			try {
				MarReportDAO.cancelMyRepair(id);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.setAttribute("msg", "Repair Cancelled");
			url = "/cancelRepair.jsp";
		}
		
		// List my Mars (USER)
		if (action.equalsIgnoreCase("listMyMars")) {
			ArrayList<MarReport> myRepairListInDB = new ArrayList<MarReport>();
			myRepairListInDB = MarReportDAO.myMarList((String) session.getAttribute("uname"));
			System.out.println("---------------"+myRepairListInDB.size());
			session.setAttribute("MyRepairList", myRepairListInDB);
			url = "/ListMyMars.jsp";
		}
		
		// viewMyMar
		if (action.equalsIgnoreCase("viewMyMar")) {
			ArrayList<MarReport> marInDB = new ArrayList<MarReport>();
			marReport = new MarReport();
			marInDB = MarReportDAO.getMarById(request.getParameter("id"));
			marReport.setMarReport(marInDB.get(0).getMarNumber(),marInDB.get(0).getFacilityName() ,marInDB.get(0).getFacilityType() ,
					marInDB.get(0).getUrgency(), marInDB.get(0).getDescription(), marInDB.get(0).getReportedBy(), marInDB.get(0).getCreatedDate(), marInDB.get(0).getAssignTo(),
					marInDB.get(0).getAssignDate(), marInDB.get(0).getAssignTime(), marInDB.get(0).getEstimateOfRepair());
			session.setAttribute("MAC", marReport);
			url = "/ViewMyMar.jsp";
			
		}
		
		
		if(action.equalsIgnoreCase("viewSpecificMar"))
		{
			
			ArrayList<MarReport> searchInDB = new ArrayList<MarReport>();
			String mar_number = request.getParameter("mar_number");
			searchInDB = MarReportDAO.searchSpecificMar(mar_number);
			session.setAttribute("MAC", searchInDB);
			getServletContext().getRequestDispatcher("/searchMar.jsp").forward(request, response);
			
			
		}
		else if (action.equalsIgnoreCase("updateMar")) {
			assignedto = request.getParameter("assignedto");
			String mac_number = request.getParameter("mar_number");
			String estimate_of_repair = request.getParameter("estimate_of_repair");
			macForm.setAssignTo(assignedto);
			macForm.setMarNumber(mac_number);
			macForm.setEstimateOfRepair("estimate_of_repair");
			boolean validateResult=macForm.validateRules(assignedto);
			boolean validateCount = macForm.validateCount(assignedto);
		
			
			
			//System.out.println(count);

			if (validateResult) {
				boolean result = MarReportDAO.updateMar(assignedto, mac_number, estimate_of_repair);
				if (result) {
					macForm.setGreetingText(
							"Mar Number " + mac_number +  " is succesfully assigned to " + macForm.getAssignTo());
					session.setAttribute("MAC", macForm);
					url = "/assignMarToRepairer.jsp";
				} 
				else {
					macForm.setGreetingText(
							"Mar Number " + mac_number + " is not succesfully assigned to " + macForm.getAssignTo());
					session.setAttribute("MAC", macForm);
					url = "/assignMarToRepairer.jsp";
				}
			}
			else {
				if(validateCount)
				{
				macForm.setGreetingText("The repairer " + assignedto + " has already exceeded his number of repairs for the day");
				}
				else
				{
				macForm.setGreetingText("The repairer " + assignedto + " has already exceeded his number of repairs for the week");	
				}
				session.setAttribute("MAC", macForm);
				url = "/assignMarToRepairer.jsp";
			}
			macForm.setMAC(macForm.getAssignTo(), macForm.getMarNumber(),
			macForm.getEstimateOfRepair());
			session.setAttribute("MAC", macForm);
			url = "/assignMarToRepairer.jsp";
		} 
		if(action.equalsIgnoreCase("assignMarToRepairer")) {
			userInDB = MarReportDAO.searchSpecificMar(request.getParameter("id"));
	
			macForm.setMAC(userInDB.get(0).getAssignTime(), userInDB.get(0).getMarNumber(),
					userInDB.get(0).getEstimateOfRepair());

			session.setAttribute("MAC", macForm);
			url = "/assignMarToRepairer.jsp";

		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);

	}
}
