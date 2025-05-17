package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Long id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "designation" , nullable = false)
    private String designation;

    @OneToMany(mappedBy = "employee" ,cascade = CascadeType.ALL)
    List<EmployeeProject> employeeProjects;
}
