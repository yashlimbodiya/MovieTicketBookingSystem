package edu.neu.csye6200.controller;

import java.util.List;

import edu.neu.csye6200.dao.ScreenDao;
import edu.neu.csye6200.dao.ScreenDaoImpl;
import edu.neu.csye6200.dao.TheatreDao;
import edu.neu.csye6200.dao.TheatreDaoImpl;
import edu.neu.csye6200.model.Screen;

public class ScreenController {
	private ScreenDao screenDao = new ScreenDaoImpl();
	private TheatreDao theatreDao = new TheatreDaoImpl();
	
	public List<Screen> getAllScreens(){
		return screenDao.getAllScreens();
	}
	
	public List<Integer> getScreenOfTheatres(String theatreName) {
		List<Integer> screens = theatreDao.getScreensOfTheatre(theatreDao.getTheatreByName(theatreName));
		return screens;
	}
	
    public Screen getScreeneById(int id) {
        return screenDao.getScreenById(id);
    }
    
    public void addScreen(Screen screen) {
    	screenDao.addScreen(screen);
    }
    
    public void updateScreen(Screen screen) {
    	screenDao.updateScreen(screen);
    }
    
    public void deleteTheatre(int id) {
    	screenDao.deleteScreen(id);
    }

}
