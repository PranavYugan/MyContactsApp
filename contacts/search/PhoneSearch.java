package com.seveneleven.mycontactsapp.contacts.search;

import java.util.ArrayList;
import java.util.List;
import com.seveneleven.mycontactsapp.contacts.Contacts;

public class PhoneSearch implements SearchStrategy {
    public List<Contacts> search(List<Contacts> contacts, String query) {
        List<Contacts> result = new ArrayList<>();
        if (query == null) return result;
        String q = query.trim();
        for (Contacts c : contacts) {
            for (String p : c.getPhoneNumbers()) {
                if (p != null) {
                    if (p.equals(q) || p.contains(q)) {
                        result.add(c);
                        break;
                    }
                }
            }
        }
        return result;
    }
}