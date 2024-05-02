package com.hivework.domain.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
public class Controller {

    @RequestMapping(value = "/up", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String helloWorld(){
        return "I'M UP";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String testAdmin(){
        return "admin";
    }
}
