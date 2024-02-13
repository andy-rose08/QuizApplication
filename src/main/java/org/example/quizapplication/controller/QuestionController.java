package org.example.quizapplication.controller;

import org.example.quizapplication.model.Question;
import org.example.quizapplication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
  @Autowired
  QuestionService questionService;

  @GetMapping("allQuestions")
  public ResponseEntity<List<Question>> getAllQuestions(){
    return questionService.getAllQuestions();
  }
  @GetMapping("category/{category}")
  public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
    return questionService.getQuestionsByCategory(category);
  }

  @PostMapping("addQuestion")
  public ResponseEntity<Question> addQuestion(@RequestBody Question question){
    return questionService.addQuestion(question);
  }
  @DeleteMapping("deleteQuestion/{id}")
  public ResponseEntity<Question> deleteQuestion(@PathVariable Long id){
    return  questionService.deleteQuestion(id);
  }
  @PutMapping("updateQuestion/{id}")
  public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question){
    return questionService.updateQuestion(question);
  }
}
