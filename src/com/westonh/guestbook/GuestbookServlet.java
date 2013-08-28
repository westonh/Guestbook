package com.westonh.guestbook;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class GuestbookServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		String thisURL = req.getRequestURI();
		resp.setContentType("text/html");
		
		if(user != null) {
			resp.getWriter().println("<p>Hello, " +
                    user.getNickname() +
                    "!  You can <a href=\"" +
                    userService.createLogoutURL(thisURL) +
                    "\">sign out</a>.</p>");
		}
		else {
			resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
		}
		
	}

}
