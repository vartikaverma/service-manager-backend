package com.vverma.webapp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// this is controller class, which will handle web requests from browser or frontend
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WebappController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hi I am Vartika and this is my Swisscomm interview project :)";
    }
    
}
