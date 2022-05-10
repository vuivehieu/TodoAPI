package com.example.todoapi.controllers;

import com.example.todoapi.dtos.NoteShow;
import com.example.todoapi.dtos.UserDTO;
import com.example.todoapi.services.NoteService;
import com.example.todoapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;

    @GetMapping(produces = "application/json",value = "/all")
    public ResponseEntity<?> getAllNote(){
        return ResponseEntity.ok(noteService.getAllNote());
    }
    @GetMapping(produces = "application/json",value = "/{id}")
    public ResponseEntity<?> getNoteByID(@PathVariable("id") Long id){
        return ResponseEntity.ok(noteService.getNoteByID(id));
    }
    @PostMapping(produces = "application/json", value = "/add/{id}")
    public ResponseEntity<?> addNote(@RequestBody NoteShow noteShow, @PathVariable(required = false,value = "id") Long uid){
        Set<NoteShow> noteShowSet;
        if (uid!=null){
            UserDTO userDTO = userService.getUserByID(uid);
            if(userDTO.getNoteShowSet()== null){
                noteShowSet=new HashSet<>();
            }else {
                noteShowSet = userDTO.getNoteShowSet();
            }
            noteShowSet.add(noteShow);
            noteShow.setUid(userDTO.getId());
            noteService.insertNote(noteShow);
            userDTO.setNoteShowSet(noteShowSet);
            userService.saveUser(userDTO);
        }else
        {
            noteService.insertNote(noteShow);
        }
        return ResponseEntity.ok(noteShow);
    }
    @PostMapping(produces = "application/json", value = "/edit/{id}")
    public ResponseEntity<?> editNote(@RequestBody NoteShow noteShow, @PathVariable("id") Long id){
        NoteShow noteShow1 = noteService.getNoteByID(id);
        noteShow1.setNote_des(noteShow.getNote_des());
        noteShow1.setRemind(noteShow.getRemind());
        noteService.updateNote(noteShow);
        return ResponseEntity.ok(noteShow1);
    }
    @GetMapping(produces = "application/json", value = "/find/{search}")
    public ResponseEntity<?> searchNote(@PathVariable("search") String search){
        return ResponseEntity.ok(noteService.getNoteByName(search));
    }


}
