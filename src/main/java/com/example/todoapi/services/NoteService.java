package com.example.todoapi.services;

import com.example.todoapi.dtos.NoteShow;

import java.util.List;

public interface NoteService {
    List<NoteShow> getAllNote();

    NoteShow getNoteByID(Long id);

    void insertNote(NoteShow noteShow);

    void updateNote(NoteShow noteShow);

    void deleteNote(Long id);

    List<NoteShow> getNoteByName(String noteDes);
}
