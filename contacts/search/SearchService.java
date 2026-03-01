package com.seveneleven.mycontactsapp.contacts.search;

import java.util.List;
import com.seveneleven.mycontactsapp.contacts.Contacts;

public class SearchService {
    private final SearchStrategy nameSearch;
    private final SearchStrategy phoneSearch;
    private final SearchStrategy emailSearch;
    private final SearchStrategy tagSearch;

    public SearchService() {
        this.nameSearch = new NameSearch();
        this.phoneSearch = new PhoneSearch();
        this.emailSearch = new EmailSearch();
        this.tagSearch = new TagSearch();
    }

    public List<Contacts> searchByName(List<Contacts> contacts, String query) {
        return nameSearch.search(contacts, query);
    }

    public List<Contacts> searchByPhone(List<Contacts> contacts, String query) {
        return phoneSearch.search(contacts, query);
    }

    public List<Contacts> searchByEmail(List<Contacts> contacts, String query) {
        return emailSearch.search(contacts, query);
    }

    public List<Contacts> searchByTag(List<Contacts> contacts, String query) {
        return tagSearch.search(contacts, query);
    }
}