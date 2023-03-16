package com.suelen.learningtestingjava.services;

import com.suelen.learningtestingjava.domain.Users;
import com.suelen.learningtestingjava.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    Users findById(Integer id);
    List<Users> findAll();
    Users create(UserDTO obj);
    Users update(UserDTO obj);
    void delete(Integer id);
}
