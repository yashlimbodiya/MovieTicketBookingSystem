package edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.neu.csye6200.model.Show;
import edu.neu.csye6200.repository.DatabaseConnection;

public class ShowDaoImpl implements ShowDao {
    Connection connection = DatabaseConnection.getDbInstance();
	
    @Override
	public List<Show> getAllShowsByMovieId(int movieId) {
		List<Show> allShows = new ArrayList<Show>();
		ResultSet rs = null;
		try {
			String sqlQuery = "select * from movie_show where movie_Id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, movieId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int showId = rs.getInt("show_Id");
				Date showTime = rs.getDate("show_Time");
				int movie_id = rs.getInt("movie_Id");
				int screenid = rs.getInt("screen_Number");		
				allShows.add(new Show(showId, showTime, movie_id, screenid));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return allShows;
	}
    
	@Override
	public List<Integer> getAllShowsByScreenId(int screenId) {
		List<Integer> allShows = new ArrayList<Integer>();
		ResultSet rs = null;
		try {
			String sqlQuery = "select show_Id from movie_show where screen_Number = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, screenId);
			rs = ps.executeQuery();
			
			while(rs.next()) {	
				allShows.add(rs.getInt("show_Id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return allShows;
	}

	@Override
	public Show getShowById(int id) {
		ResultSet rs = null;
		try {
			String sqlQuery = "select * from movie_show where show_Id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int showId = rs.getInt("show_Id");
				Date showTime = rs.getDate("show_Time");
				int movie_id = rs.getInt("movie_Id");
				int screenid = rs.getInt("screen_Number");		
				return new Show(showId, showTime, movie_id, screenid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public Map<Integer, String> getShowsByScreens(List<Integer> screens, int movieId){
		Map<Integer, String> shows = new HashMap<Integer, String>();
		ResultSet rs = null;
		try {
			
			String sqlQuery = "SELECT DISTINCT show_Id, show_Time FROM movie_show WHERE screen_Number = ? and movie_Id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(2, movieId);
			for (int screenId: screens) {
				ps.setInt(1, screenId);
				rs = ps.executeQuery();
				while (rs.next()) {
					int showId = rs.getInt("show_Id");
					String showTime = rs.getString("show_Time");
					shows.put(showId, showTime);
				}
			}	
		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return shows;


	}
	
	@Override
	public void addShow(Show show) {
		try {
			String sqlQuery = "INSERT INTO movie_show (show_Time,movie_Id,screen_Number) VALUES (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = dateFormat.format(show.getShowTime());
	        Timestamp timeStamp = Timestamp.valueOf(formattedTimestamp);
			
	        ps.setTimestamp(1, timeStamp);	
			ps.setInt(2, show.getMovieId());
			ps.setInt(3, show.getScreenId());
			int showAdded = ps.executeUpdate();

			System.out.println("execute boolean output = " + showAdded + "\n");
			
			if(showAdded > 0) {
				System.out.println("Show to the movie added");
			} else {
				System.out.println("Error adding show for movieId " + show.getMovieId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteShow(int id) {
		try {
			String sqlQuery = "DELETE FROM movie_show WHERE show_Id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			int rowDeleted = ps.executeUpdate();
			
			if(rowDeleted > 0) {
				System.out.println("Show deleted");
			}
			else {
				System.out.println("Error deleting show with Id = " + id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Integer> getScreensByMovieId(int movieId) {
		List<Integer> screens = new ArrayList<Integer>();
		ResultSet rs = null;
		try {
			String sqlQuery = "select distinct screen_Number from movie_show where movie_Id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,movieId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int showId = rs.getInt("screen_Number");
				screens.add(showId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return screens;
	}

}
