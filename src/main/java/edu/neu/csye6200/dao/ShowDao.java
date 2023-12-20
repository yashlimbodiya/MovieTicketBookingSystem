package edu.neu.csye6200.dao;

import java.util.List;
import java.util.Map;

import edu.neu.csye6200.model.Show;

public interface ShowDao {
    List<Show> getAllShowsByMovieId(int movieId);
    
    List<Integer> getScreensByMovieId(int movieId);

    Show getShowById(int id);
    
    Map<Integer, String> getShowsByScreens(List<Integer> screens, int movieId);

    void addShow(Show show);
    
    void deleteShow(int id);

	List<Integer> getAllShowsByScreenId(int screenId);
}
