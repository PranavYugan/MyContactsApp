package com.seveneleven.mycontactsapp.contacts;

import java.util.*;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Contacts {
    protected String ID;
    protected LocalDateTime createdTime;
    protected String name;
    protected List<String> phoneNumbers;
    protected List<String> emails;
    private Set<String> tags;

    public Contacts(String name) {
        this.ID = UUID.randomUUID().toString();
        this.createdTime = LocalDateTime.now();
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.tags = new HashSet<>();
    }

    public String getID() {
        return ID;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
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

    public List<String> getPhoneNumbers() {
        return Collections.unmodifiableList(phoneNumbers);
    }

    public List<String> getEmails() {
        return Collections.unmodifiableList(emails);
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void addTag(String tag) {
        if (tag != null) {
            String t = tag.trim();
            if (!t.isEmpty()) {
                tags.add(t);
            }
        }
    }

    public void addTags(Collection<String> t) {
        if (t != null) {
            for (String s : t) {
                addTag(s);
            }
        }
    }

    public void displayCommon() {
        System.out.println("Name: " + name);
        System.out.println("The list of Phone Numbers:");
        for (String p : phoneNumbers) {
            System.out.println("- " + p);
        }
        System.out.println("The list of Emails:");
        for (String e : emails) {
            System.out.println("- " + e);
        }
        if (!tags.isEmpty()) {
            System.out.println("Tags: " + String.join(", ", tags));
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}