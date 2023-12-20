 package edu.neu.csye6200.model;

public class Movie {
    private int id;
    private String title;
    private String description;
    private String genre;
    private int duration; // in minutes
  
	public Movie(int id, String title, String description, String genre, int duration) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.duration = duration;
	}
	
	public Movie(String title, String description, String genre, int duration) {
		super();
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.duration = duration;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "id= " + id + ", title= " + title + ", description= " + description + "\n";
	}   
}
