package edu.neu.csye6200.model;

public class Review {
    private int id;
    private int movie_id;
    private String comment;
    
    
	public Review(int id, int movie_id, String comment) {
		super();
		this.id = id;
		this.movie_id = movie_id;
		this.comment = comment;
	}
	
	public Review( int movie_id, String comment) {
		super();
		this.movie_id = movie_id;
		this.comment = comment;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovieID() {
		return movie_id;
	}
	public void setMovieID(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Review [id= " + id + ", movie_id= " + movie_id + ", comment= " + comment + "]";
	}   
    
}

