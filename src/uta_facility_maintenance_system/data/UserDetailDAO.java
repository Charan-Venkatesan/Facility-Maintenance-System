
package uta_facility_maintenance_system.data;

import uta_facility_maintenance_system.util.SQLConnection;
import uta_facility_maintenance_system.model.UserDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDetailDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	// List Users
	public static ArrayList<UserDetail> ReturnMatchingUserList(String queryString) {
		ArrayList<UserDetail> userListInDB = new ArrayList<UserDetail>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				UserDetail userDetail = new UserDetail();
				userDetail.setUsername(userList.getString("username"));
				userDetail.setUser_role(userList.getString("user_role"));
				userDetail.setFirst_name(userList.getString("first_name"));
				userDetail.setLast_name(userList.getString("last_name"));
				userDetail.setUta_id(userList.getString("UTA_ID"));
				userDetail.setPhone(userList.getString("phone"));
				userDetail.setEmail(userList.getString("email"));
				userDetail.setStreet_address(userList.getString("street_address"));
				userDetail.setState(userList.getString("state"));
				userDetail.setCity(userList.getString("city"));
				userDetail.setZipcode(userList.getString("zip_code"));
				userListInDB.add(userDetail);
			}
		} catch (SQLException e) {
		}

		return userListInDB;
	}

	public static void insertIntoDB(UserDetail userDetailObject, String queryString) throws SQLException {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String insertUserDetail = queryString + " VALUES ('" + userDetailObject.getUsername() + "','"
					+ userDetailObject.getFirst_name() + "','" + userDetailObject.getLast_name() + "','"
					+ userDetailObject.getPassword() + "','" + userDetailObject.getUser_role() + "','"
					+ userDetailObject.getUta_id() + "','" + userDetailObject.getPhone() + "','"
					+ userDetailObject.getEmail() + "','" + userDetailObject.getStreet_address() + "','"
					+ userDetailObject.getCity() + "','" + userDetailObject.getState() + "','"
					+ userDetailObject.getZipcode() + "')";
			// System.err.println("printing query string..."+insertUserDetail);
			stmt.executeUpdate(insertUserDetail);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Inserting a User into the database
	public static void insertUserDetail(UserDetail userDetailObject) throws SQLException {
		insertIntoDB(userDetailObject,
				"INSERT INTO user_details (username,first_name, last_name, password, user_role, uta_id, phone, email, street_address, city, state, zip_code) ");
	}

	// Authenticating a user from fetching the values from database
	public static boolean authenticateUser(String username, String password) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		System.out.println(conn);
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user_details WHERE username = '" + username
					+ "' AND password = '" + password + "' ");
			if (!rs.first())
				return false;

		} catch (SQLException e) {

		}
		return true;

	}
/*
	public static String authenticateUserUsingRole(String username, String password) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		String type = "";
		try {
			stmt = conn.createStatement();
			ResultSet rst = stmt.executeQuery("Select user_role from user_details where username = '" + username
					+ "' AND  password = '" + password + "' ");
			type = rst.getString("user_role");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("type");

		return type;
	}
	*/

	public static int updateIntoDB(String queryString) throws SQLException {
		Statement stmt = null;
		int result = 0;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(queryString);
			System.out.println(queryString);
			System.out.println("RESULT---------------------------"+result);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<UserDetail> listUsers() {
		return ReturnMatchingUserList("select * from user_details");

	}

	public static ArrayList<UserDetail> searchUser(String username) {
		return ReturnMatchingUserList(
				"select * from user_details where username = '" + username + "' ORDER BY username");
	}

	public static ArrayList<UserDetail> getUserDetail(String uname) {
		return ReturnMatchingUserList("SELECT * FROM user_details WHERE  username= '" + uname + "'");
	}

	public static ArrayList<UserDetail> getUserByName(String uname) {
		return ReturnMatchingUserList("SELECT * FROM user_details WHERE  username LIKE '%" + uname + "%'");
	}

	public static int updateUserDetail(UserDetail userDetailObject) throws SQLException {
		return updateIntoDB("UPDATE user_details SET username = '" + userDetailObject.getUsername() + "', first_name = '"
				+ userDetailObject.getFirst_name() + "', last_name = '" + userDetailObject.getLast_name()
				+ "', user_role = '" + userDetailObject.getUser_role() + "', UTA_ID = '" + userDetailObject.getUta_id()
				+ "', phone = '" + userDetailObject.getPhone() + "', email = '" + userDetailObject.getEmail()
				+ "', street_address = '" + userDetailObject.getStreet_address() + "', city = '"
				+ userDetailObject.getCity() + "', state = '" + userDetailObject.getState() + "', zip_code = '"
				+ userDetailObject.getZipcode() + "' WHERE username = '" + userDetailObject.getUsername() + "'");
	}
	
	//Determining the user being added has unique username
		public static Boolean userNameUnique(String uname)  {  
			return (ReturnMatchingUserList(" SELECT * from USER_DETAILS WHERE username = '"+uname+"' ORDER BY username").isEmpty());
		}
}
