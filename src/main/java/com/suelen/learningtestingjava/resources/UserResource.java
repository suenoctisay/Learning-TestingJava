package com.suelen.learningtestingjava.resources;

import com.suelen.learningtestingjava.domain.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @GetMapping(value = "/{id}")
    public ResponseEntity<Users> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(new Users(1, "suelen", "suelen@mail.com", "123"));
    }
}