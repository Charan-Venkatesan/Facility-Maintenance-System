package uta_facility_maintenance_system.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import uta_facility_maintenance_system.util.SQLConnection;
import uta_facility_maintenance_system.model.Facility;


public class FacilityDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static int StoreFacilityinDB(Facility newFac, String queryString) {
		Statement stmt = null;
		int i = 0;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String insertValues = queryString +" VALUES ('"
								  + newFac.getFacilityType() + "','" 
								  + newFac.getFacilityName() + "','"
								  + newFac.getInterval() + "','"
								  + newFac.getDuration() + "','"
								  + newFac.getVenue() + "', 'Available')";
			System.out.println(insertValues);
			i = stmt.executeUpdate(insertValues);
			System.err.println("printing query string" + insertValues);
			conn.commit(); 
		} catch (SQLException e) {}
		return i;
	}
	
	private static ArrayList<Facility> ReturnMatchingFacilitiesList (String queryString) {
		ArrayList<Facility> facilityListInDB = new ArrayList<Facility>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			System.err.println("printing query string" + queryString);
			while (facilityList.next()) {
				Facility facility = new Facility(); 
				facility.setFacilityType(facilityList.getString("facility_type"));
				facility.setFacilityName(facilityList.getString("facility_name"));
				facility.setInterval(facilityList.getString("interval"));
				facility.setDuration(facilityList.getString("duration"));  
				facility.setVenue(facilityList.getString("venue"));
				facility.setBookingStatus(facilityList.getString("booking_status"));
				facilityListInDB.add(facility);	
			}
		} catch (SQLException e) {}
		return facilityListInDB;
	}
	
	private static ArrayList<Facility> returnFacilityTypeList(String queryString) {
		ArrayList<Facility> facilityDetailsListInDB = new ArrayList<Facility>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			while (facilityList.next()) {
				Facility fac = new Facility();
				fac.setFacilityType(facilityList.getString("facility_type"));
				facilityDetailsListInDB.add(fac);
			}
		} catch (SQLException e) {
		}
		return facilityDetailsListInDB;
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
	public static int updateFacilityStatus(Facility facility) throws SQLException {
		return updateIntoDB("UPDATE facilities SET booking_status = '" + facility.getBookingStatus() + "', booked_by = '"
				+ facility.getBookedBy() + "', booked_duration = '" + facility.getBookedDuration()
				+ "' where facility_name='"+facility.getFacilityName()+"'");
	}
	private static ArrayList<Facility> returnFacilityNameList(String queryString) {
		ArrayList<Facility> facilityNameListInDB = new ArrayList<Facility>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet facilityNameList = stmt.executeQuery(queryString);
			System.out.println(facilityNameList.getFetchSize());
			while (facilityNameList.next()) {
				Facility fac = new Facility();
				fac.setFacilityName(facilityNameList.getString("facility_name"));
				System.out.println(facilityNameList.getString("facility_name"));
				facilityNameListInDB.add(fac);
			}
		} catch (SQLException e) {
		}
		return facilityNameListInDB;
	}
	
	public static boolean insertNewFacility(Facility newFac) {
		int i = StoreFacilityinDB(newFac, "INSERT INTO facilities(facility_type,facility_name,`interval`,duration,venue,booking_status)");
		 if( i >0 )
 		 {
 			 return true;
 		 }
 		 else
 		 {
 			 return false;
 		 }
	}	
		
	
	
	public static ArrayList<Facility>  listFacilities() {  
		return ReturnMatchingFacilitiesList(" SELECT * from FACILITIES WHERE booking_status = 'Available' ORDER BY facility_name");
}
	
	//search facility by facility type
	public static ArrayList<Facility>   searchFacilityByType(String facilitytype)  {  
		return ReturnMatchingFacilitiesList(" SELECT * from FACILITIES WHERE facility_type = '"+facilitytype+"' and booking_status = 'Available' ORDER BY facility_name");
}
	//search facility by day
	public static ArrayList<Facility>   searchFacilityByDate (String date)  {  
		return ReturnMatchingFacilitiesList(" SELECT * from FACILITIES WHERE duration = '"+date+"' and booking_status = 'Available' ORDER BY facility_name");
	}
	
	//search facility by time
	public static ArrayList<Facility>   searchFacilityByTime (String time)  {  
		return ReturnMatchingFacilitiesList(" SELECT * from FACILITIES WHERE interval = '"+time+"' and booking_status = 'Available' ORDER BY facility_name");
	}
	
	public static ArrayList<Facility>   searchFacility (String name)  {  
		return ReturnMatchingFacilitiesList(" SELECT * from FACILITIES WHERE facility_name = '"+name+"' ORDER BY facility_name");
}
	public static ArrayList<Facility> getFacilityTypeList() {
		return returnFacilityTypeList("select facility_type from facilities");
	}
	
	public static ArrayList<Facility> getFacilityNameList() {
		return returnFacilityNameList("select facility_name from facilities");
	}
	
	//Determining the facility being added is unique
	public static Boolean facilityNameUnique(String fname)  {  
		return (ReturnMatchingFacilitiesList(" SELECT * from FACILITIES WHERE facility_name = '"+fname+"' ORDER BY facility_name").isEmpty());
}
}
