package com.seveneleven.mycontactsapp.contacts.tag;

import java.util.*;

public class TagService {
    private final Map<String, Set<Tag>> userTags = new HashMap<>();

    public Set<Tag> getAllTags(String userEmail) {
        userTags.putIfAbsent(userEmail, new HashSet<>());
        return new HashSet<>(userTags.get(userEmail));
    }

    public Tag createOrGet(String userEmail, String name) {
        if (name == null) return null;
        String n = name.trim();
        if (n.isEmpty()) return null;
        userTags.putIfAbsent(userEmail, new HashSet<>());
        Set<Tag> set = userTags.get(userEmail);
        Tag t = new Tag(n);
        if (set.contains(t)) {
            for (Tag existing : set) {
                if (existing.equals(t)) return existing;
            }
        }
        set.add(t);
        return t;
    }

    public boolean deleteTag(String userEmail, String name) {
        if (name == null) return false;
        String n = name.trim();
        if (n.isEmpty()) return false;
        userTags.putIfAbsent(userEmail, new HashSet<>());
        return userTags.get(userEmail).remove(new Tag(n));
    }
}