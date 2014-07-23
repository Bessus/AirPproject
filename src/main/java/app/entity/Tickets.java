package app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Tickets implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ticketID;
	private Status status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date buyingDate;		
	@ManyToOne
	@JoinColumn(name="Customer")
	private Passenger passenger;	
	@ManyToOne
	@JoinColumn(name="FlightSchedule")
	private FlightSchedule flightSchedule;
	
	public Tickets(){
		
	}	
		
	// this constructor has been added for testing
	public Tickets(int ticketID, Status status, Date buyingDate,
			Passenger passenger, FlightSchedule flightSchedule) {
		super();
		this.ticketID = ticketID;
		this.status = status;
		this.buyingDate = buyingDate;		
		this.passenger = passenger;
		this.flightSchedule = flightSchedule;
	}

	public String getStringForPrint() {
		String txt = "TicketID = " + ticketID + ";     ";
		txt += "Status = " + status + ";";
		SimpleDateFormat dtFrm = new SimpleDateFormat("dd.MM.yyyy"); 
		if (buyingDate != null)
        txt += dtFrm.format(buyingDate); else txt+= null;
        txt+= ";   FlightSchedule = " +  flightSchedule.getFlightID() + ";   Customer = " +  passenger;		
		return txt;
	}
	
	public int getTicketID() {
		return ticketID;
	}
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public java.util.Date getBuyingDate() {
		return buyingDate;
	}

	public void setBuyingDate(Date buyingDate) {
		this.buyingDate = buyingDate;
	}

	public FlightSchedule getFlight() {
		return flightSchedule;
	}

	public void setFlight(FlightSchedule flight) {
		this.flightSchedule = flight;
	}
	
	public enum Status{
		FREE,
		RESERVED,
		BOUGHT		
	}
}


