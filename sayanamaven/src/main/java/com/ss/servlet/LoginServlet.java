
package com.ss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ss.dao.*;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
        
    }
    
    @Override
    public void init() {
    	System.out.println("**************************************");
    	System.out.println("**************************************");
        System.out.println("********The Servlet is working********");
    	System.out.println("**************************************");
    	System.out.println("**************************************");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Gson gson = new Gson();
		Login login = gson.fromJson(request.getReader(), Login.class); //changing user input json to login obj of login class
	
		PrintWriter out = response.getWriter();
		
		List<Login> users = new ArrayList<Login>();
		
		users.add(new Login( "Sayana", "Shrestha"));
		users.add(new Login("Joby", "Santosh"));
		users.add(new Login("Janet", "Fernando"));
		users.add(new Login("Afia", "Farjana"));
		
		//login.getUsername is user input and row.getUsername is existing
		
		List<Login> find = users.stream()
						.filter(newUser -> 
											(newUser.getUsername().equals(login.getUsername())) && 
											(newUser.getPassword().equals(login.getPassword())))
						.collect(Collectors.toList());
		
		if((!find.isEmpty()) && (find != null)){
			out.print("Sucess");
			
		}
		else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	
		
	}
}

