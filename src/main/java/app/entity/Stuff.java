package app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 *  This class represents Staff Entity		
 */

@Entity
public class Stuff implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int StuffID;
	private String login;
	private String password;	
	private StuffType type;
	private String name;
	private String secondName;	


	public Stuff() {

	}	

	public int getStuffID() {
		return StuffID;
	}

	public void setStuffID(int stuffID) {
		StuffID = stuffID;
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

	public String getStringForPrint() {
		String txt = "StuffID = " + StuffID + ";   Name = " + name
				+ ";   Password = ";
		txt += "" + password + ";   Type = " + type;
		return txt;
	}
	
	public enum StuffType{
		Administrator,
		Analyst,
		Accountant,
		Supervisor		
	}

}
