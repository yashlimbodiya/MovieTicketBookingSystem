package edu.neu.csye6200.model;

import java.util.List;

public class Theatre {
	private int id;
	private String name;
	List<Integer> screens;
	
	public Theatre(int theatre_id, String theatre_name, List<Integer> sr){
		id = theatre_id;
		name = theatre_name;
		screens = sr;
	}
	
	public Theatre(String theatre_name, List<Integer> sr){
		name = theatre_name;
		screens = sr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getScreens() {
		return screens;
	}
	public void setScreens(List<Integer> screens) {
		this.screens = screens;
	}
	@Override
	public String toString() {
		return "id= " + id + ", name= " + name + ", screens id=" + screens.toString() + "\n";
	}  

}
