package edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.model.Review;
import edu.neu.csye6200.repository.DatabaseConnection;

public class ReviewDaoImpl implements ReviewDao{

	Connection connection = DatabaseConnection.getDbInstance();

	@Override
	public List<Review> getAllReviews() {
		List<Review> allReviews = new ArrayList<Review>();
		Statement statement;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			String sqlQuery = "select * from review";
			rs = statement.executeQuery(sqlQuery);
			
			while(rs.next()) {
				int review_id = rs.getInt("review_id");
				int movie_id = rs.getInt("movie_id");
				String review_comment = rs.getString("review_comment");				
				allReviews.add(new Review(review_id, movie_id, review_comment));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return allReviews;
	}

	@Override
	public Review getReviewById(int review_id) {
		ResultSet rs = null;
		try {
			String sqlQuery = "select * from review where review_id = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,review_id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("review_id");
				int movie_id = rs.getInt("movie_id");
				String review_comment = rs.getString("review_comment");				
				return new Review(id, movie_id, review_comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public void addReview(Review review) {
		try {
			String sqlQuery = "INSERT INTO REVIEW (MOVIE_ID,REVIEW_COMMENT) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1, review.getMovieID());
			ps.setString(2, review.getComment());
			int reviewAdded = ps.executeUpdate();

			System.out.println("execute boolean output = " + reviewAdded + "\n");
			
			if(reviewAdded > 0) {
				System.out.println("Review to the movie added");
			}else {
				System.out.println("Error adding review for movieId " + review.getMovieID());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void updateReview(Review review) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReview(int id) {
		try {
			String sqlQuery = "DELETE FROM REVIEW WHERE REVIEW_ID = ?";
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setInt(1,id);
			
			int rowDeleted = ps.executeUpdate();
			
			if(rowDeleted > 0) {
				System.out.println("Review deleted");
			}
			else {
				System.out.println("Error deleting review with Id = " + id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
