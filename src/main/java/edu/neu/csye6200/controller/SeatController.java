package edu.neu.csye6200.controller;

import java.util.List;

import edu.neu.csye6200.dao.SeatDao;
import edu.neu.csye6200.dao.SeatDao.SeatAvailabilityDao;
import edu.neu.csye6200.dao.SeatDaoImpl;
import edu.neu.csye6200.dao.SeatDaoImpl.SeatAvailabilityDaoImpl;
import edu.neu.csye6200.dao.ShowDao;
import edu.neu.csye6200.dao.ShowDaoImpl;
import edu.neu.csye6200.model.Seat;

public class SeatController {
	private SeatDao seatDao = new SeatDaoImpl();
	private SeatAvailabilityDao seatAvailabilityDao = new SeatAvailabilityDaoImpl();
	private ShowDao showDao = new ShowDaoImpl();
	
	public List<Seat> getAllSeats(){
		return seatDao.getAllSeats();
	}
	
	public List<Seat> getAllSeatsByScreen(int screenId){
		return seatDao.getAllSeatsByScreen(screenId);
	}
	
	public List<Integer> getAvailableSeatsInShow(int showId){
		return seatAvailabilityDao.getAvailableSeatsByShow(showId);
	}
	
	public int getAvailableSeatCount(int showId) {
		return seatAvailabilityDao.getAvailableSeatCount(showId);
	}
    
    public void addSeat(Seat seat) {
    	int seatId = seatDao.addSeat(seat);
    	List<Integer> shows = showDao.getAllShowsByScreenId(seat.getScreenId());
    	seatAvailabilityDao.addSeatAvailability(seatId, shows);
    }
    /*
     * Add Screens
     * Add Shows
     * Add Seats  -> Seat Availability gets added
     */
    
    public void deleteSeat(int seatId, String seatRow) {
    	seatDao.deleteSeat(seatId, seatRow);
    }
}
