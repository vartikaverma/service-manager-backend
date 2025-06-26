package com.vverma.webapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "service") // This annotation indicates that this class is a MongoDB document and specifies the collection name.
public class Service {
    
    @Id // This annotation indicates that this field is the primary key for the document.
    private String id;

    private List<Resource> resources;

    // Getters for Id.
    public String getId() { return id; }

    // setter for Id.
    public void setId(String id) { this.id = id; }

    // Getter for resources.
    public List<Resource> getResources() { return resources; }

    // Setter for resources.
    public void setResources(List<Resource> resources) { this.resources = resources; }
}
