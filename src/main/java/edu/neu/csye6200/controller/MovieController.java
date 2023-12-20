package edu.neu.csye6200.controller;


import java.util.List;

import edu.neu.csye6200.dao.MovieDao;
import edu.neu.csye6200.dao.MovieDaoImpl;
import edu.neu.csye6200.model.Movie;

public class MovieController {
    private MovieDao movieDao = new MovieDaoImpl();

    public List<Movie> getAllMovies() {
        List<Movie> movies = movieDao.getAllMovies();
        return movies;
    }

    public Movie getMovieByName(String name) {
        Movie movie = movieDao.getMovieByMovieName(name);
        return movie;
    }

    public void addMovie(Movie movie) {
        movieDao.addMovie(movie);
    }

    public void updateMovie(Movie movie) {
        movieDao.updateMovie(movie);
    }

    public void deleteMovie(int id) {
        movieDao.deleteMovie(id);
    }
}

