package app.web;

import java.io.IOException;

import javax.inject.Named;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Named
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String url = request.getRequestURI();
		LoginBean login =null;
		if (session != null) login = (LoginBean) session.getAttribute("loginBean");

		if ((session == null || login == null) ) { 
			if (url.contains("home.xhtml") || url.contains("resultHome.xhtml") || url.contains("errorpage.xhtml")
					|| url.contains("loginPage.xhtml") || url.contains("javax.faces.resource")) {
				chain.doFilter(req, res);						
			} else {				
				response.sendRedirect(request.getContextPath() + "/loginPage.xhtml");
			} // No logged-in user found, so redirect to login page.
	
		} else if (url.contains("javax.faces.resource")){
				chain.doFilter(req, res);	// for static resources		
		} else if (((url.contains("adminPage.xhtml") || url.contains("adminTicketsPage.xhtml"))) && 
				"Administrator".equals(login.user)){
				chain.doFilter(req, res);			
		} else if ((url.contains("securityPage.xhtml")  && 	"Supervisor".equals(login.user))) {
				chain.doFilter(req, res);
		} else if ((url.contains("analyticPage.xhtml") || url.contains("resultAnalyticDestination.xhtml") ||
				url.contains("resultAnalyticTime.xhtml")) && "Analytic".equals(login.user)) {
				chain.doFilter(req, res);
		} else if (url.contains("accauntantPage.xhtml") && "Accountant".equals(login.user)) {
				chain.doFilter(req, res);	
		} else if (url.contains("home.xhtml") || url.contains("resultHome.xhtml") || url.contains("errorpage.xhtml")
				|| url.contains("afterBuy.xhtml") || url.contains("loginPage.xhtml")) {
			chain.doFilter(req, res);
	    } else { if (url.contains("resultAnalyticTime.xhtml")) 
	    	response.sendRedirect(request.getContextPath() + "/loginPage.xhtml");} // No logged-in user found, so redirect to login page.
	}	

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
//&& !url.contains("javax.faces.resource")

