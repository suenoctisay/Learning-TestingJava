package com.suelen.learningtestingjava.services;

import com.suelen.learningtestingjava.domain.Users;

import java.util.List;

public interface UserService {

    Users findById(Integer id);
    List<Users> findAll();
}
