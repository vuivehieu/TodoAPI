package com.example.todoapi.repositories;

import com.example.todoapi.entities.NoteEntity;
import com.example.todoapi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    @Query("SELECT n From NoteEntity n where n.note_description like %:search% and n.user.id = :id order by n.created_time")
    List<NoteEntity> findAllByNote_description(@PathParam("search") String search, @PathParam("id")Long id);
    List<NoteEntity> findAllByUserId(Long id);

}
