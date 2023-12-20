package edu.neu.csye6200.controller;

import java.util.List;
import java.util.Map;

import edu.neu.csye6200.dao.ShowDao;
import edu.neu.csye6200.dao.ShowDaoImpl;
import edu.neu.csye6200.model.Show;

public class ShowController {
	ShowDao showDao = new ShowDaoImpl();
	ScreenController screenController = new ScreenController();
	public List<Show> getShowsOfMovie(int movieId){
		return showDao.getAllShowsByMovieId(movieId);
	}
	
    public Show getShowById(int id) {
        return showDao.getShowById(id);
    }
    
    public Map<Integer, String> getShowsOnScreen(String theatreName, int movieId) {
    	return showDao.getShowsByScreens(screenController.getScreenOfTheatres(theatreName), movieId);
    }
    
    public void addShow(Show show) {
    	showDao.addShow(show);
    }
    
    public void deleteShow(int id) {
    	showDao.deleteShow(id);
    }
}
