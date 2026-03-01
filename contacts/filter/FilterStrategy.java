package com.seveneleven.mycontactsapp.contacts.filter;

import java.util.List;
import com.seveneleven.mycontactsapp.contacts.Contacts;

public interface FilterStrategy {
    List<Contacts> apply(List<Contacts> contacts, String input);
}