package edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.model.Screen;
import edu.neu.csye6200.repository.DatabaseConnection;

public class ScreenDaoImpl implements ScreenDao {
	Connection connection = DatabaseConnection.getDbInstance();
    ShowDao showDao = new ShowDaoImpl();
    
	@Override
	public List<Screen> getAllScreens() {
		List<Screen> allScreens = new ArrayList<Screen>();
		Statement statement;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			String sqlQuery = "select * from screen";
			rs = statement.executeQuery(sqlQuery);
			
			while(rs.next()) {
				int screenId = rs.getInt("screen_Number");
				int seating_capacity = rs.getInt("seating_Capacity");	
				int theatreId = getTheatreByScreen(screenId);
				List<Integer> shows = showDao.getAllShowsByScreenId(screenId);
				allScreens.add(new Screen(screenId, seating_capacity, theatreId, shows));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return allScreens;
	}

	@Override
	public Screen getScreenById(int id) {
		ResultSet rs = null;
		try {
			String sqlQuery = "select * from screen where screen_Number = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int screenId = rs.getInt("screen_Number");
				int seatingCap = rs.getInt("seating_Capacity");	
				int theatreId = getTheatreByScreen(screenId);
				List<Integer> shows = showDao.getAllShowsByScreenId(screenId);
				return new Screen(screenId, seatingCap, theatreId, shows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;

	}
	
	private int getTheatreByScreen(int screenId) {
		String sqlQuery = 	"SELECT theatre_id FROM theatre_screen_mapping "
				+ "WHERE screen_Number = ?";
		ResultSet rs = null;
		try {
			
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, screenId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int theatreId = rs.getInt("theatre_id");
				return theatreId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void addScreen(Screen screen) {
		String sqlQuery = 	"INSERT INTO SCREEN "
				+ 			"(seating_Capacity) "
				+ 			"VALUES (?)";
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, screen.getSeatingCapacity());
			int screenAdded = ps.executeUpdate();
			System.out.println("execute boolean output = " + screenAdded);
			if(screenAdded > 0) {
				System.out.println("Screen added to database" + "\n");
			} else {
				System.out.println("Error adding screen with " + screen.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateScreen(Screen screen) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteScreen(int id) {
		try {
			String sqlQuery = "DELETE FROM screen WHERE screen_Number = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			int rowDeleted = ps.executeUpdate();
			
			if(rowDeleted > 0) {
				System.out.println("Screen deleted");
			}
			else {
				System.out.println("Error deleting screen with Id = " + id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
