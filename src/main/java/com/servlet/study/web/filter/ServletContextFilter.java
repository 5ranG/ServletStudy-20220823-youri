package com.servlet.study.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import com.servlet.study.web.domain.user.UserRepository;
import com.servlet.study.web.service.UserServiceImpl;

@WebFilter("/*")
public class ServletContextFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;


	public ServletContextFilter() {
        super();
    }

	public void init(FilterConfig fConfig) throws ServletException {
		// init 은 생성 같은거.
		// 가장 처음에 한 번 실행될 때 아래를 실행하겠다.
		ServletContext context = fConfig.getServletContext(); //fConfig의 객체를 context에 담는다.
		
		context.setAttribute("userRepository", new UserRepository());
		context.setAttribute("userService", new UserServiceImpl((UserRepository)context.getAttribute("userRepository")));
		//set할떄는 object 타입으로 들어가므로 무조건 다운캐스팅 해야한다
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response); 
	}
}
