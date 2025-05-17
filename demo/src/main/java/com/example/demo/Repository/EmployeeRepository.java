package com.example.demo.Repository;

import com.example.demo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    //this is the interface method we use rather than querying

    //query to search by name ..... we need to use this method names only to use these queries
    public Employee findByName(String name);

//query replaced by for searching with name and designation
    public Employee findByNameAndDesignation(String name , String designation);
    public List<Employee> findByDesignation(String designation);
}
