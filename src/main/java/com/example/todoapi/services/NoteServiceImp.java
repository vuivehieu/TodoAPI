package com.example.todoapi.services;

import com.example.todoapi.dtos.NoteShow;
import com.example.todoapi.entities.NoteEntity;
import com.example.todoapi.repositories.NoteRepository;
import com.example.todoapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImp implements NoteService{
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<NoteShow> getAllNoteByUID(Long uid) {
        return noteRepository.findAllByUserId(uid).stream().map(noteEntity -> new NoteShow().toDTO(noteEntity)).sorted(Comparator.comparing(NoteShow::getCreate)).collect(Collectors.toList());
    }

    @Override
    public NoteShow getNoteByID(Long id) {
        NoteEntity noteEntity = noteRepository.findById(id).get();
        return new NoteShow().toDTO(noteEntity);
    }

    @Override
    public void insertNote(NoteShow noteShow) {
        noteRepository.save(NoteEntity.builder().id(noteShow.getId()).note_description(noteShow.getNote_des()).remind_time(noteShow.getRemind()).user(userRepository.findById(noteShow.getUid()).get()).build());
    }

    @Override
    public void updateNote(NoteShow noteShow, Long nid) {
        NoteEntity note = noteRepository.findById(nid).get();
        note.setNote_description(noteShow.getNote_des());
        note.setRemind_time(noteShow.getRemind());
        note.setUser(userRepository.findById(noteShow.getUid()).get());
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<NoteShow> getNoteLike(String noteDes, Long uid) {
        return noteRepository.findAllByNote_description(noteDes, uid).stream().map(noteEntity -> new NoteShow().toDTO(noteEntity)).collect(Collectors.toList());
    }
}
