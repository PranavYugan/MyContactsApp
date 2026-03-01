package com.seveneleven.mycontactsapp.contacts.filter;

import java.util.ArrayList;
import java.util.List;
import com.seveneleven.mycontactsapp.contacts.Contacts;

public class TagFilter implements FilterStrategy {
    public List<Contacts> apply(List<Contacts> contacts, String input) {
        List<Contacts> result = new ArrayList<>();
        if (input == null) return result;
        String q = input.trim().toLowerCase();
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