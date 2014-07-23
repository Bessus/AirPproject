package app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AirPort implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int airPortID;
	private String iata;
	private String city;
	private String country;
	
	public AirPort(){
		
	}
	
	//this constructor has been added for testing
	public AirPort(String iata, String city, String country){		
		this.iata = iata;
		this.city = city;
		this.country = country;
	}
	
	public int getAirPortID() {
		return airPortID;
	}

	public void setAirPortID(int airPortID) {
		this.airPortID = airPortID;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getStringForPrint() {
		String txt = "airPortID = " + airPortID + ";   iata = '" + iata + "';   city = ";
		txt += "" + city + ";   country = " + country;
		return txt;
	}
	
	@Override
	public String toString() {
		return city;
	}

}


