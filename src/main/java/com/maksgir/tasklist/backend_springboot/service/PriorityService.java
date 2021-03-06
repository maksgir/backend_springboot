package com.maksgir.tasklist.backend_springboot.service;


import com.maksgir.tasklist.backend_springboot.entity.Category;
import com.maksgir.tasklist.backend_springboot.entity.Priority;
import com.maksgir.tasklist.backend_springboot.repository.CategoryRepository;
import com.maksgir.tasklist.backend_springboot.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PriorityService {
    @Autowired
    private PriorityRepository repository;

    public List<Priority> findAll() {
        return repository.findAll();
    }

    public Priority save(Priority priority){
        return repository.save(priority);
    }

    public Optional<Priority> findById(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Priority> findByTitle(String title){
        return repository.findByTitle(title);
    }
}
