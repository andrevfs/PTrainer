package com.example.parametas;

public class Setting {
	private boolean option;
	private String optionText;
	private String description;	
	
	
	public Setting(){}	
	
	public Setting(boolean option, String optionText, String description){
		this.option = option;
		this.description = description;
		this.optionText = optionText;
	}
	
	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public boolean getOption() {
		return option;
	}

	public void setOption(boolean option) {
		this.option = option;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
