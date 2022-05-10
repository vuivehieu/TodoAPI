package com.example.todoapi.services;


import com.example.todoapi.entities.UserEntity;
import com.example.todoapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean addNewUser(UserEntity userEntity){
        UserEntity existUser = userRepository.findByUsername(userEntity.getUsername()).get();
        if (existUser == null){
            UserEntity user = new UserEntity();
            user.setUsername(userEntity.getUsername());
            user.setPassword(userEntity.getPassword());
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }
}
