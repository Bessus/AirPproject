package app.web;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import app.entity.*;
import app.service.AirPortService;
import app.service.TicketsService;

@Named
@Scope("session")
public class AnalyticBean {
	
	@Inject
    private AirPortService airPortService;	
	@Inject
	private TicketsService ticketsService;	
	
	private Date fromTime;
	private Date tillTime;	
	private BigDecimal sum;
	private List<AnalyticResult> dateListResult;	
	private List<AirPort> airportList; 
	private List<FlightSchedule> flightList;
	private List<ResultReport> reportList;

	public AnalyticBean() {		
		
	}
	
	@PostConstruct
	public void init(){		
		airportList = airPortService.getAirPortList();		
	}
	
	public String logout()	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "loginPage?faces-redirect=true";
	}
	
	public String getDestinationReport(){		
		reportList = ticketsService.getDestinationReport(fromTime, tillTime);
		return "resultAnalyticDestination?faces-redirect=true";
	}
	
	public String getTimeReport(){		
		reportList = ticketsService.getTimeReport(fromTime, tillTime);
		return "resultAnalyticTime?faces-redirect=true";
	}	
	
	//Getters and setters
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

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getTillTime() {
		return tillTime;
	}

	public void setTillTime(Date tillTime) {
		this.tillTime = tillTime;
	}	

	public List<AnalyticResult> getDateListResult() {
		return dateListResult;
	}

	public void setDateListResult(List<AnalyticResult> dateListResult) {
		this.dateListResult = dateListResult;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public List<ResultReport> getReportList() {
		return reportList;
	}

	public void setReportList(List<ResultReport> reportList) {
		this.reportList = reportList;
	}	
}
