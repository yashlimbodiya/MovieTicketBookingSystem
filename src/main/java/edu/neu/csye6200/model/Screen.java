package edu.neu.csye6200.model;

import java.util.List;

public class Screen {
	private int screenNumber;
	private int seatingCapacity;
	private int theatreId;
	private List<Integer> shows;
	
	public Screen(int id, int capacity, int theatre_id, List<Integer> showIds) {
		super();
		this.screenNumber = id;
		this.seatingCapacity = capacity;
		this.theatreId = theatre_id;
		this.shows = showIds;
	}
	
	public Screen(int id, int capacity, List<Integer> showIds) {
		super();
		this.screenNumber = id;
		this.seatingCapacity = capacity;
		this.shows = showIds;
	}
	
	public int getScreenNumber() {
		return screenNumber;
	}
	public void setScreenNumber(int screenNumber) {
		this.screenNumber = screenNumber;
	}
	public List<Integer> getShows() {
		return shows;
	}
	public void setShows(List<Integer> shows) {
		this.shows = shows;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	@Override
	public String toString() {
		return "id= " + screenNumber + ", capacity= " + seatingCapacity + ", theatre id = " + theatreId + "\n";
	}  

}
