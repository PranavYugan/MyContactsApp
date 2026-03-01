package com.seveneleven.mycontactsapp.contacts;

import java.util.*;
import java.time.LocalDateTime;
import java.util.UUID;
import com.seveneleven.mycontactsapp.contacts.tag.Tag;

public abstract class Contacts {
    protected String ID;
    protected LocalDateTime createdTime;
    protected String name;
    protected List<String> phoneNumbers;
    protected List<String> emails;
    private Set<Tag> tagSet;
    private int contactCount = 0;

    public Contacts(String name) {
        this.ID = UUID.randomUUID().toString();
        this.createdTime = LocalDateTime.now();
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.tagSet = new HashSet<>();
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

    public int getContactCount() {
        return contactCount;
    }

    public void incrementContactCount() {
        this.contactCount++;
    }

    public Set<Tag> getTagObjects() {
        return Collections.unmodifiableSet(tagSet);
    }

    public void addTagObject(Tag tag) {
        if (tag != null && tag.getName() != null && !tag.getName().isEmpty()) tagSet.add(tag);
    }

    public void addTagObjects(Collection<Tag> tags) {
        if (tags != null) for (Tag t : tags) addTagObject(t);
    }

    public boolean removeTagObject(Tag tag) {
        return tagSet.remove(tag);
    }

    public Set<String> getTags() {
        Set<String> names = new HashSet<>();
        for (Tag t : tagSet) names.add(t.getName());
        return Collections.unmodifiableSet(names);
    }

    public void addTag(String tag) {
        if (tag != null) {
            String t = tag.trim();
            if (!t.isEmpty()) tagSet.add(new com.seveneleven.mycontactsapp.contacts.tag.Tag(t));
        }
    }

    public void addTags(Collection<String> t) {
        if (t != null) for (String s : t) addTag(s);
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

        if (!tagSet.isEmpty()) {
            List<String> ts = new ArrayList<>();
            for (Tag t : tagSet) ts.add(t.getName());
            System.out.println("Tags: " + String.join(", ", ts));
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}