package uta_facility_maintenance_system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uta_facility_maintenance_system.model.*;
import uta_facility_maintenance_system.data.UserDetailDAO;

@WebServlet("/UserDetailController")
public class UserDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void getUserDetailParam(HttpServletRequest request, UserDetail userDetailObject) {
		userDetailObject.setUserDetails(request.getParameter("username"), request.getParameter("firstName"),
				request.getParameter("lastName"), request.getParameter("password"),
				request.getParameter("confirmPassword"), request.getParameter("user_role"),
				request.getParameter("utaID"), request.getParameter("phone"), request.getParameter("email"),
				request.getParameter("address"), request.getParameter("city"), request.getParameter("state"),
				request.getParameter("zipcode"));

	}

	private void getLoginDetailsParam(HttpServletRequest request, UserDetail userDetailObject) {
		userDetailObject.setLoginDetails(request.getParameter("usernameLogin"), request.getParameter("passwordLogin"));
	}

	private void getUserDetailParamAdmin(HttpServletRequest request, UserDetail userDetailObject, String username,
			String userrole, String state) {
		userDetailObject.setUserDetails(username, request.getParameter("firstName"), request.getParameter("lastName"),
				request.getParameter("password"), request.getParameter("confirmPassword"), userrole,
				request.getParameter("utaID"), request.getParameter("phone"), request.getParameter("email"),
				request.getParameter("address"), request.getParameter("city"), state, request.getParameter("zipcode"));
	}

	// Changes as dated on 17/09/2019 3.40 PM
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		session.removeAttribute("errorMsgs");
		// Logout
		if (action.equals("logout")) {
			session = request.getSession(false);
			if (session.getAttribute("uname") != null) {
				session.removeAttribute("uname");
				session.removeAttribute("formtext");
				session.invalidate();
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		}
		// List Users
		if (action.equalsIgnoreCase("listUsers")) {
			ArrayList<UserDetail> usersInDB = new ArrayList<UserDetail>();
			usersInDB = UserDetailDAO.listUsers();
			session.setAttribute("USERS", usersInDB);
			getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request, response);
		} else {
			doPost(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action"), url = "";
		HttpSession session = request.getSession();
		UserDetail userDetailObject = new UserDetail();
		UserDetailErrorMsgs userDetailerrMsgObject = new UserDetailErrorMsgs();
		session.removeAttribute("errorMsgs");

		PrintWriter out = response.getWriter();

		if (action.equalsIgnoreCase("saveUserDetail")) {
			getUserDetailParam(request, userDetailObject);
			userDetailObject.validateUserDetail(action, userDetailObject, userDetailerrMsgObject);
			session.setAttribute("userDetail", userDetailObject);
			if (!userDetailerrMsgObject.getErrorMsg().equals("")) {
				getUserDetailParam(request, userDetailObject);
				session.setAttribute("errorMsgs", userDetailerrMsgObject);
				url = "/registration.jsp";
			} else {
				try {
					UserDetailDAO.insertUserDetail(userDetailObject);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				url = "/index.jsp";
			}
		}

		// Login
		if (action.equalsIgnoreCase("Login")) {
			
			int count = 0;

			getLoginDetailsParam(request, userDetailObject);
			userDetailObject.validateLoginDetails(action, userDetailObject, userDetailerrMsgObject);
			session.setAttribute("userDetail", userDetailObject);
			if (!userDetailerrMsgObject.getLoginErrorMsg().equals("")) {
				getLoginDetailsParam(request, userDetailObject);
				session.setAttribute("errorMsgs", userDetailerrMsgObject);
				url = "/index.jsp";
				++count;
			}
			
			if(count == 0) {
				String uname = request.getParameter("usernameLogin");
				String pw = request.getParameter("passwordLogin");

				if (UserDetailDAO.authenticateUser(uname, pw)) {
					session.setAttribute("uname", uname);
					ArrayList<UserDetail> userList = UserDetailDAO.getUserDetail(uname);
					String user_role = userList.get(0).getUser_role();
					session.setAttribute("urole", user_role);
					if (user_role.equals("Admin")) {
						url = "/adminHomePage.jsp";
					}
					if (user_role.equals("User")) {
						url = "/homepage.jsp";
					}
					if (user_role.equals("Facility Manager")) {
						url = "/main.jsp";
					}
					if (user_role.equals("Repairer")) {
						url = "/RepairerHomePage.jsp";
					}
				} else {
					/* out.println("Invalid Username or password"); */
					session.setAttribute("message", userDetailObject);
					userDetailObject.setGreetingText("Invalid Username or Password");
					url = "/index.jsp";

				}


				
			}

					}

		// View Selected User
		if (action.equalsIgnoreCase("viewSelectedUser")) {
			ArrayList<UserDetail> usersInDB = new ArrayList<UserDetail>();
			UserDetail selectedUser = new UserDetail();
			String userName = "";
			if (request.getParameter("id").isEmpty()) {
				userName = (String) session.getAttribute("uname");
			} else {
				userName = request.getParameter("id");
			}

			usersInDB = UserDetailDAO.getUserDetail(userName);
			selectedUser.setUserDetails(usersInDB.get(0).getUsername(), usersInDB.get(0).getFirst_name(),
					usersInDB.get(0).getLast_name(), usersInDB.get(0).getPassword(),
					usersInDB.get(0).getConfirm_password(), usersInDB.get(0).getUser_role(),
					usersInDB.get(0).getUta_id(), usersInDB.get(0).getPhone(), usersInDB.get(0).getEmail(),
					usersInDB.get(0).getStreet_address(), usersInDB.get(0).getCity(), usersInDB.get(0).getState(),
					usersInDB.get(0).getZipcode());
			session.setAttribute("viewSelectedUser", selectedUser);
			url = "/ModifySelectedUser.jsp";
		}

		// View Profile
		if (action.equalsIgnoreCase("viewProfile")) {
			ArrayList<UserDetail> usersInDB = new ArrayList<UserDetail>();
			UserDetail selectedUser = new UserDetail();
			String userName = (String) session.getAttribute("uname");

			usersInDB = UserDetailDAO.getUserDetail(userName);
			selectedUser.setUserDetails(usersInDB.get(0).getUsername(), usersInDB.get(0).getFirst_name(),
					usersInDB.get(0).getLast_name(), usersInDB.get(0).getPassword(),
					usersInDB.get(0).getConfirm_password(), usersInDB.get(0).getUser_role(),
					usersInDB.get(0).getUta_id(), usersInDB.get(0).getPhone(), usersInDB.get(0).getEmail(),
					usersInDB.get(0).getStreet_address(), usersInDB.get(0).getCity(), usersInDB.get(0).getState(),
					usersInDB.get(0).getZipcode());
			session.setAttribute("viewSelectedUser", selectedUser);
			url = "/ViewMyProfile.jsp";
		}

		// modify user's profile
		if (action.equalsIgnoreCase("modifyUserDetail")) {
			String userrole = "", state = "", username = "";
			int result = 0;
			UserDetail userDetail = (UserDetail) session.getAttribute("viewSelectedUser");
			ArrayList<UserDetail> usersInDB = new ArrayList<UserDetail>();

			if (session.getAttribute("urole").equals("Admin")) {
				usersInDB = UserDetailDAO.getUserDetail(request.getParameter("username"));
				if (request.getParameter("user_role").equals("")) {
					userrole = usersInDB.get(0).getUser_role();
				} else {
					userrole = request.getParameter("user_role");
				}
				if (request.getParameter("username").equals("")) {
					userrole = usersInDB.get(0).getUsername();
				} else {
					username = request.getParameter("username");
				}
			} else {
				userrole = (String) session.getAttribute("urole");
			}
			if (request.getParameter("state").equals("")) {
				state = userDetail.getState();
			} else {
				state = request.getParameter("state");
			}
			getUserDetailParamAdmin(request, userDetailObject, username, userrole, state);
			userDetailObject.validateUserDetail(action, userDetailObject, userDetailerrMsgObject);
			session.setAttribute("userDetail", userDetailObject);
			if (!userDetailerrMsgObject.getErrorMsg().equals("")) {
				getUserDetailParamAdmin(request, userDetailObject, username, request.getParameter("user_role"),
						request.getParameter("state"));
				session.setAttribute("errorMsgs", userDetailerrMsgObject);
				url = "/ModifySelectedUser.jsp";
			} else {
				try {
					result = UserDetailDAO.updateUserDetail(userDetailObject);
					if (result > 0) {
						userDetail.setGreetingText("Profile has been successfully updated.");
						session.setAttribute("formtext", userDetail);
					} else {
						userDetail.setGreetingText("Profile has been not successfully updated.");
						session.setAttribute("formtext", userDetail);
					}
					url = "/ModifySelectedUser.jsp";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// Modify own Profile
		if (action.equalsIgnoreCase("modifyProfile")) {
			String userrole = "", state = "", username = "";
			int result = 0;
			UserDetail userDetail = (UserDetail) session.getAttribute("viewSelectedUser");
			userrole = (String) session.getAttribute("urole");
			if (request.getParameter("state").equals("")) {
				state = userDetail.getState();
			} else {
				state = request.getParameter("state");
			}
			username = (String) session.getAttribute("uname");
			getUserDetailParamAdmin(request, userDetailObject, username, userrole, state);
			userDetailObject.validateUserDetail(action, userDetailObject, userDetailerrMsgObject);

			session.setAttribute("userDetail", userDetailObject);
			if (!userDetailerrMsgObject.getErrorMsg().equals("")) {
				getUserDetailParamAdmin(request, userDetailObject, username, request.getParameter("user_role"),
						request.getParameter("state"));
				session.setAttribute("errorMsgs", userDetailerrMsgObject);
				url = "/ModifyMyProfile.jsp";
			} else {
				try {
					result = UserDetailDAO.updateUserDetail(userDetailObject);
					System.out.println("result-------------------" + result);
					if (result > 0) {
						userDetail.setGreetingText("Profile has been successfully updated.");
						session.setAttribute("formtext", userDetail);
					} else {
						userDetail.setGreetingText("Profile has not been successfully updated.");
						session.setAttribute("formtext", userDetail);
					}
					url = "/ModifyMyProfile.jsp";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		/*// Search user by username
		if (action.equalsIgnoreCase("searchUser")) {
			String uname = request.getParameter("username");
			ArrayList<UserDetail> usersInDB = new ArrayList<UserDetail>();
			usersInDB = UserDetailDAO.getUserByName(uname);
			session.setAttribute("USERS", usersInDB);
			url = "/listUsers.jsp";
		}*/
		
		
		// Search User By Username
		if (action.equalsIgnoreCase("searchUser")) {
			String uname = request.getParameter("username");
			ArrayList<UserDetail> usersInDB = new ArrayList<UserDetail>();
			usersInDB = UserDetailDAO.searchUser(uname);
			if (usersInDB.size() == 0) {
				String errorMsgs = "No such user in the database";
				session.setAttribute("errorMsgs",errorMsgs);
				url="/listUsers.jsp";	
			}
			session.setAttribute("USERS", usersInDB);
			url = "/listUsers.jsp";
		
		}

		// Redirect to user home page
		if (action.equalsIgnoreCase("homepage")) {
			String user_role = (String) session.getAttribute("urole");
			if (user_role.equals("Admin")) {
				url = "/adminHomePage.jsp";
			}
			if (user_role.equals("User")) {
				url = "/homepage.jsp";
			}
			if (user_role.equals("Facility Manager")) {
				url = "/main.jsp";
			}
			if (user_role.equals("Repairer")) {
				url = "/RepairerHomePage.jsp";
			}
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
