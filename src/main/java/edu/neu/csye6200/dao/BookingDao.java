package edu.neu.csye6200.dao;

import java.util.List;

import edu.neu.csye6200.model.Booking;

public interface BookingDao {
	List<Booking> getAllBookingsByCustomerId(int customerId);

    Booking getBookingById(int id);

    void addBooking(Booking booking);

    void updateBooking(Booking booking);

    void deleteBooking(int id);
}

