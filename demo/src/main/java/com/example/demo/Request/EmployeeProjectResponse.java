package com.example.demo.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeProjectResponse {
    Long id;
    String name;
//    List<String> projects;
//    Long projectId;
//    ProjectPojo projectPojo;
    List<ProjectPojo> projectPojos;
}
