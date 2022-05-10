package com.example.todoapi.services;


import com.example.todoapi.dtos.LoginRequest;
import com.example.todoapi.entities.RoleEntity;
import com.example.todoapi.entities.UserEntity;
import com.example.todoapi.repositories.RoleRepository;
import com.example.todoapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public boolean addNewUser(UserEntity userEntity){
        RoleEntity USER_ROLE = roleRepository.getById(2l);
        UserEntity existUser = userRepository.findByUsername(userEntity.getUsername()).get();
        if (existUser == null){
            UserEntity user = new UserEntity();
            user.setUsername(userEntity.getUsername());
            user.setPassword(userEntity.getPassword());
            user.setRoles(Set.of(USER_ROLE));
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }
}
