package com.seveneleven.mycontactsapp.contacts.search;

import java.util.List;
import com.seveneleven.mycontactsapp.contacts.Contacts;

public interface SearchStrategy {
    List<Contacts> search(List<Contacts> contacts, String query);
}