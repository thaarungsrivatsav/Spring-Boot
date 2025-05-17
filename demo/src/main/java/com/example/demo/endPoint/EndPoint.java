package com.example.demo.endPoint;

import com.example.demo.Entity.Employee;
import com.example.demo.Entity.ReturnEmployee;
import com.example.demo.Request.*;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EndPoint {
    @Autowired
    DemoService demoService;

//    @GetMapping(value = "/getvalues")
//    public SampleRequest sampleRequest(@RequestParam String name , @RequestParam String dob , @RequestParam String email , @RequestParam List<String> expertise)
//    {
//        return demoService.getSampleRequest(name , dob , email , expertise);
//    }

    @PostMapping(value = "/welcome")
    public SampleRequest hi(@RequestBody SampleRequest sampleRequest)
    {
//        demoService.mapping(sampleRequest.getName(),sampleRequest.getExpertise());
        return this.demoService.getSampleRequest(sampleRequest);
    }
    @PostMapping(value = "/sqlconnection")
    public void sqlconnection( @RequestBody SampleRequest sampleRequest )
    {
        this.demoService.sqlConnection(sampleRequest);
    }
    //using path varibale we search that id in the db and will print the values in the postman
    @RequestMapping(value = "/sqlsearch/{id}")
    public PojoforSearchIdValues search(@PathVariable int id)
    {
        return this.demoService.searchById(id);
    }
    //using post & get we use resultparam ->search and get the employee details
//    @GetMapping(value = "/sqlsearch")
//    public PojoforSearchIdValues search(@RequestParam (value = "id") int id)
//    {
//        return this.demoService.searchById(id);
//    }
//    @PostMapping(value = "/sqlsearch")
//    public PojoforSearchIdValues search(@RequestParam (value = "id") int id)
//    {
//        return this.demoService.searchById(id);
//    }


    @PostMapping(value = "/insertemployee")
    public void insert(@RequestBody PojoForEmployeeIntoTable pojoForEmployeeIntoTable)
    {
         this.demoService.insertintotable(pojoForEmployeeIntoTable);
    }
    @GetMapping(value = "/searchbyid/{id}")
    public ReturnEmployee search1(@PathVariable Long id)
    {
       return this.demoService.search(id);
    }

    @PutMapping(value = "/updateemployee")
    public Employee update(@RequestBody PojoForEmployeeUpdate pojoForEmployeeUpdate)
    {
        return this.demoService.update(pojoForEmployeeUpdate);
    }
    @DeleteMapping (value = "/deletemployee/{id}")
    public void delete(@PathVariable Long id)
    {
        this.demoService.delete(id);
    }

    @GetMapping(value = "/searchbyname")
    public PojoForEmployeeUpdate searchName(@RequestParam (value = "name") String name)
    {
        return this.demoService.searchForName(name);
    }
    @GetMapping(value = "/searchbynameanddesignation")
    public PojoForEmployeeUpdate searchNameAndDesignation(@RequestParam (value = "name") String name , @RequestParam(value = "designation") String designation)
    {
        return this.demoService.searchForNameForDesignation(name,designation);
    }

    //search using
    @GetMapping(value = "/searchbydesignation")
    public List<PojoForEmployeeUpdate> searchDesignation(@RequestParam(value = "designation") String designation)
    {
        return this.demoService.searchForDesignation(designation);
    }

    @PostMapping(value = "/insertprojects")
    public void insertintoprojecttable(@RequestBody EmployeeRequest employeeRequest)
    {
        this.demoService.createEmployeeProject(employeeRequest);
    }


    //
    @GetMapping(value = "/getprojectdetails")
    public EmployeeProjectResponse getproejctdetail(@RequestParam(value = "id") Long id)
    {
        return this.demoService.getProjectDetails(id);
    }

}
