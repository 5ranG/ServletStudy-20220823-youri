package com.servlet.study.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.study.web.servlet.dto.PrincipalUser;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter implements Filter {
       
    public AuthenticationFilter() {
        super();
    }
    
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; //다운캐스팅
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession();
		
		String uri = httpServletRequest.getRequestURI();
		
		if(!uri.contains("static") && !uri.contains("index") && !uri.contains("auth/signin") && !uri.contains("auth/signup")) {
			PrincipalUser principalUser = (PrincipalUser) session.getAttribute("principal");
			
			if(principalUser == null) { //로그인이 안되어 있는 경우
				if(uri.contains("auth/signin")) {					
					session.setAttribute("preUri", uri);
				}
				
				
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("<html>");
				stringBuilder.append("<body>");
				stringBuilder.append("<script>");

				stringBuilder.append("alert(\"로그인 후 접근할 수 있습니다.\");");
				stringBuilder.append("location.replace(\"/auth/signin\")");
				stringBuilder.append("</script>");
				stringBuilder.append("</body>");
				stringBuilder.append("</html>");
				
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().print(stringBuilder.toString());
				return;
			}else if(uri.contains("auth/signin")) {
				String preUri = (String) session.getAttribute("preUri"); //이전 페이지가 있으면?
				if(preUri == null) {
					httpServletResponse.sendRedirect("/index"); //없으면
				}else {
					httpServletResponse.sendRedirect("preUri"); //있으면
				}
				session.setAttribute("preUri", null); //한번 쭉 돌렸으니 버리자!
				return;
			}
		}

		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
