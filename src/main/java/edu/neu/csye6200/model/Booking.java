package edu.neu.csye6200.model;

import java.util.Date;
import java.util.List;

public class Booking {
    private int id;
    private int showId;
    private Date bookingDate;
    private List<Integer> bookedSeats;
    private int customerId;
    	
	public Booking(int showId, Date bookingDate, int customerId) {
		super();
		this.showId = showId;
		this.bookingDate = bookingDate;
		this.customerId = customerId;
	}
	public Booking(int id, int showId, Date bookingDate, List<Integer> bookedSeats, int customerId) {
		super();
		this.id = id;
		this.showId = showId;
		this.bookingDate = bookingDate;
		this.bookedSeats = bookedSeats;
		this.customerId = customerId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public List<Integer> getBookedSeats() {
		return bookedSeats;
	}
	public void setBookedSeats(List<Integer> bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + " show time= " + bookingDate + " Booked seats = " + bookedSeats.toString() + "]";
	}
        
}
