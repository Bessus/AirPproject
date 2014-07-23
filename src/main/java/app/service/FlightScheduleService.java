package app.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import app.dao.AirPortDao;
import app.dao.FlightScheduleDao;
import app.dao.TicketsDao;
import app.entity.AirPort;
import app.entity.FlightSchedule;
import app.entity.Tickets;

@Named
public class FlightScheduleService {
	@Inject
    private FlightScheduleDao flightScheduleDao;
	@Inject
	private TicketsDao ticketsDao;
	@Inject
	private AirPortDao airPortDao;
	
	public FlightScheduleService(){
		
	}
	
	@Transactional
	public void removeFlight(FlightSchedule flight){
		FlightSchedule fligthToRemove = flightScheduleDao.getFlight(flight);           //first we get flight
		List<Tickets> ticketList = ticketsDao.getTicketsForFlight(fligthToRemove);     //get all tickets for this flight
		for (Tickets ticket : ticketList){
			ticketsDao.removeTicket(ticket);                                           //remove the tickets
		}
		flightScheduleDao.removeFlight(fligthToRemove);                                //remove the flight
	}	
	
	@Transactional
	public void editFlight(FlightSchedule flight){
		FlightSchedule flightToEdit = flightScheduleDao.getFlight(flight);
		flightToEdit.setDeparture(flight.getDeparture());
		flightToEdit.setDestination(flight.getDestination());
		flightToEdit.setDepartureTime(flight.getDepartureTime());
		flightToEdit.setPrice(flight.getPrice());
		flightScheduleDao.editFlight(flightToEdit);
	}
	
	@Transactional
	public void addFlight(FlightSchedule flight){
		flightScheduleDao.addFlight(flight);
	}
	
	@Transactional
	public void addFlight(Date departureTime, String departure, String destination, double price){
		AirPort departureAirport = airPortDao.getAirPortByName(departure);
		AirPort destinationAirport = airPortDao.getAirPortByName(destination);
		FlightSchedule flight = new FlightSchedule();
		flight.setDeparture(departureAirport);
		flight.setDestination(destinationAirport);
		flight.setPrice(new BigDecimal(price));
		flight.setDepartureTime(departureTime);
		flightScheduleDao.addFlight(flight);
	}
	
	@Transactional
	public FlightSchedule getFlightByID(int ID){
		return flightScheduleDao.getFlightByID(ID);
	}
	
	@Transactional
    public List<FlightSchedule> getTable(String departure, String destination, Date date) {
		Date date1 = date;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, 23);
		cal.add(Calendar.MINUTE, 59);
		Date date2 = cal.getTime();
		AirPort departureA = airPortDao.getAirPortByName(departure);
		AirPort destinationA = airPortDao.getAirPortByName(destination);
		return flightScheduleDao.getTable(departureA, destinationA, date1, date2);
    }	
	
	@Transactional
    public List<FlightSchedule> getFullTable() {
		return flightScheduleDao.getFullTable();
    }	
}
