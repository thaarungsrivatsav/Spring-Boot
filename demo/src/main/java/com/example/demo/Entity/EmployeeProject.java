package com.example.demo.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "employee_project")
public class EmployeeProject {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_name")
    private String projectName;

    @ManyToOne()
    @JoinColumn(name ="employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;


}
