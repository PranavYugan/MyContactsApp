package com.seveneleven.mycontactsapp.contacts;

import java.time.LocalDateTime;
import java.util.UUID;

public class Person extends Contacts {
	
	protected String ID= UUID.randomUUID().toString();
	protected LocalDateTime createdTime=LocalDateTime.now();
	
	public String getID() {
		return ID;
	}
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	private String relationship;
	public Person(String name,String relationship){
		super(name);
		this.relationship=relationship;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	
}
