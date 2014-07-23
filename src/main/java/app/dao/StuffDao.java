package app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import app.entity.Stuff;

@Repository
public class StuffDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Stuff> getStuffList() {		
  		@SuppressWarnings("unchecked")
		List<Stuff> result = em.createQuery("SELECT f FROM Stuff f").getResultList();  			
  		return result;  		
  	}
	
	public void edit(Stuff stuff){
		em.merge(stuff);
	}
	
	public void remove(Stuff stuff){
		em.remove(stuff);
	}
	
	public void addStuff(Stuff stuff){
		em.persist(stuff);
	}
	
	public Stuff getStuffByID(int ID){
		return em.find(Stuff.class, ID);
	}
	
	public Stuff getStuffByLogin(String login){		
		Stuff stuff = null;
		try{
			stuff = (Stuff) em.createQuery("SELECT f FROM Stuff f WHERE f.login=:login").setParameter("login", login).getSingleResult();}
		catch (Exception e){
			e.printStackTrace();
			return null;			
		}		
		return stuff;
	}	
}
