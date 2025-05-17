package com.SpringBootProject.IMS.endpoint;

import com.SpringBootProject.IMS.service.UserService;
import com.SpringBootProject.IMS.valueobject.UserCreatePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEndPoint {

    @Autowired
    UserService userService;

    @PostMapping(value = "/create/user")
    public String createUser(@RequestBody UserCreatePojo userCreatePojo)
    {
        return this.userService.createUser(userCreatePojo);
    }
}
