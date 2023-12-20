package edu.neu.csye6200.dao;

import edu.neu.csye6200.model.*;

import java.util.List;

public interface MovieDao {
    List<Movie> getAllMovies();

    Movie getMovieByMovieName(String movieName);

    void addMovie(Movie movie);
    
    void updateMovie(Movie movie);
    
    void deleteMovie(int id);
}
