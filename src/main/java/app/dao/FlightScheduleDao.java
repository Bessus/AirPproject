package app.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import app.entity.AirPort;
import app.entity.FlightSchedule;

@Repository
public class FlightScheduleDao {
	@PersistenceContext
	private EntityManager em;
	
	public FlightScheduleDao(){
		
	}	
	
	//this method returns list of flights by given departure and destination
	@SuppressWarnings("unchecked")
	public List<FlightSchedule> getFlightListByDepartureDestinationTime(AirPort departure, AirPort destination){
		Query q = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.destination=:destination AND f.departure=:departure");
		q.setParameter("destination", destination);
		q.setParameter("departure", departure);
		
		List<FlightSchedule> flightList = null;
		try{
			flightList = q.getResultList();
		}
		catch(Exception e){
			return null;
		}
		return flightList;
	}
	
	//this method returns full list of flights
	public List<FlightSchedule> getFullTable() {		
		@SuppressWarnings("unchecked")
		List<FlightSchedule> result = em.createQuery("SELECT f FROM FlightSchedule f").getResultList();					
		return result;
	}
	
	public void removeFlight(FlightSchedule flight){
		em.remove(flight);
	}
	
	public void addFlight(FlightSchedule flight){
		em.persist(flight);
	}
	
	public void editFlight(FlightSchedule flight){
		em.merge(flight);
	}
	
	public FlightSchedule getFlight(FlightSchedule flight){
		return em.find(FlightSchedule.class, flight.getFlightID());
	}
	
	public FlightSchedule getFlightByID(int ID){
		return (FlightSchedule) em.createQuery("SELECT f FROM FlightSchedule f WHERE f.flightID=:ID").setParameter("ID", ID).getSingleResult();
	}
	
	//this method returns flightList by given departure, destination and dates
	public List<FlightSchedule> getTable(AirPort departure, AirPort destination, Date date1, Date date2) {
		Query q = em.createQuery("SELECT f FROM FlightSchedule f WHERE f.departure= :departure AND f.destination = :destination AND f.departureTime>:date1 AND f.departureTime<:date2"); 
		q.setParameter("date1", date1);
		q.setParameter("date2", date2);		
		q.setParameter("departure", departure);
		q.setParameter("destination", destination);
		@SuppressWarnings("unchecked")
		List<FlightSchedule> result = q.getResultList();
		return result;
	}
}
