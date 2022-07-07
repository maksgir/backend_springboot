package com.maksgir.tasklist.backend_springboot.controller;


import com.maksgir.tasklist.backend_springboot.entity.Task;
import com.maksgir.tasklist.backend_springboot.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping()
    public List<Task> showAllTasks() {
        return taskRepository.findAll();
    }
}
