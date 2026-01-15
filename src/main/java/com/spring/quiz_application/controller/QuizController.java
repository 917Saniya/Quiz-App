package com.spring.quiz_application.controller;

import com.spring.quiz_application.model.QuizQuestion;
import com.spring.quiz_application.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping("/questions")
    public ResponseEntity<?> getQuestions(){
        return ResponseEntity.ok(quizService.getQuestions());
    }

    @PostMapping("/submit")
    public ResponseEntity<?>submitQuiz(@RequestBody Map<String, Map<Integer, String>> payload) {
        Map<Integer, String> answers = payload.get("answers");
        return ResponseEntity.ok(quizService.submitQuiz(answers));
    }

    @PostMapping("/question")
    public ResponseEntity<?> addQuestion(@RequestBody QuizQuestion question) {
        quizService.addQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}