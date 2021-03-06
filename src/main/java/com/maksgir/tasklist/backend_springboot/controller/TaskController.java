package com.maksgir.tasklist.backend_springboot.controller;


import com.maksgir.tasklist.backend_springboot.entity.Category;
import com.maksgir.tasklist.backend_springboot.entity.Task;
import com.maksgir.tasklist.backend_springboot.repository.TaskRepository;
import com.maksgir.tasklist.backend_springboot.search.CategorySearchValues;
import com.maksgir.tasklist.backend_springboot.search.TaskSearchValues;
import com.maksgir.tasklist.backend_springboot.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping()
    public List<Task> findAllTasks() {
        return service.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("id must be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(service.save(task));
    }

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        if (task.getId() == null || task.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(service.save(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> optional = service.findById(id);
        Task task = null;
        if (optional.isPresent()) {
            task = optional.get();
        } else {
            return new ResponseEntity("Task with ID = " + id + " wasn't found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable Long id) {
        Optional<Task> optional = service.findById(id);
        Task task = null;
        if (optional.isPresent()) {
            service.deleteById(id);
            task = optional.get();
        } else {
            return new ResponseEntity("Task with ID = " + id + " wasn't found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(task);
    }


    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues values) {
        // ???????? ???????????? ?????????? ?????????? ?????????? - ???????????????? ?????? ??????????????????

        // ?????????????????? NullPointerException
        String text = values.getTitle() != null ? values.getTitle() : null;

        // ???????????????????????? Boolean ?? Integer
        Integer completed = values.getCompleted() != null ? values.getCompleted() : null;

        Long priorityId = values.getPriorityId() != null ? values.getPriorityId() : null;
        Long categoryId = values.getCategoryId() != null ? values.getCategoryId() : null;

        String sortColumn = values.getSortColumn() != null ? values.getSortColumn() : null;
        String sortDirection = values.getSortDirection() != null ? values.getSortDirection() : null;

        Integer pageNumber = values.getPageNumber() != null ? values.getPageNumber() : null;
        Integer pageSize = values.getPageSize() != null ? values.getPageSize() : null;


        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;


        // ?????????????????????? ?????? ????????????????

        // ???????????? ????????????????????
        Sort sort = Sort.by(direction, sortColumn);

        // ???????????? ????????????????????????????
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        // ?????????????????? ?????????????? ?? ???????????????????????? ??????????????
        Page result = service.findByParams(text, completed, priorityId, categoryId, pageRequest);


        return ResponseEntity.ok(result);

    }


}
