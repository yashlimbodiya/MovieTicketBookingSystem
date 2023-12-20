package edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.model.Booking;
import edu.neu.csye6200.repository.DatabaseConnection;

public class BookingDaoImpl implements BookingDao{
	Connection connection = DatabaseConnection.getDbInstance();
	
	@Override
	public List<Booking> getAllBookingsByCustomerId(int customerId) {
		List<Booking> customerBookings = new ArrayList<Booking>();
		ResultSet rs = null;
		try {
			String sqlQuery = "select * from booking where customer_Id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,customerId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("booking_Id");
				int show_id = rs.getInt("show_Id");
				Date showDate = rs.getDate("booking_Date");
				int customer_id = rs.getInt("customer_Id");
				List<Integer> seats = getBookedSeats(id);
				customerBookings.add(new Booking(id, show_id, showDate, seats, customer_id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return customerBookings;
	}

	@Override
	public Booking getBookingById(int id) {
		ResultSet rs = null;
		try {
			String sqlQuery = "select * from booking where booking_Id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int bookingId = rs.getInt("booking_Id");
				int showId = rs.getInt("show_Id");
				Date showDate = rs.getDate("booking_Date");
				int customer_id = rs.getInt("customer_Id");
				List<Integer> seats = getBookedSeats(id);
				return new Booking(bookingId, showId, showDate, seats, customer_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	public List<Integer> getBookedSeats(int bookingId) {
		List<Integer> seats = new ArrayList<Integer>();
		ResultSet rs = null;
		try {
			String sqlQuery = "SELECT DISTINCT seat_Id FROM booking_seat_mapping WHERE booking_id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, bookingId);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int seatId = rs.getInt("seat_Id");
				seats.add(seatId);
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return seats;
	}

	@Override
	public void addBooking(Booking booking) {
		try {
			String sqlQuery = "INSERT INTO BOOKING (show_Id, booking_Date, customer_Id) VALUES (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = dateFormat.format(booking.getBookingDate());
	        Timestamp timeStamp = Timestamp.valueOf(formattedTimestamp);
	        
			ps.setInt(1, booking.getShowId());
			ps.setTimestamp(2, timeStamp);
			ps.setInt(3, booking.getCustomerId());
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
			    int bookingId = generatedKeys.getInt(1);
			    addBookedSeats(bookingId, booking.getBookedSeats());
			}
			System.out.println("Booking by customer added");			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private void addBookedSeats(int bookingId, List<Integer> seats) {		
		String sqlQuery = 	"INSERT INTO booking_seat_mapping "
				+ 			"(booking_id, seat_Id) "
				+ 			"VALUES (?,?)";
		
		try {

			int theatreAdded = 0;
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, bookingId);
			for (int seatId: seats) {
				ps.setInt(2,  seatId);
				theatreAdded = ps.executeUpdate();				
			}
			if(theatreAdded > 0) {
				System.out.println("Booked Seats added to database");
			} else {
				System.out.println("Error adding mapping with " + bookingId);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBooking(Booking booking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBooking(int id) {
		try {
			String sqlQuery = "DELETE FROM BOOKING WHERE BOOKING_ID = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			int rowDeleted = ps.executeUpdate();
			
			if(rowDeleted > 0) {
				System.out.println("Booking deleted");
			}
			else {
				System.out.println("Error deleting booking with Id = " + id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
