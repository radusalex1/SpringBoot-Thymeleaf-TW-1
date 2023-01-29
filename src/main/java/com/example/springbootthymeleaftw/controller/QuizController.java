package com.example.springbootthymeleaftw.controller;

import com.example.springbootthymeleaftw.model.entity.QuestionEntity;
import com.example.springbootthymeleaftw.model.entity.QuestionList;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.service.QuestionService;
import com.example.springbootthymeleaftw.service.SecurityService;
import com.example.springbootthymeleaftw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/QuizController")
public class QuizController {

    private final QuestionService questionService;
    private final SecurityService securityService;
    private final UserService userService;
    private static UserEntity loggedUser;
    @GetMapping("/OpenQuiz")
    public String openQuiz(@ModelAttribute("email") String email, Model model)
    {
        //var email  = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        loggedUser=userService.getByEmail(email).get();
        List<QuestionEntity> questions = questionService.getAll();

        model.addAttribute("questionList", questions);

        return "index";
    }

    private QuestionEntity getQuestionByIdFromQuestionList(List<QuestionEntity> questionEntityList,Long id)
    {
        for (QuestionEntity q:questionEntityList) {
            if(q.getId()==id)
            {
                return q;
            }
        }
        return null;
    }
    @PostMapping("/CheckTest")
    public String checkTest(@RequestParam Map<String, String> formData,Model model)
    {
        int score = 0;

        List<QuestionEntity> questions = questionService.getAll();

        for(Map.Entry<String,String> entry:formData.entrySet()){
            if(!entry.getKey().toString().startsWith("_csrf") && !entry.getValue().equals("")) {
                System.out.println(entry.getKey() + "" + entry.getValue());
                QuestionEntity question = getQuestionByIdFromQuestionList(questions,Long.valueOf(entry.getKey()));
                    if(question!=null)
                    {
                        if(question.getCorrecAnswer().equals(entry.getValue()))
                        {
                            score+=1;
                        }
                    }
                }
            }


        return "quizResult";

    }
}
