package app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import app.dao.AirPortDao;
import app.dao.FlightScheduleDao;
import app.dao.TicketsDao;
import app.entity.AirPort;
import app.entity.FlightSchedule;
import app.entity.ResultReport;
import app.entity.Tickets;
import app.entity.Tickets.Status;

@Named
public class TicketsService {
	@Inject
	private TicketsDao ticketsDao;
	@Inject
	private FlightScheduleDao flightScheduleDao;
	@Inject
	private AirPortDao airPortDao;	

	public TicketsService() {

	}

	@Transactional
	public void submitPayment(Tickets ticket) {
		Tickets ticketConvert = ticketsDao.getTicket(ticket);
		ticketConvert.setStatus(Status.BOUGHT);
		ticketsDao.updateTicket(ticketConvert);
	}
	
	@Transactional
	public void remove(Tickets ticket){
		Tickets ticketToRemove = ticketsDao.getTicket(ticket);
		ticketsDao.removeTicket(ticketToRemove);
	}
	
	@Transactional
	public List<FlightSchedule> getTicketsQuontityForFlight(List<FlightSchedule> flightList){		
		for (FlightSchedule flight : flightList){
			flight.setQuontity(ticketsDao.getFreeTicketsQuontityForFlight(flight));
			System.out.println(flight.getQuontity());
		}		
		return flightList;
	} 
	
	@Transactional
	public void convertFree(Tickets ticket){
		Tickets ticketFree = ticketsDao.getTicket(ticket);
		ticketFree.setStatus(Status.FREE);
		ticketFree.setBuyingDate(null);
		ticketFree.setPassenger(null);
		ticketsDao.updateTicket(ticketFree);
	}

	@Transactional
	public void convertAllBookedTicketsFree() {
		Calendar cal = Calendar.getInstance();
		List<Tickets> listChange = new ArrayList<Tickets>();
		List<Tickets> list = ticketsDao.getListOfReservedTickets();
		for (Tickets n : list) {
			Date date = new Date(n.getBuyingDate().getTime());
			Calendar cal2 = new GregorianCalendar();
			cal2.setTime(date);
			if (daysBetween(cal2, cal) > 3)
				listChange.add(n);
		}		
		for (Tickets n : listChange) {
			n.setStatus(Status.FREE);
			n.setPassenger(null);
			n.setBuyingDate(null);
			ticketsDao.updateTicket(n);
		}
	}
	
	@Transactional
	public void addTickets(int quontity, FlightSchedule flight){		
		FlightSchedule flightSetted = flightScheduleDao.getFlight(flight);		
		for (int i=0; i<quontity; i++){
			Tickets ticket = new Tickets();
			ticket.setFlight(flightSetted);
			ticket.setStatus(Status.FREE);
			ticket.setBuyingDate(null);
			System.out.println("Adding ticket");
			ticketsDao.addTicket(ticket);
		}
	}
	
	@Transactional
	public List<Tickets> getTicketsForFlight(FlightSchedule flight) {
		return ticketsDao.getTicketsForFlight(flight);
	}

	@Transactional
	public List<Tickets> getListOfReservedTickets() {
		return ticketsDao.getListOfReservedTickets();
	}
	
	//!!!in order to work this method efficient it is necessary to change database!!!
	@Transactional
	public List<ResultReport> getDestinationReport(Date fromTime, Date tillTime) {
		List<ResultReport> reportList = new ArrayList<ResultReport>();
		List<AirPort> airList = airPortDao.getAirPortList();
		for (int i=0; i<airList.size(); i++){
			for (int j=0; j<airList.size(); j++){
			if (i==j) continue;
			BigDecimal sum = new BigDecimal(0);
			int quontity = 0;
			List<FlightSchedule> fList = flightScheduleDao.getFlightListByDepartureDestinationTime(airList.get(i), airList.get(j));
			for (int k=0; k<fList.size(); k++){				 
				List<Tickets> tList = ticketsDao.getSoldTicketsForFlight(fList.get(k));
				for (Tickets ticket : tList){
					quontity++;
					sum = sum.add(ticket.getFlight().getPrice());					
				}
			}
			ResultReport report = new ResultReport(airList.get(i), airList.get(j), quontity, sum);
			reportList.add(report);
			}
		}		
		return reportList;
	}	
	
	//this method returns map with quantity and price of sold ticket for each date for date range
	@Transactional
	public List<ResultReport> getTimeReport(Date fromTime, Date tillTime) {		
		List<ResultReport> list = new ArrayList<ResultReport>();
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		cal1.setTime(fromTime);
		cal2.setTime(tillTime);
		Date nextFrom = new Date();
		Date nextTill = new Date();
		while (cal1.before(cal2)){
			nextFrom = cal1.getTime();
			cal1.add(Calendar.DAY_OF_MONTH, 1);
			nextTill = cal1.getTime();
			List<Tickets> ticketsList = ticketsDao.getSoldTicketsForTimePeriod(
					nextFrom, nextTill);
			BigDecimal sum = new BigDecimal(0);			
			for (Tickets ticket : ticketsList){				
				sum = sum.add(ticket.getFlight().getPrice());
			}
			ResultReport report = new ResultReport(nextTill, ticketsList.size(), sum);
			list.add(report);
		}
		return list;
	}	
	
	
	// this method returns number of days between two dates
	public static int daysBetween(Calendar c1, Calendar c2) {
		int daysBetween = 0;
		while (c1.before(c2)) {
			c1.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;
	}	
}
