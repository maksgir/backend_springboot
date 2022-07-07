package com.maksgir.tasklist.backend_springboot.repository;


import com.maksgir.tasklist.backend_springboot.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {
}
