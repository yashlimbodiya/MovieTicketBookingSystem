package edu.neu.csye6200.model;

public class Seat {
	private int seatId;
	private int seatNumber;
	private String seatRow;
	private String seatClass;
	private int screenId;
	
	public Seat(int seatId, int seatNumber, String seatRow, String seatClass, int screenId) {
		super();
		this.seatId = seatId;
		this.seatNumber = seatNumber;
		this.seatRow = seatRow;
		this.seatClass = seatClass;
		this.screenId = screenId;
	}
	public Seat(int seatNumber, String seatRow, String seatClass, int screenId) {
		super();
		this.seatNumber = seatNumber;
		this.seatRow = seatRow;
		this.seatClass = seatClass;
		this.screenId = screenId;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getSeatRow() {
		return seatRow;
	}
	public void setSeatRow(String seatRow) {
		this.seatRow = seatRow;
	}
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	@Override
	public String toString() {
		return "Seat Number= " + seatNumber + ", Seat Row= " + seatRow + ", Seat Class = " 
	               + seatClass + ", Screen Id = " + screenId + "\n";
	} 

}
