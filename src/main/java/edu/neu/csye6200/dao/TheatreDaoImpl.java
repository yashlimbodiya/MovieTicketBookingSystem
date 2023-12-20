package edu.neu.csye6200.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.neu.csye6200.model.Theatre;
import edu.neu.csye6200.repository.DatabaseConnection;
import java.util.Collections;

public class TheatreDaoImpl implements TheatreDao {
	Connection connection = DatabaseConnection.getDbInstance();
	
	@Override
	public int getTheatreByName(String theatreName) {
		ResultSet rs = null;
		try {
			String sqlQuery = "select theatre_id from theatre where theatre_name = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setString(1,theatreName);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int movieId = rs.getInt("theatre_id");
				return movieId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return 0;
	}
	
	public List<Integer> getScreensOfTheatre(int theatre_id) {
		List<Integer> screens = new ArrayList<Integer>();
		ResultSet rs = null;
		try {
			
			String sqlQuery = "SELECT screen_Number FROM theatre_screen_mapping WHERE theatre_id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, theatre_id);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int screenId = rs.getInt("screen_Number");
				screens.add(screenId);
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return screens;
	}
	
	@Override
	public List<String> getTheatresByScreen(List<Integer> screens) {
		List<String> theatres = new ArrayList<String>();
		ResultSet rs = null;
		try {
			
			String sqlQuery = "SELECT DISTINCT theatre_id FROM theatre_screen_mapping WHERE screen_Number = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			
			for (int screenId: screens) {
				ps.setInt(1, screenId);
				rs = ps.executeQuery();
				while (rs.next()) {
					int theatreId = rs.getInt("theatre_id");
					theatres.add(getTheatreById(theatreId).getName());
				}
			}	
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		Set<String> theatreName = new HashSet<>(theatres);
                theatres = new ArrayList<>(theatreName);
                Collections.sort(theatres);
		return theatres;
	}


	private Theatre getTheatreById(int id) {
		ResultSet rs = null;
		
		try {
			String sqlQuery = "select * from theatre where theatre_id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int theatreId = rs.getInt("theatre_Id");
				String theatreName = rs.getString("theatre_name");
				List<Integer> screens = getScreensOfTheatre(theatreId);
				return new Theatre(theatreId, theatreName, screens);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public void addTheatre(Theatre theatre) {
		
		String sqlQuery = 	"INSERT INTO THEATRE "
				+ 			"(theatre_name) "
				+ 			"VALUES (?)";
		
		try {			
				PreparedStatement ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, theatre.getName());
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
				    int theatreId = generatedKeys.getInt(1);
				    addScreensToTheatre(theatreId, theatre.getScreens());
				}
				System.out.println("Theatre added to database" + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addScreensToTheatre(int theatreId, List<Integer> screens) {		
		String sqlQuery = 	"INSERT INTO theatre_screen_mapping "
				+ 			"(theatre_id, screen_Number) "
				+ 			"VALUES (?,?)";
		
		try {
			int theatreAdded = 0;
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, theatreId);
			for (int screenId: screens) {
				ps.setInt(2,  screenId);
				theatreAdded = ps.executeUpdate();				
			}
			if(theatreAdded > 0) {
				System.out.println("Mapping added to database");
			} else {
				System.out.println("Error adding theatre to screen mapping " + theatreId);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTheatre(int id) {
		try {
			String sqlQuery = "DELETE FROM theatre WHERE theatre_Id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			int rowDeleted = ps.executeUpdate();
			
			if(rowDeleted > 0) {
				System.out.println("Theatre deleted");
			}
			else {
				System.out.println("Error deleting theatre with Id = " + id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
