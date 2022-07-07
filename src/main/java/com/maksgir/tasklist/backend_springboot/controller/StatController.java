package com.maksgir.tasklist.backend_springboot.controller;

import com.maksgir.tasklist.backend_springboot.entity.Stat;
import com.maksgir.tasklist.backend_springboot.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stat")
public class StatController {
    @Autowired
    private StatRepository statRepository;

}
