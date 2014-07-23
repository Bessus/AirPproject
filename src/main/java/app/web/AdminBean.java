package app.web;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import java.util.*;
import java.io.Serializable;
import java.math.BigDecimal;

import app.entity.*;
import app.service.AirPortService;
import app.service.FlightScheduleService;
import app.service.TicketsService;

@Named
@Scope("session")
public class AdminBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private FlightScheduleService flightScheduleService;
	@Inject
    private AirPortService airPortService;
	@Inject
	private TicketsService ticketsService;	
	
	private Tickets ticket;
	private int quontity;	
	private FlightSchedule flight;	
	private Date departureTime;	
	private String departure;
	private String destination;
	private double price;
	private List<FlightSchedule> flightList;         //this field will allow Admin to see the list of flights
	private List<Tickets> ticketList;                //this field will allow Admin to see the list of tickets
	private List<AirPort> airportList;
	
	public AdminBean(){		
		
	}
	
	@PostConstruct
	public void init(){
		airportList = airPortService.getAirPortList();
		flightList = flightScheduleService.getFullTable();
		flight = new FlightSchedule();
		flightList = ticketsService.getTicketsQuontityForFlight(flightList);
	}	
	
	public void setEdit(FlightSchedule flight){			
		departure =  flight.getDeparture().toString();
		destination =  flight.getDestination().toString();
		departureTime = flight.getDepartureTime();
		price = flight.getPrice().doubleValue();
	}	
	
	public String convertAllBookedTicketsFree(){
		ticketsService.convertAllBookedTicketsFree();
		init();
		return "adminPage?faces-redirect=true";
	}
	
	public String addFlight(){		
		flightScheduleService.addFlight(departureTime, departure, destination, price);
		init();
		return "adminPage?faces-redirect=true";
	}
		
	
	public String deleteFlight(FlightSchedule flight){
		flightScheduleService.removeFlight(flight);
		init();
		return "adminPage?faces-redirect=true";
	}
	
	public String editFlight(){		
		flight.setDeparture(airPortService.getAirPortByName(departure));
		flight.setDestination(airPortService.getAirPortByName(destination));
		flight.setPrice(new BigDecimal(price));
		flight.setDepartureTime(departureTime);		
		flightScheduleService.editFlight(flight);
		init();
		return "adminPage?faces-redirect=true";
	}
			
	public String getTicketsForFlight(FlightSchedule flight){
		this.flight = flight;		
		ticketList = ticketsService.getTicketsForFlight(flight);		
		return "adminTicketsPage?faces-redirect=true";
	}	
	
	public String deleteTicket(Tickets ticket){		
		if (ticket.getStatus() == Tickets.Status.FREE)
		ticketsService.remove(ticket);		
		return getTicketsForFlight(flight);
	}	
	
	
	public String addTickets(){		
		ticketsService.addTickets(quontity, flight);		
		return getTicketsForFlight(flight);
		
	}	
	
	public String logout()	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "loginPage?faces-redirect=true";
	}
	
	public String goBack(){
		init();
		return "adminPage?faces-redirect=true";
	}
	
	//Validators
	public void validateQuontity(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		int q = (Integer) value;
		
		if (q<1) {		
            throw new ValidatorException(new FacesMessage("number must be more then 0"));
        }
		if (q>50) {			
            throw new ValidatorException(new FacesMessage("number cannot be more than 50"));
        }
	}
	
	//Getters and setters

	public List<FlightSchedule> getFlightList() {
		return flightList;
	}

	public void setFlightList(List<FlightSchedule> flightList) {
		this.flightList = flightList;
	}

	public List<Tickets> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Tickets> ticketList) {
		this.ticketList = ticketList;
	}

	public Tickets getTicket() {
		return ticket;
	}

	public void setTicket(Tickets ticket) {
		this.ticket = ticket;
	}

	public int getQuontity() {
		return quontity;
	}

	public void setQuontity(int quontity) {
		this.quontity = quontity;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}	

	public List<AirPort> getAirportList() {
		return airportList;
	}

	public void setAirportList(List<AirPort> airportList) {
		this.airportList = airportList;
	}

	public FlightSchedule getFlight() {
		return flight;
	}

	public void setFlight(FlightSchedule flight) {
		this.flight = flight;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
