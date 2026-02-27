package com.seveneleven.mycontactsapp.contacts;

import java.time.LocalDateTime;
import java.util.UUID;

public class Organization extends Contacts {
	
	private String ID= UUID.randomUUID().toString();
	private LocalDateTime createdTime=LocalDateTime.now();
	private String location;

	public String getID() {
		return ID;
	}
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public Organization(String name,String location){
		super(name);
		this.location=location;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	

}
