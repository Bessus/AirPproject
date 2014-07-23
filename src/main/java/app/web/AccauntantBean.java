package app.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import app.entity.Tickets;
import app.service.TicketsService;

@Named
@Scope("session")
public class AccauntantBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TicketsService ticketsService;
	
	private List<Tickets> ticketList; 
	
	public AccauntantBean(){
		
	}
	
	@PostConstruct
	public void init(){
		ticketList = ticketsService.getListOfReservedTickets();
	}
	
	public String logout()	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "loginPage?faces-redirect=true";
	}
	
	public String submitPayment(Tickets ticket){
		ticketsService.submitPayment(ticket);
		init();
		return "accauntantPage?faces-redirect=true";
	}
	
	public String convertFree(Tickets ticket){
		ticketsService.convertFree(ticket);
		init();
		return "accauntantPage?faces-redirect=true";
	}	

	public List<Tickets> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Tickets> ticketList) {
		this.ticketList = ticketList;
	}
}
