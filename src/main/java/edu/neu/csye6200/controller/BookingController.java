package edu.neu.csye6200.controller;
import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.dao.BookingDao;
import edu.neu.csye6200.dao.BookingDaoImpl;
import edu.neu.csye6200.dao.SeatDao;
import edu.neu.csye6200.dao.SeatDaoImpl;
import edu.neu.csye6200.dao.SeatDao.SeatAvailabilityDao;
import edu.neu.csye6200.dao.SeatDaoImpl.SeatAvailabilityDaoImpl;
import edu.neu.csye6200.model.Booking;

public class BookingController {
	private BookingDao bookingDao = new BookingDaoImpl();
	private SeatController seatController = new SeatController();
	private SeatAvailabilityDao seatAvailabilityDao = new SeatAvailabilityDaoImpl();
	
	public List<Booking> getAllCustomerBookings(int id) {
		List<Booking> booking = bookingDao.getAllBookingsByCustomerId(id);
        return booking;
    }
	
	public Booking getBookingById(int bookingId) {
		return bookingDao.getBookingById(bookingId);
	}
	
    public void addBooking(Booking booking, int numberOfSeats) {
    	List<Integer> seats = seatController.getAvailableSeatsInShow(booking.getShowId());
    	List<Integer> bookedSeats = new ArrayList<Integer>();
    	for (int i=0; i<numberOfSeats; i++) {
    		bookedSeats.add(seats.get(i));
    	}
    	booking.setBookedSeats(bookedSeats);
    	bookingDao.addBooking(booking);
    	seatAvailabilityDao.updateSeatAvailability(booking.getBookedSeats(), booking.getShowId());
    }
    
    public void updateBooking(Booking booking) {
    	bookingDao.updateBooking(booking);
    }
    
    public void deleteBooking(int id) {
    	bookingDao.deleteBooking(id);
    }
}
