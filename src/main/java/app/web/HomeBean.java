package app.web;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import app.entity.*;
import app.service.AirPortService;
import app.service.FlightScheduleService;
import app.service.PassengerService;
import app.service.TicketsService;

@Named
@Scope("session")
public class HomeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private FlightScheduleService flightScheduleService;
	@Inject
    private AirPortService airPortService;
	@Inject
    private PassengerService passengerService;
	@Inject
	private TicketsService ticketsService;
	
	private String departure;
	private String destination;
	private Date departureTime;
	private List<AirPort> airportList;                 // this field will allow customer to
										               // choose airports properly
	private List<FlightSchedule> flightList;           // this field will allow customer
												       // to see the result of search
	private Passenger passenger;                       //field for customer registration
	private FlightSchedule flight;
	private int quontity;                              //this field represents quantity of tickets that customer is going to buy	
	private Date minDate = new Date();                 //this is for calendar
	
	private List<Integer> numbers;
	
	public String user;
	
	public HomeBean() {		
		 
	}
	
	@PostConstruct
	public void init(){			
		passenger = new Passenger();
		numbers = new ArrayList<Integer>();
		for (int i=1; i<7; i++){
			numbers.add(i);
		}
	}
	
	//autoComplete method
	public List<AirPort> completeAirPort(String query) {
        List<AirPort> allAirPorts = airPortService.getAirPortList();       
        List<AirPort> filteredAirPorts = new ArrayList<AirPort>();         
        for (int i = 0; i < allAirPorts.size(); i++) {
        	AirPort airport = allAirPorts.get(i);
            if(airport.getCity().toLowerCase().startsWith(query) || airport.getCity().startsWith(query)) {
            	filteredAirPorts.add(airport);
            }
        }
         
        return filteredAirPorts;
    }
	
	//this method allows passenger who entered his data to buy a ticket
	public String buy(){
		System.out.println(passenger.getStringForPrint());
		Passenger passengerNew = new Passenger();
		passengerNew.setName(passenger.getName());
		passengerNew.seteMail(passenger.geteMail());
		passengerNew.setPhoneNumber(passenger.getPhoneNumber());
		passengerNew.setSecondName(passenger.getSecondName());
		passengerService.buyTicket(passengerNew, quontity, flight);		
		return "afterBuy?faces-redirect=true";
	}

	//this method allows customer to get list of flights for
	//a given departure, destination and date
	public String find() {		
		flightList = flightScheduleService.getTable(departure, destination, departureTime);
		flightList = ticketsService.getTicketsQuontityForFlight(flightList);
		return "resultHome?faces-redirect=true";
	}
	
	public BigDecimal getTotalPrice(){
		return flight.getPrice().multiply(new BigDecimal(quontity));
	}
	
	//Validators
	
	public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String email = (String) value;
		String pattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
		java.util.regex.Matcher m = p.matcher(email);
		if (!m.matches()) {			
            throw new ValidatorException(new FacesMessage("Incorrect e-mail"));
        }
	}
	
	public void validatePhone(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String phone = (String) value;
		String pattern = "^[0-9\\-\\+]{4,15}$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
		java.util.regex.Matcher m = p.matcher(phone);
		if (!m.matches()) {			
            throw new ValidatorException(new FacesMessage("Incorrect phone number"));
        }
	}
	
	public void validateQuontity(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		int quont = (int) value;
		if (quont>flight.getQuontity()) {			
            throw new ValidatorException(new FacesMessage("Only " + flight.getQuontity() + " tickets are available"));
        }
	}
	
	//getters and setters	
	
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

	public List<FlightSchedule> getFlightList() {
		return flightList;
	}

	public void setFlightList(List<FlightSchedule> flightList) {
		this.flightList = flightList;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public int getQuontity() {
		return quontity;
	}

	public void setQuontity(int quontity) {
		this.quontity = quontity;
	}	

	public AirPortService getAirPortService() {
		return airPortService;
	}

	public void setAirPortService(AirPortService airPortService) {
		this.airPortService = airPortService;
	}

	public FlightScheduleService getFlightScheduleService() {
		return flightScheduleService;
	}

	public void setFlightScheduleService(FlightScheduleService flightScheduleService) {
		this.flightScheduleService = flightScheduleService;
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

	public FlightSchedule getFlight() {
		return flight;
	}

	public void setFlight(FlightSchedule flight) {
		this.flight = flight;		
	}	
	
	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}	
	
	public String goToResultPage(){
		return "afterBuy";
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}	
}
