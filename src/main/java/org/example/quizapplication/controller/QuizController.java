package org.example.quizapplication.controller;

import org.example.quizapplication.model.Question;
import org.example.quizapplication.model.QuestionWrapper;
import org.example.quizapplication.model.Response;
import org.example.quizapplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
  @Autowired
  QuizService service;

  @PostMapping("create")
  public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
    return service.createQuiz(category, numQ, title);

  }

  @GetMapping("get/{id}")
  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable long id){
    return service.getQuizQuestions(id);
  }
  @PostMapping("submit/{id}")
  public ResponseEntity<Integer> submitQuiz(@PathVariable long id, @RequestBody List<Response> responses ){
    return service.submitQuiz(id, responses);
  }
}
