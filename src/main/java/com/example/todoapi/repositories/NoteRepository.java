package com.example.todoapi.repositories;

import com.example.todoapi.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    @Query("SELECT a From NoteEntity a where a.note_description like %:noteDes%")
    List<NoteEntity> findAllByNote_description(String noteDes);
}
