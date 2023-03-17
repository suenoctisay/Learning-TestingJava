package com.suelen.learningtestingjava.services.implementations;

import com.suelen.learningtestingjava.domain.Users;
import com.suelen.learningtestingjava.domain.dto.UserDTO;
import com.suelen.learningtestingjava.exceptions.DataIntegrityViolation;
import com.suelen.learningtestingjava.exceptions.ObjectNotFound;
import com.suelen.learningtestingjava.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "sue";
    public static final String EMAIL = "sue@mail";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private Users user;
    private UserDTO userDTO;
    private Optional<Users> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        Users response = service.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Users.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void findByIdObjtNotFound(){
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFound("Object not found"));

        try{
            service.findById(ID);
        }catch (Exception ex){
            Assertions.assertEquals(ObjectNotFound.class, ex.getClass());
            Assertions.assertEquals("Object not found", ex.getMessage());
        }
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<Users> response = service.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(Users.class, response.get(INDEX).getClass());

        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(NAME, response.get(INDEX).getName());
        Assertions.assertEquals(EMAIL, response.get(INDEX).getEmail());
        Assertions.assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void createSuccess() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        Users response = service.create(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Users.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void createDataIntegrityViolation(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex){
            Assertions.assertEquals(DataIntegrityViolation.class, ex.getClass());
            Assertions.assertEquals("Email already registered in the system", ex.getMessage());
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByEmail() {
    }

    private void startUser(){
        user = new  Users (ID, NAME, EMAIL, PASSWORD);
        userDTO = new  UserDTO (ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new Users(ID, NAME, EMAIL, PASSWORD));
    }
}