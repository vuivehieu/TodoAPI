package com.example.todoapi.services;

import com.example.todoapi.dtos.NoteShow;

import java.util.List;

public interface NoteService {
    List<NoteShow> getAllNoteByUID(Long uid);

    NoteShow getNoteByID(Long id);

    void insertNote(NoteShow noteShow);

    void updateNote(NoteShow noteShow, Long nid);

    void deleteNote(Long id);

    List<NoteShow> getNoteLike(String noteDes, Long uid);
}
