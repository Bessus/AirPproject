package app.dao;

import java.util.List;

import app.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;




import org.springframework.stereotype.Repository;

@Repository
public class AirPortDao {
	@PersistenceContext
	private EntityManager em;	
	
  	public AirPortDao(){
		
	}
  	
  	public void removeAirPort(AirPort airPort){
  		em.remove(airPort);
  	}
  	
  	public AirPort getAirPortByName(String city){  		
  		return (AirPort) em.createQuery("SELECT p from AirPort p where p.city = :city").setParameter("city", city).getSingleResult();  		
  	}
  	
  	public List<AirPort> getAirPortList() {  
  		@SuppressWarnings("unchecked")
		List<AirPort> result = em.createQuery("SELECT f FROM AirPort f").getResultList();  		
  		return result;  		
  	}
}
