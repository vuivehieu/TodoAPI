package com.example.todoapi.services;

import com.example.todoapi.dtos.NoteShow;
import com.example.todoapi.entities.NoteEntity;
import com.example.todoapi.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImp implements NoteService{
    @Autowired
    NoteRepository noteRepository;
    @Override
    public List<NoteShow> getAllNote() {
        return noteRepository.findAll().stream().map(noteEntity -> NoteShow.builder().id(noteEntity.getId()).note_des(noteEntity.getNote_description()).remind(noteEntity.getRemind_time()).build()).collect(Collectors.toList());
    }

    @Override
    public NoteShow getNoteByID(Long id) {
        NoteEntity noteEntity = noteRepository.findById(id).get();
        return NoteShow.builder().id(noteEntity.getId()).note_des(noteEntity.getNote_description()).remind(noteEntity.getRemind_time()).build();
    }

    @Override
    public void insertNote(NoteShow noteShow) {
        noteRepository.save(NoteEntity.builder().id(noteShow.getId()).note_description(noteShow.getNote_des()).remind_time(noteShow.getRemind()).build());
    }

    @Override
    public void updateNote(NoteShow noteShow) {
        NoteEntity note = noteRepository.findById(noteShow.getId()).get();
        note.setNote_description(noteShow.getNote_des());
        note.setRemind_time(noteShow.getRemind());
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.delete(noteRepository.findById(id).get());
    }

    @Override
    public List<NoteShow> getNoteByName(String noteDes) {
        return noteRepository.findAllByNote_description(noteDes).stream().map(noteEntity -> NoteShow.builder().id(noteEntity.getId()).note_des(noteEntity.getNote_description()).remind(noteEntity.getRemind_time()).build()).collect(Collectors.toList());
    }
}
