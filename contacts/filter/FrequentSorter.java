package com.seveneleven.mycontactsapp.contacts.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.seveneleven.mycontactsapp.contacts.Contacts;

public class FrequentSorter implements FilterStrategy {
    public List<Contacts> apply(List<Contacts> contacts, String input) {
        List<Contacts> copy = new ArrayList<>(contacts);
        Collections.sort(copy, new Comparator<Contacts>() {
            public int compare(Contacts a, Contacts b) {
                return Integer.compare(b.getContactCount(), a.getContactCount());
            }
        });
        return copy;
    }
}