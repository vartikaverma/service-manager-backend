package com.vverma.webapp.model;

import java.util.List;

public class Resource {


    private String id;
    private List<Owner> owners;

    // Getter for Id.
    public String getId() { return id; }

    // Setter for Id.
    public void setId(String id) { this.id = id; }

    // Getter for owners.
    public List<Owner> getOwners() { return owners; }

    // Setter for owners.
    public void setOwners(List<Owner> owners) { this.owners = owners; }
}
