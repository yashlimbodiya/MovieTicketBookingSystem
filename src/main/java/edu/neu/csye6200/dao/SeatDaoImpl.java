package edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.neu.csye6200.model.Seat;
import edu.neu.csye6200.repository.DatabaseConnection;

public class SeatDaoImpl implements SeatDao {
	static Connection connection = DatabaseConnection.getDbInstance();
	@Override
	public List<Seat> getAllSeats() {
		List<Seat> allSeats = new ArrayList<Seat>();
		Statement statement;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			String sqlQuery = "select * from seat";
			rs = statement.executeQuery(sqlQuery);
			
			while(rs.next()) {
				int seatId = rs.getInt("seat_Id");
				int seatNumber = rs.getInt("seat_Number");
				String seatRow = rs.getString("seat_Row");
				String seatClass = rs.getString("seat_Class");
				int screenId = rs.getInt("screen_Number");		
				allSeats.add(new Seat(seatId, seatNumber, seatRow, seatClass, screenId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return allSeats;
	}
	
	@Override
	public List<Seat> getAllSeatsByScreen(int screenId) {
		List<Seat> response = new ArrayList<Seat>();
		ResultSet rs = null;
		try {
			String sqlQuery = "select * from seat where screen_Number = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,screenId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int seatId = rs.getInt("seat_Id");
				int seatNumber = rs.getInt("seat_Number");
				String seatRow = rs.getString("seat_Row");
				String seatClass = rs.getString("seat_Class");
				int screen_Id = rs.getInt("screen_Number");		
				response.add(new Seat(seatId, seatNumber, seatRow, seatClass, screen_Id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return response;


	}
	
	@Override
	public int addSeat(Seat seat) {
		try {
			String sqlQuery = "INSERT INTO seat (seat_Number, seat_Row, seat_Class, screen_Number) VALUES (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, seat.getSeatNumber());
			ps.setString(2, seat.getSeatRow());
			ps.setString(3, seat.getSeatClass());
			ps.setInt(4, seat.getScreenId());
			ps.executeUpdate();
			System.out.println("Seat Added Successfully = " + "\n");
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
			    return generatedKeys.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void deleteSeat(int seatNumber, String seatRow) {
		try {
			String sqlQuery = "DELETE FROM SEAT WHERE SEAT_NUMBER = ? AND SEAT_ROW = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, seatNumber);
			ps.setString(2, seatRow);
			
			int rowDeleted = ps.executeUpdate();
			
			if(rowDeleted > 0) {
				System.out.println("Seat deleted");
			}
			else {
				System.out.println("Error deleting seat " + seatNumber + " " + seatRow);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static class SeatAvailabilityDaoImpl implements SeatAvailabilityDao {

		@Override
		public Map<Seat, Boolean> getSeatAvailabilityByScreen(int screenId) {
			Map<Seat, Boolean> response = new HashMap<Seat, Boolean>();
			ResultSet rs = null;
			try {
				String sqlQuery = "select * from seat where screen_Number = ?";
				PreparedStatement ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1,screenId);
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					int seatId = rs.getInt("seat_Id");
					int seatNumber = rs.getInt("seat_Number");
					String seatRow = rs.getString("seat_Row");
					String seatClass = rs.getString("seat_Class");
					int screen_Id = rs.getInt("screen_Number");		
					response.put(new Seat(seatId, seatNumber, seatRow, seatClass, screen_Id), true);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			return response;
		}


		@Override
		public void addSeatAvailability(int seatId, List<Integer> shows) {
			try {
				String sqlQuery = "INSERT INTO seat_availability (seat_Id, availability, show_Id) VALUES (?,true,?)";
				PreparedStatement ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1, seatId);
				
				for(int showId: shows) {
					ps.setInt(2, showId);
					ps.executeUpdate();
				}	
				
				System.out.println("Seat Availability Added Successfully = " + "\n");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		@Override
		public List<Integer> getAvailableSeatsByShow(int showId) {
			List<Integer> response = new ArrayList<Integer>();
			ResultSet rs = null;
			try {
				String sqlQuery = "select seat_Id from seat_availability where show_Id = ? and availability = true";
				PreparedStatement ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1, showId);
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					int seatId = rs.getInt("seat_Id");	
					response.add(seatId);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			return response;
		}

		@Override
		public int getAvailableSeatCount(int showId) {
			ResultSet rs = null;
			try {
				String sqlQuery = "select count(*) from seat_availability where show_Id = ? and availability = true";
				PreparedStatement ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1,showId);
				rs = ps.executeQuery();			
				while(rs.next()) {
					
					int seatId = rs.getInt(1);
					return seatId;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			return 0;

		}

		@Override
		public void updateSeatAvailability(List<Integer> seats, int showId) {
			try {
				String sqlQuery = "UPDATE seat_availability " + 
			                      " SET availability = false" + 
						          " WHERE show_Id = ? and seat_Id = ?";

				PreparedStatement ps = connection.prepareStatement(sqlQuery);
				ps.setInt(1, showId);
				
				for(int seatId : seats) {
					ps.setInt(2, seatId);
					ps.executeUpdate();
				}
				System.out.println("Seat Availability Updated");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
