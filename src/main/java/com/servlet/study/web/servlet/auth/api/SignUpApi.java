package com.servlet.study.web.servlet.auth.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@WebServlet("/api/v1/auth/addition")
public class SignUpApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUpApi() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("user-id: ");
		System.out.println(request.getParameter("userId"));
		System.out.print("user-Password: ");
		System.out.println(request.getParameter("userPassword"));
		System.out.print("user-Name: ");
		System.out.println(request.getParameter("userName"));
		System.out.print("user-Email: ");
		System.out.println(request.getParameter("userEmail"));
		
		Gson jsonUser = new GsonBuilder().setPrettyPrinting().create();
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", request.getParameter("userId"));
		jsonObject.addProperty("Password", request.getParameter("userPassword"));
		jsonObject.addProperty("Name", request.getParameter("userName"));
		jsonObject.addProperty("Email", request.getParameter("userEmail"));
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonUser.toJson(jsonObject));

	}

}
