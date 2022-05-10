package com.example.todoapi.services;


import com.example.todoapi.dtos.NoteShow;
import com.example.todoapi.dtos.UserDTO;
import com.example.todoapi.entities.NoteEntity;
import com.example.todoapi.entities.RoleEntity;
import com.example.todoapi.entities.UserEntity;
import com.example.todoapi.repositories.NoteRepository;
import com.example.todoapi.repositories.RoleRepository;
import com.example.todoapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    NoteRepository noteRepository;

    public boolean addNewUser(UserEntity userEntity){
        RoleEntity roleUser = roleRepository.findByName("ROLE_USER");
        UserEntity existUser = userRepository.findByUsername(userEntity.getUsername()).get();
        if (existUser == null){
            UserEntity user = new UserEntity();
            user.setUsername(userEntity.getUsername());
            user.setPassword(userEntity.getPassword());
            user.setRoles(Set.of(roleUser));
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }
    public Set<UserDTO> getAllUser(){
        return userRepository.findAll().stream().map(userEntity -> UserDTO.builder().id(userEntity.getId()).name(userEntity.getUsername()).noteShowSet(userEntity.getNoteEntities().stream().map(noteEntity -> NoteShow.builder().id(noteEntity.getId()).note_des(noteEntity.getNote_description()).remind(noteEntity.getRemind_time()).build()).collect(Collectors.toSet())).build()).collect(Collectors.toSet());
    }
    public UserDTO getUserByID(Long id){
        UserEntity a = userRepository.findById(id).get();
        UserDTO user = UserDTO.builder().id(a.getId()).noteShowSet(a.getNoteEntities().stream().map(noteEntity -> NoteShow.builder().id(noteEntity.getId()).note_des(noteEntity.getNote_description()).remind(noteEntity.getRemind_time()).build()).collect(Collectors.toSet())).name(a.getUsername()).build();
        return user;
    }

    public void saveUser(UserDTO userDTO){
        UserEntity a = userRepository.findById(userDTO.getId()).get();
        Set<NoteEntity> noteEntities = new HashSet<>();
        for (NoteEntity n: noteRepository.findAllByUser(userRepository.findById(userDTO.getId()).get())
             ) {
            noteEntities.add(n);
        }
        a.setNoteEntities(noteEntities);
        userRepository.save(a);
    }

}
