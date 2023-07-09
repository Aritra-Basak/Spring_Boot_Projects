package com.wbhealth.openAI.model;

//@JsonInclude(value = Include.NON_NULL)
public class UserInput {

	String userCommand;


	public String getUserCommand() {
		return userCommand;
	}

	public void setUserCommand(String userCommand) {
		this.userCommand = userCommand;
	}

}