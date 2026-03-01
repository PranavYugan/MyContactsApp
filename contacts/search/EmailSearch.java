package com.seveneleven.mycontactsapp.contacts.search;

import java.util.ArrayList;
import java.util.List;
import com.seveneleven.mycontactsapp.contacts.Contacts;

public class EmailSearch implements SearchStrategy {
    public List<Contacts> search(List<Contacts> contacts, String query) {
        List<Contacts> result = new ArrayList<>();
        if (query == null) return result;
        String q = query.trim().toLowerCase();
        for (Contacts c : contacts) {
            for (String e : c.getEmails()) {
                if (e != null) {
                    String em = e.toLowerCase();
                    if (em.equals(q) || em.contains(q)) {
                        result.add(c);
                        break;
                    }
                }
            }
        }
        return result;
    }
}