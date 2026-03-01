package com.seveneleven.mycontactsapp.contacts.tag;

import java.util.Objects;

public class Tag {
    private final String name;

    public Tag(String name) {
        this.name = name == null ? "" : name.trim();
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag other = (Tag) o;
        return this.name.equalsIgnoreCase(other.name);
    }

    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    public String toString() {
        return name;
    }
}