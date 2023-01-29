package com.example.springbootthymeleaftw.service;

import com.example.springbootthymeleaftw.model.entity.QuestionEntity;
import com.example.springbootthymeleaftw.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<QuestionEntity> getAll(){
        return questionRepository.findAll();
    }
}
