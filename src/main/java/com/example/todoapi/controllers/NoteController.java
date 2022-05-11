package com.example.todoapi.controllers;

import com.example.todoapi.dtos.NoteShow;
import com.example.todoapi.dtos.UserDTO;
import com.example.todoapi.services.NoteService;
import com.example.todoapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;

    @GetMapping(produces = "application/json",value = "/{id}/all")
    public ResponseEntity<?> getAllNote(@PathVariable("id") Long uid){
        return ResponseEntity.ok(noteService.getAllNoteByUID(uid));
    }
    @GetMapping(produces = "application/json", value = "/{id}/{nid}")
    public ResponseEntity<?> getNoteByID(@PathVariable("id") Long id, @PathVariable("nid") Long nid){
        return ResponseEntity.ok(noteService.getNoteByID(nid));
    }
    @PostMapping(produces = "application/json", value = "/{id}/add")
    public ResponseEntity<?> addNote(@RequestBody NoteShow noteShow, @PathVariable(required = false,value = "id") Long uid){
        if (uid!=null){
            noteShow.setUid(uid);
            noteService.insertNote(noteShow);
            return ResponseEntity.ok(noteShow);
        }
        return ResponseEntity.badRequest().body(noteShow);
    }
    @PutMapping(produces = "application/json", value = "{id}/edit/{idn}")
    public ResponseEntity<?> editNote(@RequestBody NoteShow noteShow, @PathVariable(required = true, value = "id") Long uid, @PathVariable(required = true, value = "idn") Long nid){
        NoteShow noteShow1 = noteService.getNoteByID(nid);
        noteShow1.setNote_des(noteShow.getNote_des());
        noteShow1.setRemind(noteShow.getRemind());
        noteService.updateNote(noteShow1, nid);
        return ResponseEntity.ok(noteShow1);
    }
    @DeleteMapping(produces = "application/json", value = "{id}/delete/{nid}")
    public ResponseEntity<?> deleteNote(@PathVariable(required = false, value = "id") Long uid, @PathVariable(required = true, value = "nid") Long nid){
        noteService.deleteNote(nid);
        return ResponseEntity.ok("deleted");
    }
    @GetMapping(produces = "application/json", value = "{id}/find")
    public ResponseEntity<?> searchNote(@RequestParam("search") String search, @PathVariable(required = true,value = "id") Long uid){
        return ResponseEntity.ok(noteService.getNoteLike(search, uid));
    }
}
