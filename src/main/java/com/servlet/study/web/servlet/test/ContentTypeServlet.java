package com.servlet.study.web.servlet.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/content/type")
public class ContentTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청 URL: " + request.getRequestURI());
		System.out.println("요청 Method: " + request.getMethod());
		System.out.println("요청 param(id): " + request.getParameter("id"));
		System.out.println("요청 param(password): " + request.getParameter("password"));
		System.out.println("요청 param(email): " + request.getParameter("email"));
		
		request.getRequestDispatcher("/WEB-INF/dispatcher-test.html").forward(request, response);
		//WEB-INF 폴더는 서버에 있는 것만 가져올 수 있다? 그냥은 못 가져온다
		//다른 파일을 불러왔다
		
//		response.setContentType("application/json; charset=utf-8");
//		
//		response.getWriter().print("{\"name\":\"김준일\"}");
		
//		response.getWriter().print("<html>");
//		response.getWriter().print("<head>");
//		response.getWriter().print("<title>contentType</title>");
//		response.getWriter().print("</head>");
//		response.getWriter().print("<body>");
//		response.getWriter().print("<h1>ContentType Test!</h1>");
//		response.getWriter().print("</body>");
//		response.getWriter().print("</html>");
	}

}
