package app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import app.entity.FlightSchedule;
import app.entity.Tickets;

@Repository
public class TicketsDao {
	
	@PersistenceContext
	private EntityManager em;	
	
	
	public void addTicket(Tickets ticket){
		em.persist(ticket);
	}
	
	//this method returns quantity of free tickets for a given flight
	public Long getFreeTicketsQuontityForFlight(FlightSchedule flight){
		System.out.println("Inside getTicketsForFlightMethod");
		Query q = em.createQuery("SELECT count(t) FROM Tickets t WHERE t.flightSchedule=:flight AND t.status=0");
		q.setParameter("flight", flight);
		long result=0;
		try{result = (Long) q.getSingleResult();}
		catch (Exception e){ e.printStackTrace(); ; return (long) 0; }		
		return result;
	}
		
	//This method returns a List of tickets with status 1; 
	@SuppressWarnings("unchecked")
	public List<Tickets> getListOfReservedTickets(){
		List<Tickets> result = null;
		try{
		result = em.createQuery("SELECT t FROM Tickets t WHERE t.status=1").getResultList();}
		catch(Exception e){return new ArrayList<Tickets>();}
		return result;
	}
	
	//this method updates given ticket 
	public void updateTicket(Tickets ticket){		
		em.merge(ticket);			
	}
	
	//this method returns list of tickets that have been sold during time period between two given dates	
	@SuppressWarnings("unchecked")
	public List<Tickets> getSoldTicketsForTimePeriod(Date dateFrom, Date dateTill){
		Query q = em.createQuery("SELECT t FROM Tickets t WHERE (t.status=1 OR t.status=2) AND t.buyingDate>:dateFrom AND t.buyingDate<:dateTill");
		q.setParameter("dateFrom", dateFrom);
		q.setParameter("dateTill", dateTill);
		return q.getResultList();
	}
	
	//this method returns list of tickets for certain flight that have been sold
	@SuppressWarnings("unchecked")
	public List<Tickets> getSoldTicketsForFlight(FlightSchedule flight){	
		System.out.println(flight.getStringForPrint());
		Query q2 = em.createQuery("SELECT t FROM Tickets t WHERE (t.status=1 OR t.status=2) AND t.flightSchedule=:flight");
		q2.setParameter("flight", flight);
		return q2.getResultList();
	}
	
	//this method removes given ticket
	public void removeTicket(Tickets ticket){
  		em.remove(ticket);
  	}
	
	//This method returns list of tickets for certain flight
	@SuppressWarnings("unchecked")
	public List<Tickets> getTicketsForFlight(FlightSchedule flight){		
		Query q = em.createQuery("SELECT t FROM Tickets t WHERE t.flightSchedule = :flight)");
		q.setParameter("flight", flight);
		return q.getResultList();
	}	
	
	//This method returns one ticket for certain flight with minimum ID
	public Tickets getOneRandomTicketForFlight(FlightSchedule flight){		
		Query q = em.createQuery("SELECT min(t.ticketID) FROM Tickets t WHERE t.flightSchedule=:flight AND t.status=0");
		q.setParameter("flight", flight);		
		int ticketID = (int) q.getSingleResult();
		Tickets ticket = em.find(Tickets.class, ticketID);			
		return ticket;
	}
	
	public Tickets getTicket(Tickets ticket){
		return em.find(Tickets.class, ticket.getTicketID());
	}
	
	
}