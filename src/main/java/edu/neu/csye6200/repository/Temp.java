package edu.neu.csye6200.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import edu.neu.csye6200.controller.BookingController;
import edu.neu.csye6200.controller.SeatController;
import edu.neu.csye6200.controller.ShowController;
import edu.neu.csye6200.dao.*;
import edu.neu.csye6200.model.*;

public class Temp {
	public static void main(String[] args) {
//		MovieDao movieDao = new MovieDaoImpl();
//		System.out.println(movieDao.getAllMovies().toString());
//		movieDao.deleteMovie(3);
//		ReviewDao reviewDao = new ReviewDaoImpl();
//		
//		//reviewDao.addReview(new Review(1, "Bollywood Hit Starrer Salman is an average movie"));
//		reviewDao.deleteReview(4);
//		System.out.println(reviewDao.getAllReviews().toString());
//		
		
//		TheatreDao theatreDao = new TheatreDaoImpl();
//		theatreDao.addTheatre(new Theatre("Pheonix", Arrays.asList(1,2)));
//		theatreDao.addTheatre(new Theatre("Cinepolis", Arrays.asList(3,4)));
//		theatreDao.addTheatre(new Theatre("INOX", Arrays.asList(5)));
//		System.out.println(theatreDao.getAllTheatres().toString());
//		theatreDao.deleteTheatre(2);
//		List<Integer> screens = new ArrayList<Integer>();
//		screens.add(1);
//		theatreDao.addScreensToTheatre(new Theatre(3, "Cinepolis", screens));
//		System.out.println(theatreDao.getAllTheatres().toString());
//		System.out.println(theatreDao.getTheatreById(1).toString());
//		System.out.println(theatreDao.getTheatreById(3).toString());
		
// 		ScreenDao screenDao = new ScreenDaoImpl();
// 		System.out.println(screenDao.getAllScreens().toString());
//		screenDao.addScreen(new Screen(101, 60));
//		screenDao.addScreen(new Screen(102, 70));
//		System.out.println(screenDao.getAllScreens().toString());
//	    screenDao.deleteScreen(2);
//		System.out.println(screenDao.getAllScreens().toString());
 		
// 		SeatDao seatDao = new SeatDaoImpl();
// 		seatDao.addSeat(new Seat(1,"A","Economy", 1));
// 		seatDao.addSeat(new Seat(2,"A","Economy", 1));
// 		seatDao.addSeat(new Seat(3,"A","Economy", 1));
// 		seatDao.addSeat(new Seat(1,"B","Premium", 1));
// 		System.out.println(seatDao.getAllSeats().toString());
// 		seatDao.deleteSeat(3, "A");
// 		System.out.println(seatDao.getAllSeats().toString());
// 		
// 		ShowDao showDao = new ShowDaoImpl();
 		
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, 2023);
//        calendar.set(Calendar.MONTH, Calendar.NOVEMBER); // Remember, January is 0!
//        calendar.set(Calendar.DAY_OF_MONTH, 30);
//        calendar.set(Calendar.HOUR_OF_DAY, 10); // HOUR_OF_DAY for 24-hour format
//        calendar.set(Calendar.MINUTE, 05);
// 		showDao.addShow(new Show(calendar.getTime(), 2, 1));
// 		
//        calendar.set(Calendar.HOUR_OF_DAY, 3); // HOUR_OF_DAY for 24-hour format
//        calendar.set(Calendar.MINUTE, 25);
// 		showDao.addShow(new Show(calendar.getTime(), 3, 1));
 		
// 		calendar.set(Calendar.HOUR_OF_DAY, 3); // HOUR_OF_DAY for 24-hour format
//        calendar.set(Calendar.MINUTE, 25);
// 		showDao.addShow(new Show(calendar.getTime(), 2, 3));
// 		System.out.println(showDao.getShowById(2).toString());
// 		System.out.println(showDao.getShowsByScreenId(1).toString());
 		
// 		BookingDao bookingDao = new BookingDaoImpl();
// 		
//       Calendar calendar = Calendar.getInstance();
//       calendar.set(Calendar.YEAR, 2023);
//       calendar.set(Calendar.MONTH, Calendar.NOVEMBER); // Remember, January is 0!
//       calendar.set(Calendar.DAY_OF_MONTH, 30);
//       calendar.set(Calendar.HOUR_OF_DAY, 10); // HOUR_OF_DAY for 24-hour format
//       calendar.set(Calendar.MINUTE, 05);
//       List<Integer> seats = new ArrayList<Integer>();
//       seats.add(1);
//       seats.add(2);
// 	   bookingDao.addBooking(new Booking(1, calendar.getTime(), seats, 1));
// 	   System.out.println(bookingDao.getAllBookingsByCustomerId(1).toString());
//       bookingDao.deleteBooking(3);
		
//		CustomerDao customerDao = new CustomerDaoImpl();
//		customerDao.updateCustomer(new Customer("ishi.gupta@gmail.com", "1234", "Ishita Gupta"));
//		customerDao.updateCustomer(new Customer(5, "ishi.gupta@gmail.com", "1234", "Ishita Gupta"));
//		customerDao.deletecustomer(5);
//		System.out.println(customerDao.getAllCustomers().toString());
		
//		SeatController seatController = new SeatController();
//		System.out.println(seatController.getAvailableSeatsInShow(1).toString());
//		seatController.addSeat(new Seat(1,"A", "Economy",5));
//		seatController.addSeat(new Seat(2,"A", "Economy",5));
//		seatController.addSeat(new Seat(3,"A", "Economy",5));
//		seatController.addSeat(new Seat(4,"A", "Economy",5));
//		seatController.addSeat(new Seat(5,"A", "Economy",5));
//		seatController.addSeat(new Seat(1,"B", "Premium",5));
//		seatController.addSeat(new Seat(2,"B", "Premium",5));
//		seatController.addSeat(new Seat(3,"B", "Premium",5));
//		seatController.addSeat(new Seat(4,"B", "Economy",5));
//		seatController.addSeat(new Seat(5,"B", "Economy",5));
//		System.out.println(seatController.getAvailableSeatCount(10));
		
//		ShowController showCont = new ShowController();
//		System.out.println(showCont.getShowsOfMovie(2).toString());
//		//String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		BookingController bookingContr = new BookingController();
//		List<Integer> seats = new ArrayList<Integer>();
//		seats.add(5);
//		seats.add(6);
//		bookingContr.addBooking(new Booking(1, Calendar.getInstance().getTime(), seats, 1));
		System.out.println(bookingContr.getAllCustomerBookings(1).toString());
	}
}

/*
 * Add Screens w/o theatres
 * Add Theatres
 * Add Screens To Theatres
 * Get Theatres / Screens
 * Add Shows
 * Add Seats  -> Seat Availability gets added
 * Add Customer
 * Add Bookings

 */
	
