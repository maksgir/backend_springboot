package com.maksgir.tasklist.backend_springboot.controller;


import com.maksgir.tasklist.backend_springboot.entity.Priority;
import com.maksgir.tasklist.backend_springboot.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    @Autowired
    private PriorityRepository priorityRepository;

    @GetMapping()
    public List<Priority> showAllPriorities() {
        return priorityRepository.findAll();
    }
}
