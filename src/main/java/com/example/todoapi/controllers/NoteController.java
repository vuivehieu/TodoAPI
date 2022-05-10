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
        return ResponseEntity.ok(noteService.getAllNote(uid));
    }
    @GetMapping(produces = "application/json",value = "/{id}/{nid}")
    public ResponseEntity<?> getNoteByID(@PathVariable("id") Long id, @PathVariable("nid") Long nid){
        return ResponseEntity.ok(userService.getUserByID(id).getNoteShowSet().stream().filter(noteShow -> noteShow.getId() == nid).findFirst());
    }
    @PostMapping(produces = "application/json", value = "/{id}/add")
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
    @PutMapping(produces = "application/json", value = "{id}/edit/{idn}")
    public ResponseEntity<?> editNote(@RequestBody NoteShow noteShow, @PathVariable(required = true, value = "id") Long uid, @PathVariable(required = true, value = "idn") Long nid){
        NoteShow noteShow1 = noteService.getNoteByID(nid);
        noteShow1.setNote_des(noteShow.getNote_des());
        noteShow1.setRemind(noteShow.getRemind());
        noteService.updateNote(noteShow1, nid);
        return ResponseEntity.ok(noteShow1);
    }
    @DeleteMapping(produces = "application/json", value = "{id}/delete/{idn}")
    public ResponseEntity<?> deleteNote(@PathVariable(required = true, value = "id") Long uid, @PathVariable(required = true, value = "idn") Long nid){
        UserDTO user = userService.getUserByID(uid);
        Set<NoteShow> userNotes = user.getNoteShowSet();
        user.setNoteShowSet(userNotes.stream().filter(noteShow -> noteShow.getId()==nid).collect(Collectors.toSet()));
        userService.saveUser(user);
        noteService.deleteNote(nid);
        return ResponseEntity.ok("deleted");
    }
    @GetMapping(produces = "application/json", value = "{id}/find")
    public ResponseEntity<?> searchNote(@RequestParam("search") String search, @PathVariable(required = true,value = "id") Long uid){
        return ResponseEntity.ok(noteService.getNoteByDes(search,uid));
    }


}
