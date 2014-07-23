package app.web;


import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import app.entity.Stuff.StuffType;
import app.service.StuffService;

@Named
@Scope("session")
public class LoginBean {
	@Inject
	private StuffService stuffService;
	
	private String name;
	private String password;
	
	private String message;
	
	public String user;

	public LoginBean() {  }
	
	public String login(){		
		StuffType result =  stuffService.checkLoginAndPassword(name, password);
		if (result == null) {message = "Wrong login/password" ; return "loginPage?faces-redirect=true";} 
		if (result == StuffType.Administrator) {message = ""; user = "Administrator"; return "adminPage?faces-redirect=true";}
		if (result == StuffType.Accountant) {message = ""; user = "Accountant"; return "accauntantPage?faces-redirect=true";}
		if (result == StuffType.Analyst) {message = ""; user = "Analytic"; return "analyticPage?faces-redirect=true";}
		if (result == StuffType.Supervisor) {message = ""; user = "Supervisor"; return "securityPage?faces-redirect=true";}
		return "loginPage?faces-redirect=true";		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}		
}
