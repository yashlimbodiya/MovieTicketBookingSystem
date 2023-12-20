package edu.neu.csye6200.dao;

import java.util.List;
import java.util.Map;

import edu.neu.csye6200.model.Seat;

public interface SeatDao {
    List<Seat> getAllSeats();

    List<Seat> getAllSeatsByScreen(int screenId);
    
    int addSeat(Seat seat);
    
    void deleteSeat(int seatNumber, String seatRow);
        
    public interface SeatAvailabilityDao {
    	Map<Seat, Boolean> getSeatAvailabilityByScreen(int screenId);

    	void addSeatAvailability(int seatId, List<Integer> shows);
        
    	List<Integer> getAvailableSeatsByShow(int showId);
        
        int getAvailableSeatCount(int showId);
        
        void updateSeatAvailability(List<Integer> seats, int showId);

    	
    }

}
