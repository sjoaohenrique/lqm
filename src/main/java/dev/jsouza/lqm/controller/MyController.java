package dev.jsouza.lqm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jsouza.lqm.services.DockerService;

@RestController
@RequestMapping("/my")
public class MyController {

    @Autowired
    DockerService dockerService;

    @GetMapping
    public boolean test() {
        return dockerService.isContainerRunning("localstack/localstack");
    }
    
}
