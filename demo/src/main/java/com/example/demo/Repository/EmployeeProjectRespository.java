package com.example.demo.Repository;

import com.example.demo.Entity.EmployeeProject;
import com.example.demo.Request.EmployeeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeProjectRespository extends JpaRepository<EmployeeProject,Long> {

    List<EmployeeProject> findByEmployeeId(Long id);
}
