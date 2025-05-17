package com.SpringBootProject.IMS.service;


import com.SpringBootProject.IMS.entity.RoleTable;
import com.SpringBootProject.IMS.entity.UserProfileTable;
import com.SpringBootProject.IMS.repository.UserProfileRepository;
import com.SpringBootProject.IMS.valueobject.UserCreatePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserService {

    @Autowired
    UserProfileRepository userProfileRepository;


    public String createUser(UserCreatePojo userCreatePojo)
    {
        UserProfileTable userProfileTable = userProfileRepository.findByUserEmail(userCreatePojo.getEmail());
        if(userProfileTable != null)
        {
            return "User Already Exists";
        }

        RoleTable roleTable = new RoleTable();
        roleTable.setRoleId(userCreatePojo.getRoleId());
        String roleName = "";
        if(userCreatePojo.getRoleId()==1)
        {
            roleName="vendor";
        }
        else if(userCreatePojo.getRoleId()==2){
            roleName="admin";
        }
        roleTable.setRoleName(roleName);
        userProfileTable = new UserProfileTable();
        userProfileTable.setUserEmail(userCreatePojo.getEmail());
        userProfileTable.setUserName(userCreatePojo.getName());
        userProfileTable.setUserPassword(userCreatePojo.getPassword());
        userProfileTable.setRoleTable(roleTable);
        userProfileTable.setCreatedAt(LocalDateTime.now());
        userProfileRepository.save(userProfileTable);
        return "User Created";
    }
}
