package com.seveneleven.mycontactsapp.contacts;
import java.util.*;
import java.time.LocalDateTime;

public abstract class Contacts {
	
	protected String ID;
	protected LocalDateTime createdTime;
    protected String name;
    protected List<String> phoneNumbers;
    protected List<String> emails;

    public Contacts(String name) {
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        this.emails = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addPhoneNumber(String phone) {
        phoneNumbers.add(phone);
    }

    public void addEmail(String email) {
        emails.add(email);
    }
	
    
	
	
}
