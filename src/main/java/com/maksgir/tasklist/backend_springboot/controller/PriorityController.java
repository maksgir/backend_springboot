package com.maksgir.tasklist.backend_springboot.controller;


import com.maksgir.tasklist.backend_springboot.entity.Priority;
import com.maksgir.tasklist.backend_springboot.repository.PriorityRepository;
import com.maksgir.tasklist.backend_springboot.search.PrioritySearchValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    @Autowired
    private PriorityRepository priorityRepository;

    @GetMapping()
    public List<Priority> findAllPriorities() {
        return priorityRepository.findAll();
    }


    @PostMapping("/add")
    public ResponseEntity<Priority> addPriority(@RequestBody Priority priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("id must be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }


    @PutMapping("/update")
    public ResponseEntity<Priority> updatePriority(@RequestBody Priority priority) {
        if (priority.getId() == null || priority.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priority> getPriorityById(@PathVariable Long id) {
        Optional<Priority> optional = priorityRepository.findById(id);
        Priority priority = null;
        if (optional.isPresent()) {
            priority = optional.get();


        } else {
            return new ResponseEntity("Priority with ID = " + id + " wasn't found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(priority);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Priority> deletePriorityById(@PathVariable Long id) {
        Optional<Priority> optional = priorityRepository.findById(id);
        Priority priority = null;
        if (optional.isPresent()) {
            priority = optional.get();
            priorityRepository.deleteById(id);
        } else {
            return new ResponseEntity("Priority with ID = " + id + " wasn't found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(priority);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues values){
        return ResponseEntity.ok(priorityRepository.findByTitle(values.getText()));
    }
}
