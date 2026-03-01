package com.seveneleven.mycontactsapp.contacts.search;

import java.util.ArrayList;
import java.util.List;
import com.seveneleven.mycontactsapp.contacts.Contacts;

public class TagSearch implements SearchStrategy {
    public List<Contacts> search(List<Contacts> contacts, String query) {
        List<Contacts> result = new ArrayList<>();
        if (query == null) return result;
        String q = query.trim().toLowerCase();
        for (Contacts c : contacts) {
            for (String t : c.getTags()) {
                if (t != null) {
                    String tag = t.toLowerCase();
                    if (tag.equals(q) || tag.contains(q)) {
                        result.add(c);
                        break;
                    }
                }
            }
        }
        return result;
    }
}