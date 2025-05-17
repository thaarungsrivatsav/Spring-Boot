package com.example.demo.service;

import com.example.demo.Entity.Employee;
import com.example.demo.Entity.EmployeeProject;
import com.example.demo.Entity.ReturnEmployee;
import com.example.demo.Repository.EmployeeProjectRespository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.*;

@Service
public class DemoService {
    public SampleRequest getSampleRequest(SampleRequest sampleRequest)
    {
        System.out.println(sampleRequest.getName()+sampleRequest.getEmail()+sampleRequest.getDob()+sampleRequest.getId());
        return sampleRequest;
    }
    public void sqlConnection(SampleRequest sampleRequest)
    {
        Connection connection;
        {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/springboot_database","root","admin");
//                System.out.println(sampleRequest.getName()+sampleRequest.getExpertise().get(0));
                System.out.println("Connected");
                PreparedStatement preparedStatement = connection.prepareStatement("Insert into employee_table values(?,?,?,?)");
                preparedStatement.setInt(1,sampleRequest.getId());
                preparedStatement.setString(2,sampleRequest.getName());
                preparedStatement.setString(3,sampleRequest.getEmail());
                preparedStatement.setString(4, sampleRequest.getDob());
                preparedStatement.executeUpdate();
                System.out.println("Created");


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void mapping(String n , List<String> x)
    {
        Map<String , List<String>> employeemap=new HashMap<>();
        employeemap.put(n,x);
        System.out.println(employeemap);
    }

    public PojoforSearchIdValues searchById(int id)
    {
        System.out.println("Id to be searched = "+id);
        PojoforSearchIdValues pj = new PojoforSearchIdValues();
        Connection connection;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/springboot_database","root","admin");
//                System.out.println(sampleRequest.getName()+sampleRequest.getExpertise().get(0));
                System.out.println("Connected");
                PreparedStatement preparedStatement = connection.prepareStatement("Select * from employee_table where emp_id=?");
                preparedStatement.setInt(1,id);
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println("Searched");
                while(rs.next())
                {
                    pj.setName(rs.getString("emp_name"));
                    pj.setDob(rs.getString("emp_dob"));
                    pj.setEmail(rs.getString("emp_email"));
                    pj.setId(rs.getInt("emp_id"));

                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return pj;
    }
    @Autowired
            //to use JPA we need to create an JPA repository to call the inbuilt methods.
    EmployeeRepository employeeRepository;
    // all the below methods we defined are using JPA concept where we do not need to query any single dmbs operations , see the above methods where we have used sql queries so long , but using JPA
    // we do not need to sql query just call the in-bulilt methods of the JPA for every query executions.

    // we are inserting an new employee into the employee table
    public void insertintotable(PojoForEmployeeIntoTable pojoForEmployeeIntoTable)
    {
        Employee employee = new Employee();
        employee.setDesignation(pojoForEmployeeIntoTable.getDesignation());
        employee.setName(pojoForEmployeeIntoTable.getName());
        employeeRepository.save(employee);

    }
    // we are searching whether the employee is present in the employee table
    public ReturnEmployee search(Long id)
    {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
       if(optionalEmployee.isPresent())
       {
           Employee emp = optionalEmployee.get();
           ReturnEmployee re = new ReturnEmployee();
           re.setId((emp.getId()));
           re.setName(emp.getName());
           re.setDesignation(emp.getDesignation());
//           System.out.println(emp.getId());
           return re;

       }
       return null;
    }
    //uopdating the employee table by the searching the employee in the table and then we update the entire employee row
    public Employee update(PojoForEmployeeUpdate pojoForEmployeeUpdate)
    {
        Optional<Employee> optionalEmployee = employeeRepository.findById(pojoForEmployeeUpdate.getId());
        if(optionalEmployee.isPresent())
        {
           Employee emp = optionalEmployee.get();
           emp.setName(pojoForEmployeeUpdate.getName());
           emp.setDesignation(pojoForEmployeeUpdate.getDesignation());
           emp.setId(pojoForEmployeeUpdate.getId());
           employeeRepository.save(emp);
            return emp;
        }
        return null;
    }
   public void delete(Long id)
   {
       this.employeeRepository.deleteById(id);
   }
    //search using by the column names (name) sent from the url from postman
   public PojoForEmployeeUpdate searchForName(String name)
   {
       Employee employee = employeeRepository.findByName(name);
       PojoForEmployeeUpdate x = new PojoForEmployeeUpdate();
       x.setId(employee.getId());
       x.setName(employee.getName());
       x.setDesignation(employee.getDesignation());
       return x;

   }

   //search using by the column names (name and designation) sent from the url from postman
    public PojoForEmployeeUpdate searchForNameForDesignation(String name, String designation)
    {
        Employee employee = employeeRepository.findByNameAndDesignation(name,designation);
        PojoForEmployeeUpdate x = new PojoForEmployeeUpdate();
        x.setId(employee.getId());
        x.setName(employee.getName());
        x.setDesignation(employee.getDesignation());
        return x;

    }
    //when we get a column values its present in many columns then we have to create a list to accept the response
    public List<PojoForEmployeeUpdate> searchForDesignation(String designation)
    {
        List<Employee> employees = employeeRepository.findByDesignation(designation);
        List<PojoForEmployeeUpdate> emp = new LinkedList<>();
        employees.forEach(employee -> {
            PojoForEmployeeUpdate x = new PojoForEmployeeUpdate();
            x.setId(employee.getId());
            x.setName(employee.getName());
            x.setDesignation(employee.getDesignation());
            emp.add(x);

        });
        return emp;

    }
//this  will add into employee table and also it will  be added into the employee_project table
    public void createEmployeeProject(EmployeeRequest employeeRequest)
    {
        Employee employee = new Employee();
        employee.setId(employeeRequest.getId());
        employee.setName(employeeRequest.getName());
        employee.setDesignation(employeeRequest.getDesignation());
        List<EmployeeProject> employeeProjects = new LinkedList<>();
        employeeRequest.getProjects().forEach(s->{//we are adding each project with particular employee id into employee_project table
            EmployeeProject employeeProject = new EmployeeProject();
            employeeProject.setEmployee(employee);
            employeeProject.setProjectName(s);
            employeeProjects.add(employeeProject);
        });
//        employee.setEmployeeProjects(employeeProjects);//here we are adding the new employee into the employee table
        this.employeeRepository.save(employee);
    }

    @Autowired
    EmployeeProjectRespository employeeProjectRespository;
//
//public EmployeeProjectResponse getProjectDetails(Long id)
//{
//    List<EmployeeProject> employeeProjects = employeeProjectRespository.findByEmployeeId(id);
//    EmployeeProjectResponse employeeProjectResponse = new EmployeeProjectResponse();
//    List<String> projects = new ArrayList<>();
//    employeeProjects.forEach(s->{
////            EmployeeProject employeeProject = new EmployeeProject();
//        Employee employee = s.getEmployee();
//        projects.add(s.getProjectName());
//        employeeProjectResponse.setProjectId(s.getId());
//        employeeProjectResponse.setName(employee.getName());
//        employeeProjectResponse.setId(employee.getId());
//    });
//    employeeProjectResponse.setProjects(projects);
//
//    return employeeProjectResponse;
//}
//}

    public EmployeeProjectResponse getProjectDetails(Long id)
    {
        List<EmployeeProject> employeeProjects = employeeProjectRespository.findByEmployeeId(id);
        EmployeeProjectResponse employeeProjectResponse = new EmployeeProjectResponse();
//        ProjectPojo projectPojo = new ProjectPojo();
        List<ProjectPojo> projectPojos=new ArrayList<>();
        employeeProjects.forEach(s->{
//            EmployeeProject employeeProject = new EmployeeProject();
            ProjectPojo projectPojo = new ProjectPojo();
            Employee employee = s.getEmployee();
            employeeProjectResponse.setName(employee.getName());
            employeeProjectResponse.setId(employee.getId());
            projectPojo.setProjectId(s.getId());
            projectPojo.setProjectName(s.getProjectName());
            projectPojos.add(projectPojo);
//            employeeProjectResponse.setProjectPojo(projectPojo);

        });
        employeeProjectResponse.setProjectPojos(projectPojos);


        return employeeProjectResponse;
    }



    //TRANSACTION CONCEPTS IN SPRINGBOOT

//    @Transactional
//    public void insertUsingTransactionMethod()
//    {
//        Employee employee = new Employee();
//        employee.setName("kuland");
//        employee.setDesignation("SSEE");
//        this.employeeRepository.save(employee);
//
//        Employee employee1 = new Employee();
//        employee1.setName("ABCD");
//        employee1.setDesignation("Dev");
//        this.employeeRepository.save(employee1);
//
//    }

}
