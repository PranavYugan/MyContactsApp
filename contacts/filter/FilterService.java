package com.seveneleven.mycontactsapp.contacts.filter;

import java.util.List;
import com.seveneleven.mycontactsapp.contacts.Contacts;

public class FilterService {
    private final FilterStrategy tagFilter;
    private final FilterStrategy dateSorter;
    private final FilterStrategy frequentSorter;

    public FilterService() {
        this.tagFilter = new TagFilter();
        this.dateSorter = new DateAddedSorter();
        this.frequentSorter = new FrequentSorter();
    }

    public List<Contacts> byTag(List<Contacts> contacts, String tagQuery) {
        return tagFilter.apply(contacts, tagQuery);
    }

    public List<Contacts> byDateAdded(List<Contacts> contacts, String order) {
        return dateSorter.apply(contacts, order);
    }

    public List<Contacts> byFrequentlyContacted(List<Contacts> contacts) {
        return frequentSorter.apply(contacts, null);
    }
}