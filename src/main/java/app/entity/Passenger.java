package app.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
public class Passenger implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int passengerID;
	private String name;
	private String secondName;
	private String eMail;
	private String phoneNumber;
	
	public Passenger(){
		
	}
	
	public Passenger(String name, String secondName, String eMail, String phoneNumber){
		this.name = name;
		this.secondName = secondName;
		this.eMail = eMail;
		this.phoneNumber = phoneNumber;
	}

	public int getPassengerID() {
		return passengerID;
	}

	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getStringForPrint() {
		String txt = "PassengerID = " + passengerID + ";   name = '" + name + "';   secondName = ";
		txt += "" + secondName + ";   eMail = " + eMail + ";   phoneNumber = " + phoneNumber;
		return txt;
	}
	
	@Override
	public String toString() {
		return name + " " + secondName;
	}
}
