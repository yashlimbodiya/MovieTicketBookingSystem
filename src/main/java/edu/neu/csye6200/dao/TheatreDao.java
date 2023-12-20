package edu.neu.csye6200.dao;

import java.util.List;

import edu.neu.csye6200.model.Theatre;

public interface TheatreDao {

    int getTheatreByName(String theatreName);
    
    List<String> getTheatresByScreen(List<Integer> screens);
    
    List<Integer> getScreensOfTheatre(int theatre_id);

    void addTheatre(Theatre theatre);
    
    void deleteTheatre(int id);
}
