package net.geekscore.core.entities;

import net.geekscore.core.BaseEntity;

public class Employer extends BaseEntity {

    private String name;

    private String displayName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
