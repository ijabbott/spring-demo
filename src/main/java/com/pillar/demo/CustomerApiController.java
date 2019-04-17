package com.pillar.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class CustomerApiController {

    @RequestMapping(path = "/health", method = {RequestMethod.GET})
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Healthy", HttpStatus.OK);
    }
}
