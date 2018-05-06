package com.goyal.springsurvey.controller;

import com.goyal.springsurvey.configuration.BasicConfiguration;
import com.goyal.springsurvey.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;

    @Autowired
    private BasicConfiguration basicConfiguration;

    @RequestMapping("/welcome")
    public String welcome() {
        return welcomeService.getWelcomeMessage();
    }

    @RequestMapping("/dynamic-config")
    public Map dynamiConfiguration() {
        Map map = new HashMap();
        map.put("message", basicConfiguration.getMessage());
        map.put("number", basicConfiguration.getNumber());
        map.put("value", basicConfiguration.isValue());

        return map;
    }
}
