package com.vverma.webapp.repository;

import com.vverma.webapp.model.Service;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//A ServiceRepository for MongoDB access
// This interface extends MongoRepository to provide CRUD operations for Service entities.
public interface ServiceRepository extends MongoRepository<Service, String> {

    // Method to find services by resource ID
    List<Service> findByResources_Id(String resourceId);

    // Method to find services by owner ID
    List<Service> findByResources_Owners_Id(String ownerId);
}
