package com.vverma.webapp.controller;

import com.vverma.webapp.model.Dummy;
import org.springframework.web.bind.annotation.*;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@RestController
@RequestMapping("/dummy")
@CrossOrigin(origins = "http://localhost:4200")
// This is a REST controller for managing Dummy objects in the web application.
public class DummyController {

    // Using Caffeine cache for Dummy objects
    private final Cache<String, Dummy> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .build();

    @PostMapping
    public Dummy createDummy(@RequestBody Dummy dummy) {
        cache.put(dummy.getId(), dummy);
        return dummy;
    }

    @PutMapping("/{id}")
    public Dummy updateDummy(@PathVariable String id, @RequestBody Dummy dummy) {
        dummy.setId(id);
        cache.put(id, dummy);
        return dummy;
    }

    @GetMapping("/{id}")
    public Dummy getDummy(@PathVariable String id) {
        return cache.getIfPresent(id);
    }
}
