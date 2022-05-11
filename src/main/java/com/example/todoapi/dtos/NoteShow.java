package com.example.todoapi.dtos;

import com.example.todoapi.entities.NoteEntity;
import lombok.*;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteShow {
    private Long id;
    private String note_des;
    private Timestamp create;
    private Timestamp remind;
    private Long uid;

    public NoteShow toDTO(NoteEntity noteEntity){
        return NoteShow.builder()
                .id(noteEntity.getId())
                .note_des(noteEntity.getNote_description())
                .create(noteEntity.getCreated_time())
                .remind(noteEntity.getRemind_time())
                .uid(noteEntity.getUser().getId())
                .build();
    }
}
