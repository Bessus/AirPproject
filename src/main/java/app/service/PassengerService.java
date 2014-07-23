package app.service;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import app.dao.PassengerDao;
import app.dao.TicketsDao;
import app.entity.FlightSchedule;
import app.entity.Passenger;
import app.entity.Tickets;
import app.entity.Tickets.Status;


@Named
public class PassengerService {
	
	@Inject
    private PassengerDao passengerDao;
	@Inject
    private TicketsDao ticketsDao;
	
	@Transactional
	public void removePassenger(Passenger passenger){
		passengerDao.remove(passenger);
	}
	
	@Transactional
	public void buyTicket(Passenger passenger, int quontity, FlightSchedule flight){		
		passengerDao.addPassenger(passenger);
		Passenger pass = passengerDao.findPassenger(passenger);			
		for (int i=0; i<quontity; i++){
			Tickets ticket = ticketsDao.getOneRandomTicketForFlight(flight);
			ticket.setStatus(Status.RESERVED);
			ticket.setPassenger(pass);
			ticket.setBuyingDate(new Date());
			ticketsDao.updateTicket(ticket);
		}
	}
}
