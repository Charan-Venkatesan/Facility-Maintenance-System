package uta_facility_maintenance_system.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import uta_facility_maintenance_system.data.FacilityDAO;
import uta_facility_maintenance_system.model.FacilityErrorMsgs;
import uta_facility_maintenance_system.model.Facility;

@WebServlet("/FacilityController")
public class FacilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void getFacilityDetail(HttpServletRequest request, Facility newFacility) {
		newFacility.setFacilityDetails(request.getParameter("facility_type"), request.getParameter("facility_name"),
				request.getParameter("interval"), request.getParameter("duration"), request.getParameter("venue"));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		session.removeAttribute("faccilitylist");

		// Logout
		if (action.equals("logout")) {
			session = request.getSession(false);
			if (session.getAttribute("uname") != null) {
				session.removeAttribute("uname");
				session.removeAttribute("formtext");
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		}

		if (action.equalsIgnoreCase("listFacility")) {
			ArrayList<Facility> facilityInDB = new ArrayList<Facility>();
			facilityInDB = FacilityDAO.listFacilities();
			session.setAttribute("faccilitylist", facilityInDB);
			getServletContext().getRequestDispatcher("/listFacilities.jsp").forward(request, response);
		} else // redirect all other gets to post
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Facility facility = new Facility();
		session.removeAttribute("SearchFacilityReport");
		session.removeAttribute("specificfacility");
		Facility newFac = new Facility();
		FacilityErrorMsgs newFacilityErrorMsgs = new FacilityErrorMsgs();
		session.removeAttribute("errorMsgs");
		session.removeAttribute("formtext1");
		session.removeAttribute("addNewFacility");
		String message;
		String url = "";

		if (action.equalsIgnoreCase("reqReservation")) {
			String facilityName = request.getParameter("facilityName");
			String bookedDuration = request.getParameter("bookDuration");
			facility.setFacilityName(facilityName);
			facility.setBookedDuration(bookedDuration);
			facility.setBookedBy((String) session.getAttribute("uname"));
			facility.setBookingStatus("Booked");
			int result;

			try {
				result = FacilityDAO.updateFacilityStatus(facility);
				if (result > 0) {
					message = "Facility Reserved Successfully";
				} else {
					message = "Failed to Reserve Facility";
				}
				session.setAttribute("msg", message);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "/ResultFacility.jsp";
		}
		if (action.equalsIgnoreCase("saveFacility")) {
			getFacilityDetail(request, newFac);
			newFac.validateInputFacilityDetails(action, newFac, newFacilityErrorMsgs);
			if (!newFacilityErrorMsgs.getErrorMsg().equals("")) {
				getFacilityDetail(request, newFac);

				session.setAttribute("errorMsgs", newFacilityErrorMsgs);
			} else {
				session.setAttribute("addNewFacility", newFac);
				System.out.println("New Facility added");
				boolean result = FacilityDAO.insertNewFacility(newFac);
				if (result) {
					newFac.setGreetingText("Facility has been successfully added ");
					session.setAttribute("formtext1", newFac);
				}
			}
			url = "/AddNewFacility.jsp";

		}

		if (action.equalsIgnoreCase("searchFacility")) {
			session.removeAttribute("SearchFacilityReport");
			String facilitytype = request.getParameter("facility_type");
			String date = request.getParameter("date");
			String time = request.getParameter("time_slot");
		//	facility.setFacility(facilitytype, "", time, date, "", "");

			ArrayList<Facility> facilityInDB = new ArrayList<Facility>();
			if (!facilitytype.equals(""))
				facilityInDB = FacilityDAO.searchFacilityByType(facilitytype);
			session.setAttribute("SearchFacilityReport", facilityInDB);
			 session.removeAttribute("facility");
			url = "/facilitySearchResults.jsp";
		}

		if (action.equalsIgnoreCase("listSpecificFacility")) { // action=listSpecificFacility
			ArrayList<Facility> facilityInDB = new ArrayList<Facility>();
			Facility selectedFacility = new Facility();
			facilityInDB = FacilityDAO.searchFacility(request.getParameter("id"));
//			selectedFacility.setFacility(facilityInDB.get(0).getFacilityType(), facilityInDB.get(0).getFacilityName(),
//					facilityInDB.get(0).getInterval(), facilityInDB.get(0).getDuration(),
//					facilityInDB.get(0).getVenue(), facilityInDB.get(0).getBookingStatus());
			session.setAttribute("specificfacility", selectedFacility);
			url = "/listSpecificFacility.jsp";
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);

	}
}
