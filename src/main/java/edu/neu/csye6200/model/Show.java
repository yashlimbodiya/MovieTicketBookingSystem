package edu.neu.csye6200.model;

import java.util.Date;
import java.util.Map;

public class Show {
	private int showId;
	private Date showTime;
	private int movieId; 
	private int screenId;
	private Map<Integer, Boolean> seatAvailability;
	
	public Show(int showId, Date showTime, int movieId, int screenId) {
		super();
		this.showId = showId;
		this.showTime = showTime;
		this.movieId = movieId;
		this.screenId = screenId;
	}
	public Show(Date showTime, int movieId, int screenId) {
		super();
		this.showTime = showTime;
		this.movieId = movieId;
		this.screenId = screenId;
	}	
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public Date getShowTime() {
		return showTime;
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	public Map<Integer, Boolean> getSeatAvailability() {
		return seatAvailability;
	}
	public void setSeatAvailability(Map<Integer, Boolean> seatAvailability) {
		this.seatAvailability = seatAvailability;
	}
	@Override
	public String toString() {
		return "Show Id = " + showId + ", Show Time= " + showTime + ", Movie ID = " 
	               + movieId + ", Screen Id = " + screenId + "\n";
	} 
}
