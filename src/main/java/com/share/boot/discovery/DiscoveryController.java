package com.share.boot.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscoveryController {

    @Autowired
    DiscoveryService discoveryService;

    @GetMapping(value = "/services")
    public List<String> services() {
        return discoveryService.getServices();
    }
    
    @GetMapping(value = "/ribbon/{id}")
    public String ribbon(@PathVariable("id") String id) {
        return discoveryService.ribbon(id);
    }
}
