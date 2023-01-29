package com.example.springbootthymeleaftw.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Questions", schema = "public", catalog = "college")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "text")
    private String text;

    @Basic
    @Column(name = "correctAnswer")
    private String correcAnswer;

}
