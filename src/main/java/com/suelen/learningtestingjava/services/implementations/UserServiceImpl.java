package com.suelen.learningtestingjava.services.implementations;

import com.suelen.learningtestingjava.domain.Users;
import com.suelen.learningtestingjava.domain.dto.UserDTO;
import com.suelen.learningtestingjava.exceptions.DataIntegratyViolation;
import com.suelen.learningtestingjava.exceptions.ObjectNotFound;
import com.suelen.learningtestingjava.repositories.UserRepository;
import com.suelen.learningtestingjava.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFound("Object not found"));
    }

    public List<Users> findAll(){
        return repository.findAll();
    }

    @Override
    public Users create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Users.class));
    }

    @Override
    public Users update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Users.class));
    }

    public void findByEmail(UserDTO obj){
        Optional<Users> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegratyViolation("Email already registered in the system");
        }
    }
}
