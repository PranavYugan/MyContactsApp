package com.seveneleven.mycontactsapp.contacts.search;

import java.util.ArrayList;
import java.util.List;
import com.seveneleven.mycontactsapp.contacts.Contacts;

public class NameSearch implements SearchStrategy {
    public List<Contacts> search(List<Contacts> contacts, String query) {
        List<Contacts> result = new ArrayList<>();
        if (query == null) return result;
        String q = query.trim().toLowerCase();
        for (Contacts c : contacts) {
            String n = c.getName();
            if (n != null) {
                String name = n.toLowerCase();
                if (name.equals(q) || name.contains(q)) {
                    result.add(c);
                }
            }
        }
        return result;
    }
}