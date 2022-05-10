package com.example.todoapi.dtos;

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
    private Timestamp remind;
    private Long uid;
}
