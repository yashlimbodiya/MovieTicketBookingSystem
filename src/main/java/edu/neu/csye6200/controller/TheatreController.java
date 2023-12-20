package edu.neu.csye6200.controller;

import java.util.List;

import edu.neu.csye6200.dao.*;
import edu.neu.csye6200.model.Movie;
import edu.neu.csye6200.model.Theatre;
import java.util.Set;

public class TheatreController {
	private TheatreDao theatreDao = new TheatreDaoImpl();
	private MovieDao movieDao = new MovieDaoImpl();
	private ShowDao showDao = new ShowDaoImpl();
	
	public int getTheatreByName(String theatreName) {
		return theatreDao.getTheatreByName(theatreName);
	}
    
    public void addTheatre(Theatre theatre) {
    	theatreDao.addTheatre(theatre);
    }
    
    public void deleteTheatre(int id) {
    	theatreDao.deleteTheatre(id);
    }
    
    public List<String> getTheatresForMovie(String movieName) {
    	Movie movie = movieDao.getMovieByMovieName(movieName);       
    	List<Integer> screens = showDao.getScreensByMovieId(movie.getId());
    	List<String> theatres = theatreDao.getTheatresByScreen(screens);
    	return theatres;
    }
}
