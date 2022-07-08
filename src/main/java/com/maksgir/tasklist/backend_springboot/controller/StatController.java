package com.maksgir.tasklist.backend_springboot.controller;


import com.maksgir.tasklist.backend_springboot.entity.Stat;
import com.maksgir.tasklist.backend_springboot.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stat")
public class StatController {
    @Autowired
    private StatRepository statRepository;

    private Long defaultId = 1L;


    @GetMapping()
    public ResponseEntity<Stat> findStat() {
        return ResponseEntity.ok(statRepository.findById(defaultId).get());
    }

}
