package app.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class FlightSchedule implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightID;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date departureTime;
	@ManyToOne(targetEntity = AirPort.class)
	@JoinColumn(name = "Departure")
	private AirPort departure;
	@ManyToOne(targetEntity = AirPort.class)
	@JoinColumn(name = "Destination" )
	private AirPort destination;
	private BigDecimal price;
	transient private long quontity;
	
	public FlightSchedule() {

	}	
	
	// this constructor has been added for testing
	public FlightSchedule(Date departureTime, AirPort departure,
			AirPort destination) {
		super();		
		this.departureTime = departureTime;
		this.departure = departure;
		this.destination = destination;
	}	

	public java.util.Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(java.util.Date depatureTime) {
		this.departureTime = depatureTime;
	}

	public int getFlightID() {
		return flightID;
	}

	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}

	public AirPort getDeparture() {
		return departure;
	}

	public void setDeparture(AirPort departure) {
		this.departure = departure;
	}

	public AirPort getDestination() {
		return destination;
	}

	public void setDestination(AirPort destination) {
		this.destination = destination;
	}
	
	

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	//!!!this is for testing only!!!
	public String getStringForPrint() {
		String txt = "FlightID = " + flightID + ";   DepatureDate = ";
		SimpleDateFormat dtFrm = new SimpleDateFormat("dd.MM.yyyy");
		txt += dtFrm.format(departureTime) + ";   DepatureTime = "
				+ departureTime + ";   departure = " + departure.getCity();
		txt += ";    destination = " + destination.getCity() + ";   Price = " + price; 
		return txt;
	}

	public long getQuontity() {
		return quontity;
	}

	public void setQuontity(long quontity) {
		this.quontity = quontity;
	}

	@Override
	public String toString() {
		return "FlightSchedule [departureTime=" + departureTime
				+ ", departure=" + departure + ", destination=" + destination
				+ "]";
	}	
}
