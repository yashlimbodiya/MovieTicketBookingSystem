package edu.neu.csye6200.dao;

import java.util.List;

import edu.neu.csye6200.model.Screen;

public interface ScreenDao {
    List<Screen> getAllScreens();

    Screen getScreenById(int id);

    void addScreen(Screen screen);
    
    void updateScreen(Screen screen);
    
    void deleteScreen(int id);
}
