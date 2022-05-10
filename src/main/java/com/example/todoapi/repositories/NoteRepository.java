package com.example.todoapi.repositories;

import com.example.todoapi.entities.NoteEntity;
import com.example.todoapi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    @Query("SELECT a From NoteEntity a where a.note_description like %:noteDes%")
    List<NoteEntity> findAllByNote_description(String noteDes);

    List<NoteEntity> findAllByUser(UserEntity user);

}
