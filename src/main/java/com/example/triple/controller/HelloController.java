package com.example.triple.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/hello")
public class HelloController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<String> Hello(){
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }
}
