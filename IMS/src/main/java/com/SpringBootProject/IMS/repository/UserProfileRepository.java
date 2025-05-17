package com.SpringBootProject.IMS.repository;


import com.SpringBootProject.IMS.entity.UserProfileTable;
import org.springframework.data.jpa.repository.JpaRepository;


//this repo handles with all the user_profile table related query methods.
public interface UserProfileRepository extends JpaRepository<UserProfileTable , Long> {

    public UserProfileTable findByUserEmail(String userEmail);
}