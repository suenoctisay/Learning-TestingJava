package com.suelen.learningtestingjava.services.implementations;

import com.suelen.learningtestingjava.domain.Users;
import com.suelen.learningtestingjava.exceptions.ObjectNotFound;
import com.suelen.learningtestingjava.repositories.UserRepository;
import com.suelen.learningtestingjava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepository repository;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFound("Object not found"));
    }

    public List<Users> findAll(){
        return repository.findAll();
    }
}
