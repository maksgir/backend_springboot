package com.maksgir.tasklist.backend_springboot.repository;


import com.maksgir.tasklist.backend_springboot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
