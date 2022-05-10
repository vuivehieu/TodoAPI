package com.example.todoapi.controllers;

import com.example.todoapi.dtos.NoteShow;
import com.example.todoapi.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class NoteController {
@Autowired
    NoteService noteService;

    @GetMapping(produces = "application/json",value = "/all")
    public ResponseEntity<?> getAllNote(){
        return ResponseEntity.ok(noteService.getAllNote());
    }
    @GetMapping(produces = "application/json",value = "/{id}")
    public ResponseEntity<?> getNoteByID(@PathVariable("id") Long id){
        return ResponseEntity.ok(noteService.getNoteByID(id));
    }
    @PostMapping(produces = "application/json", value = "/add")
    public ResponseEntity<?> addNote(@RequestBody NoteShow noteShow){
        noteService.insertNote(noteShow);
        return ResponseEntity.ok(noteShow);
    }
    @PostMapping(produces = "application/json", value = "/edit")
    public ResponseEntity<?> editNote(@RequestBody NoteShow noteShow){
        noteService.updateNote(noteShow);
        return ResponseEntity.ok(noteShow);
    }
    @GetMapping(produces = "application/json", value = "/find/{search}")
    public ResponseEntity<?> searchNote(@PathVariable("search") String search){
        return ResponseEntity.ok(noteService.getNoteByName(search));
    }
}
