package com.maksgir.tasklist.backend_springboot.service;


import com.maksgir.tasklist.backend_springboot.entity.Stat;
import com.maksgir.tasklist.backend_springboot.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StatService {
    @Autowired
    private StatRepository repository;

    public Optional<Stat> findById(Long id){
        return repository.findById(id);
    }
}
