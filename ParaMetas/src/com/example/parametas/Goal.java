package com.example.parametas;

public class Goal {
	private String name;
	private String description;
	private String startDate;
	private String goalDate;
	private int goalNum;
	private int goalState;
	private String unit;
	
	public Goal(){}
	
	public Goal(String name, String description, String startDate, 
		String goalDate, int goalNum, int goalState, String unit){
		
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.goalDate = goalDate;
		this.goalNum = goalNum;
		this.goalState = goalState;
		this.unit = unit;
	}
	
	public int getGoalNum() {
		return goalNum;
	}

	public void setGoalNum(int goalNum) {
		this.goalNum = goalNum;
	}

	public int getGoalState() {
		return goalState;
	}

	public void setGoalState(int goalState) {
		this.goalState = goalState;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getGoalDate() {
		return goalDate;
	}

	public void setGoalDate(String goalDate) {
		this.goalDate = goalDate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
