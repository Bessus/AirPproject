package app.web;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import app.entity.Stuff;
import app.entity.Stuff.StuffType;
import app.service.StuffService;

import java.util.*;

@Named
@Scope("session")
public class SecurityBean {
	
		@Inject
		private StuffService stuffService;
		private List<StuffType> typeList;
		private List<Stuff> stuffList;
		private Stuff stuff;
				
		private String login;
		private String password;
		private String name;
		private String secondName;
		private StuffType type; 
		
		public SecurityBean(){				
		}
		
		@PostConstruct
		public void init(){
			stuffList = stuffService.getStuffList();	
			stuff = new Stuff();
			typeList = new ArrayList<StuffType>();
			typeList.add(StuffType.Accountant);
			typeList.add(StuffType.Administrator);
			typeList.add(StuffType.Analyst);
		}		
		
		public String addStuff(){			
			Stuff stuffNew = new Stuff();
			stuffNew.setName(stuff.getName());
			stuffNew.setPassword(stuff.getPassword());
			stuffNew.setType(stuff.getType());
			stuffNew.setLogin(stuff.getLogin());
			stuffNew.setSecondName(stuff.getSecondName());
			stuffService.addStuff(stuffNew);
			init();
			return "securityPage?faces-redirect=true";
		}
		
		public String logout()	{
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "loginPage?faces-redirect=true";
		}	
		
		public String delete(Stuff stuff){
			if (stuff.getType()==StuffType.Supervisor) return "securityPage?faces-redirect=true"; // in order to prevent a possibility of deleting supervisor
			stuffService.remove(stuff);
			init();
			return "securityPage?faces-redirect=true";
		}
		
		public void setEdit(Stuff stuff){			
			this.stuff = stuff;
			name = stuff.getName();
			login = stuff.getLogin();
			secondName = stuff.getSecondName();
			password = stuff.getPassword();
			type = stuff.getType();
		}	
		
		public String edit(){
			stuff.setLogin(login);
			stuff.setSecondName(secondName);
			stuff.setName(name);
			stuff.setPassword(password);
			stuff.setType(type);
			stuffService.edit(stuff);
			init();
			return "securityPage?faces-redirect=true";
		}
		
		public List<Stuff> getStuffList() {
			return stuffList;
		}
		public void setStuffList(List<Stuff> stuff) {
			this.stuffList = stuff;
		}

		public Stuff getStuff() {
			return stuff;
		}

		public void setStuff(Stuff stuff) {
			this.stuff = stuff;
		}

		public List<StuffType> getTypeList() {
			return typeList;
		}

		public void setTypeList(List<StuffType> typeList) {
			this.typeList = typeList;
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

		public StuffType getType() {
			return type;
		}

		public void setType(StuffType type) {
			this.type = type;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getSecondName() {
			return secondName;
		}

		public void setSecondName(String secondName) {
			this.secondName = secondName;
		}
		
		
}
