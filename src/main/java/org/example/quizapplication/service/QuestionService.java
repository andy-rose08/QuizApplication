package org.example.quizapplication.service;

import org.example.quizapplication.model.Question;
import org.example.quizapplication.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
  @Autowired
  QuestionRepository repo;
  public ResponseEntity<List<Question>> getAllQuestions() {
    try {
      return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
    try {
      return new ResponseEntity<>(repo.findAllByCategory(category), HttpStatus.OK);
    }catch (Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Question> addQuestion(Question question) {
    try {

      return new ResponseEntity<>(repo.save(question), HttpStatus.CREATED);
    }catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Question> deleteQuestion(Long id) {
    try {
      if(repo.existsById(id)) {
        repo.deleteById(id);
        if(!repo.existsById(id)) {
          return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
      } else {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  public ResponseEntity<Question> updateQuestion(Question question) {

    try{
        if(repo.existsById(question.getId())) {
          return new ResponseEntity<>(repo.save(question), HttpStatus.OK);
        }else{
          return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }catch (Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
