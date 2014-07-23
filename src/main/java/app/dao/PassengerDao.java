package app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import app.entity.Passenger;


@Repository
public class PassengerDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void remove(Passenger passenger){
		em.remove(passenger);
	}
	
	public void addPassenger(Passenger passenger){	
		em.persist(passenger);
	}
	
	public Passenger findPassenger(Passenger passenger){
		return (Passenger) em.createQuery("SELECT p FROM Passenger p WHERE p.eMail=:eMail").setParameter("eMail", passenger.geteMail()).getSingleResult();
	}
}
