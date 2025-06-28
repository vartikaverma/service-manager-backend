package com.vverma.webapp.controller;

import com.vverma.webapp.model.Service;
import com.vverma.webapp.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = "http://localhost:4200")
// This is a REST controller for managing services in the web application.
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @PostMapping
    public Service createService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }

    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable String id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Service updateService(@PathVariable String id, @RequestBody Service updatedService) {
        updatedService.setId(id);
        return serviceRepository.save(updatedService);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable String id) {
        serviceRepository.deleteById(id);
    }

    @GetMapping("/by-resource/{resourceId}")
    public List<Service> getServicesByResourceId(@PathVariable String resourceId) {
        return serviceRepository.findByResources_Id(resourceId);
    }

    @GetMapping("/by-owner/{ownerId}")
    public List<Service> getServicesByOwnerId(@PathVariable String ownerId) {
        return serviceRepository.findByResources_Owners_Id(ownerId);
    }

    @DeleteMapping("/{serviceId}/resource/{resourceId}")
    public Service deleteResourceFromService(@PathVariable String serviceId, @PathVariable String resourceId) {
        Service service = serviceRepository.findById(serviceId).orElse(null);
        if (service != null && service.getResources() != null) {
            service.getResources().removeIf(resource -> resourceId.equals(resource.getId()));
            serviceRepository.save(service);
        }
        return service;
    }

    @DeleteMapping("/{serviceId}/resource/{resourceId}/owner/{ownerId}")
    public Service deleteOwnerFromService(@PathVariable String serviceId, @PathVariable String resourceId, @PathVariable String ownerId) {
        Service service = serviceRepository.findById(serviceId).orElse(null);
        if(service != null && service.getResources() != null) {
            service.getResources().forEach(resource -> {
                if (resourceId.equals(resource.getId())) {
                    resource.getOwners().removeIf(owner -> ownerId.equals(owner.getId()));
                }
            });
            serviceRepository.save(service);
        }
        return service;
    }
}
