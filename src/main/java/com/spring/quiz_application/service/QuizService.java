package com.spring.quiz_application.service;

import com.spring.quiz_application.model.QuizQuestion;
import com.spring.quiz_application.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }
    public List<Map<String,Object>> getQuestions() {
        List<QuizQuestion> questions = quizRepository.findAll();
        List<Map<String,Object>> response = new ArrayList<>();
        for (QuizQuestion q : questions) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",q.getId());
            map.put("question",q.getQuestion());
            map.put("options", List.of(q.getOptionA(),q.getOptionB(),
                    q.getOptionC(),q.getOptionD()));
            response.add(map);
        }
        return response;
    }
    public Map<String, Integer> submitQuiz(Map<Integer,String> answer) {
        int score = 0;
        int total = answer.size();
        for (Map.Entry<Integer, String> entry : answer.entrySet()) {
            int questionId = entry.getKey();
            String userAnswer = entry.getValue();

            QuizQuestion question = quizRepository.findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Invalid question ID: " + questionId));

            if (question.getCorrectOption().equalsIgnoreCase(userAnswer)) {
                score++;
            }
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("score", score);
        result.put("total", total);

        return result;
    }

    public void addQuestion(QuizQuestion question) {
        quizRepository.save(question);
    }
}