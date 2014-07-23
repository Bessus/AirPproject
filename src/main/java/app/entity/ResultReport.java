package app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ResultReport {
	
	//fields for time report
	private Date date;	
	
	//fields for destination report
	private AirPort destination;
	private AirPort departure;
	
	//shared fields
	private int quantity;
	private BigDecimal sum;
	
	
	public ResultReport(){
		
	}	
	
	//Constructor for time report
	public ResultReport(Date date, int quantity, BigDecimal sum) {
		super();
		this.date = date;
		this.quantity = quantity;
		this.sum = sum;
	}
	
	//Constructor for destination report
	public ResultReport(AirPort departure, AirPort destination, int quantity, BigDecimal sum) {
		super();
		this.departure = departure;
		this.destination = destination;
		this.quantity = quantity;
		this.sum = sum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public AirPort getDestination() {
		return destination;
	}

	public void setDestination(AirPort destination) {
		this.destination = destination;
	}

	public AirPort getDeparture() {
		return departure;
	}

	public void setDeparture(AirPort departure) {
		this.departure = departure;
	}	
	
}
