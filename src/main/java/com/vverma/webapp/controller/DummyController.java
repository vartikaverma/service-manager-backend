package com.vverma.webapp.controller;

import com.vverma.webapp.model.Dummy;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/dummy")
// This is a REST controller for managing Dummy objects in the web application.
public class DummyController {

    // using ConcurrentHashMap to simulate a cache for Dummy objects
    // ConcurrentHashMap is thread-safe and allows concurrent access that ensures that multiple threads can read and write to the cache without corrupting the data.
    // This is useful in a web application where multiple requests can be handled simultaneously.
    private final ConcurrentHashMap<String, Dummy> cache = new ConcurrentHashMap<>();

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
        return cache.get(id);
    }
}
