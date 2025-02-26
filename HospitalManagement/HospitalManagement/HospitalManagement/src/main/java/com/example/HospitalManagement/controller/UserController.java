package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.exception.UserNotFoundException;
import com.example.HospitalManagement.model.User;
import com.example.HospitalManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/user")
    User newUser (@RequestBody User newUser){

        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping ("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                     user.setCodParafaMedic(newUser.getCodParafaMedic());
                     user.setRole((newUser.getRole()));
                     user.setSurname((newUser.getSurname()));
                     user.setName((newUser.getName()));
                     user.setEmail(newUser.getEmail());
                     user.setAdresa(newUser.getAdresa());
                     user.setCnp(newUser.getCnp());
                     user.setVarsta(newUser.getVarsta());
                     user.setUsername(newUser.getUsername());
                     user.setPassword(newUser.getPassword());
                     return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));

    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id "+id+" has been deleted succesfully";
    }






}

