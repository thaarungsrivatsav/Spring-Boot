package com.example.demo.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EmployeeRequest {


    private Long id;
    private String name;
    private String designation;
    private List<String> projects;

}