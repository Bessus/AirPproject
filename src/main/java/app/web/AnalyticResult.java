package app.web;

import java.util.*;
import java.math.BigDecimal;

public class AnalyticResult {
	
	private String route;
	private BigDecimal sum;
	private Date date;
	
	public AnalyticResult(){
		
	}	

	public AnalyticResult(String route, BigDecimal sum, Date date) {
		super();
		this.route = route;
		this.sum = sum;
		this.date = date;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
