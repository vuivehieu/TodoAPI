package com.example.todoapi.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tbl_note")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "note_description", length = 2000)
    private String note_description;
    @Column(name = "remind_time")
    private Timestamp remind_time;
    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp created_time;
    @UpdateTimestamp
    @Column(name = "updated_time")
    private Timestamp updated_time;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
