package com.example.spring.Security.repository;

import com.example.spring.Security.entiry.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DemoRepository extends JpaRepository<Demo,Long> {

    Optional<Demo> findByEmail(String email);
}
