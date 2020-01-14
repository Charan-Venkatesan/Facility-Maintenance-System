package uta_facility_maintenance_system.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import uta_facility_maintenance_system.model.MarReport;
import uta_facility_maintenance_system.util.SQLConnection;

public class MarReportDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	private static ArrayList<MarReport> returnMatchingMarReportList(String queryString) {
		ArrayList<MarReport> marReportListInDB = new ArrayList<MarReport>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		try {
			stmt = conn.createStatement();
			ResultSet marReportList = stmt.executeQuery(queryString);

			while (marReportList.next()) {
				MarReport marReport = new MarReport();
				marReport.setMarNumber(marReportList.getString("mar_number"));
				marReport.setFacilityName(marReportList.getString("facility_name"));
				marReport.setFacilityType(marReportList.getString("facility_type"));
				marReport.setUrgency(marReportList.getString("urgency"));
				marReport.setDescription(marReportList.getString("description"));
				marReport.setReportedBy(marReportList.getString("reported_by"));
				marReport.setCreatedDate(marReportList.getString("created_date"));
				marReport.setAssignTo(marReportList.getString("assigned_to"));
				marReport.setAssignDate(marReportList.getString("assigned_date"));
				marReport.setEstimateOfRepair(marReportList.getString("estimate_of_repair"));
				marReportListInDB.add(marReport);
			}
		} catch (SQLException e) {
		}
		return marReportListInDB;
	}
	
	private static ArrayList<MarReport> retrieveMatchingList(String queryString) {
		ArrayList<MarReport> marInDb = new ArrayList<MarReport>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(queryString);
			while(rs.next()) {
				MarReport cMAR = new MarReport();
				cMAR.setResultSetMAR(rs.getString("mar_number"));
				marInDb.add(cMAR);
			}
			conn.commit(); 
		} catch (SQLException e) {}
		return marInDb;
	}
	
	private static int StoreMARinDB(MarReport createMAR,String queryString) {
		Statement stmt = null;
		int i = 0; String insertMAR="";		
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			System.out.println("inside");
			 insertMAR = queryString + " VALUES ('"  
					+ createMAR.getFacilityType()  + "','"
					+ createMAR.getFacilityName() + "','"		
					+ createMAR.getUrgency() + "','"
					+ createMAR.getDescription() + "','"
					+ createMAR.getUserName() + "'," 
					+ "NOW()" + ",'"
					+ createMAR.getMarNumber() + "')";
			System.err.println("printing query string" + insertMAR);
		    i = stmt.executeUpdate(insertMAR);			    
			conn.commit(); 
		} catch (SQLException e) {}
		
		return i;
	}
	
	public static boolean insertMAR(MarReport createMAR) {  
		 int i = StoreMARinDB(createMAR,"INSERT INTO MAR_Report (facility_type,facility_name,urgency,description,reported_by,created_date, mar_number)");
		 if( i >0 )
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	}
	
	private static ArrayList<MarReport> returnMatchingMARList(String queryString) {
		ArrayList<MarReport> listInDB = new ArrayList<MarReport>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				MarReport mcfm = new MarReport();
				mcfm.setMarNumber(userList.getString("mar_number"));				
				mcfm.setEstimateOfRepair(userList.getString("estimate_of_repair"));
				mcfm.setAssignTo(userList.getString("assigned_to"));
				mcfm.setUrgency(userList.getString("urgency"));
				mcfm.setFacilityType(userList.getString("facility_type"));
				mcfm.setFacilityName(userList.getString("facility_name"));
				mcfm.setDescription(userList.getString("description"));
				mcfm.setReportedBy(userList.getString("reported_by"));
				mcfm.setCreatedDate(userList.getString("created_date"));
				listInDB.add(mcfm);
			}
		} catch (SQLException e) {
		}
		System.err.println("printing query string" + queryString);
		return listInDB;
	}
	
	private static ArrayList<MarReport> returnMatchingUserNameList(String queryString) {
		ArrayList<MarReport> userNameList = new ArrayList<MarReport>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet usersList = stmt.executeQuery(queryString);
			while (usersList.next()) {
				MarReport macForm = new MarReport();
				macForm.setUserName(usersList.getString("username"));
				userNameList.add(macForm);
			}
		} catch (SQLException e) {
		}
		return userNameList;	
	}

	private static void UpdateListinDB(String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(queryString);
			conn.commit();

		} catch (SQLException e) {
		}
	}
	private static ArrayList<MarReport> returnMatchingMarList(String queryString) {
		ArrayList<MarReport> marNumberList = new ArrayList<MarReport>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(queryString);
			while (marList.next()) {
				MarReport marForm = new MarReport();
				marForm.setMarNumber(marList.getString("mar_number"));
				marNumberList.add(marForm);
			}
		} catch (SQLException e) {
		}
		return marNumberList;	
	}
	
	public static int updateIntoDB(String queryString) throws SQLException {
		Statement stmt = null;
		int result = 0;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(queryString);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int searchcount(String assignedto) {
		int count = 0;
		String query = "SELECT COUNT(*) FROM (SELECT * from mar_report where assigned_to = '"+assignedto+"' AND assigned_date = curdate()) AS t";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(query);
			userList.next();
			count = userList.getInt(1);
			
			userList.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;

	}
	
	public static int searchcountMonth(String assignedto) {
		int count = 0;
		String query = "SELECT COUNT(*) FROM (SELECT * from mar_report where assigned_to = '"+assignedto+"' AND assigned_date between SUBDATE(CURDATE(), WEEKDAY(CURDATE())) AND Date(SUBDATE(CURDATE(), WEEKDAY(CURDATE())) + 6)) AS t";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(query);
			userList.next();
			count = userList.getInt(1);
			
			userList.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;

	}
	
	public static ArrayList<MarReport> getUserNameList() {
		return returnMatchingUserNameList(" SELECT username from user_details WHERE user_role= 'Repairer'");
	}

	public static ArrayList<MarReport> getMarList() {
		return returnMatchingMarList(" SELECT mar_number from mar_report ORDER BY mar_number");
	}
	
	public static ArrayList<MarReport> listUnassignedMAR() {
		// String assigned_date = null;
		return returnMatchingMARList("SELECT * from mar_report WHERE assigned_to is null ORDER BY mar_number");
	}

	public static ArrayList<MarReport> listAllMAR() {
		// String assigned_date = null;
		return returnMatchingMARList("SELECT * from mar_report ORDER BY mar_number");
	}

	public static ArrayList<MarReport> searchSpecificMar(String mar_number) {
		return returnMatchingMARList(
				" SELECT * from mar_report WHERE mar_number = '" + mar_number + "' ORDER BY mar_number");
	}

	public static boolean updateMar(String assignedto, String mar_number, String estimate_of_repair) {
		UpdateListinDB(" UPDATE mar_report SET assigned_to = '" + assignedto + "', estimate_of_repair= '"
				+ estimate_of_repair + "', assigned_date = SYSDATE() WHERE mar_number = '" + mar_number + "' ");
		return true;

	}
	
	public ArrayList<MarReport> generateMARNumber() {
		return retrieveMatchingList("SELECT * FROM mar_report order by created_date DESC LIMIT 1");
	}

	public static ArrayList<MarReport> myRepairlist(String repairer) {
		return returnMatchingMarReportList(
				" SELECT * FROM mar_report WHERE assigned_to = '" + repairer + "' ORDER BY assigned_date");
	}

	public static ArrayList<MarReport> viewMySpecificRepair(String repairer, String marNumber) {
		return returnMatchingMarReportList(
				"SELECT * FROM mar_report WHERE assigned_to = '" + repairer + "' AND mar_number = '" + marNumber + "'");
	}
	
	public static ArrayList<MarReport> assignedMARByDate(String date) {
		return returnMatchingMarReportList(
				" SELECT * FROM mar_report WHERE assigned_date = '" + date + "'");
	}
	
	public static ArrayList<MarReport> viewRepairerSchedule(String uname, String date) {
		return returnMatchingMarReportList(
				" SELECT * FROM mar_report WHERE assigned_date = '" + date + "' and assigned_to = '" + uname + "'");
	}
	
	public static void cancelMyRepair(String marNumber) throws SQLException {
		updateIntoDB("update mar_report set assigned_to = null,assigned_date=null,assigned_time=null where mar_number = '" + marNumber + "'");
	}
	
	public int updateRepairTime(MarReport marReport) throws SQLException {
		int result;
		result = updateIntoDB( "UPDATE mar_report SET assigned_time='"+marReport.getAssignTime()+"' WHERE mar_number='"+marReport.getMarNumber()+"'");
		return result;
	}

	public static ArrayList<MarReport> myMarList(String username) {
		return returnMatchingMarReportList(
				" SELECT * FROM mar_report WHERE reported_by = '" + username + "'");
	}
	public static ArrayList<MarReport> getMarById(String id) {
		return returnMatchingMarReportList(
				" SELECT * FROM mar_report WHERE mar_number = '" + id + "'");
	}
	
}
