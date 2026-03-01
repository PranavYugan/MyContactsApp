package com.seveneleven.mycontactsapp.contacts;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BulkOperationsService {

    public int bulkDelete(List<Contacts> contacts, Set<Integer> indices) {
        List<Integer> sorted = new ArrayList<>(indices);
        Collections.sort(sorted, Collections.reverseOrder());
        int count = 0;
        for (int index : sorted) {
            if (index >= 0 && index < contacts.size()) {
                contacts.remove(index);
                count++;
            }
        }
        return count;
    }

    public int bulkTag(List<Contacts> contacts, Set<Integer> indices, Set<String> tags) {
        int count = 0;
        for (int index : indices) {
            if (index >= 0 && index < contacts.size()) {
                Contacts c = contacts.get(index);
                c.addTags(tags);
                count++;
            }
        }
        return count;
    }

    public void bulkExport(List<Contacts> contacts, Set<Integer> indices, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
        writer.write("Index,Name,Phones,Emails,Tags");
        writer.newLine();
        for (int index : indices) {
            if (index >= 0 && index < contacts.size()) {
                Contacts c = contacts.get(index);
                String phones = String.join("|", c.getPhoneNumbers());
                String emails = String.join("|", c.getEmails());
                String tags = String.join("|", c.getTags());
                String line = index + "," + escape(c.getName()) + "," + escape(phones) + "," + escape(emails) + "," + escape(tags);
                writer.write(line);
                writer.newLine();
            }
        }
        writer.flush();
        writer.close();
    }

    private String escape(String s) {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\"") || s.contains("\n")) {
            String t = s.replace("\"", "\"\"");
            return "\"" + t + "\"";
        }
        return s;
    }
}