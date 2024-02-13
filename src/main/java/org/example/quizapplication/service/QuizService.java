package org.example.quizapplication.service;

import org.example.quizapplication.model.Question;
import org.example.quizapplication.model.QuestionWrapper;
import org.example.quizapplication.model.Quiz;
import org.example.quizapplication.model.Response;
import org.example.quizapplication.repository.QuestionRepository;
import org.example.quizapplication.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

  @Autowired
  QuizRepository repository;
  @Autowired
  QuestionRepository questionRepository;

  public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
    List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);

    if(questions.size() == 0) {
      Quiz quiz = new Quiz();
      quiz.setTitle(title);

      quiz.setQuestions(questions);
      repository.save(quiz);
      return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
    } else{
      return new ResponseEntity<>("No questions found with that category", HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(long id){
    Optional<Quiz> quiz = repository.findById(id);
    List<Question> questions = quiz.get().getQuestions();
    List<QuestionWrapper> questionsForUser = new ArrayList<>();
    for(Question q : questions){
      QuestionWrapper wrapper = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
      questionsForUser.add(wrapper);
    }
    return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
  }

  public ResponseEntity<Integer> submitQuiz(long id, List<Response> responses) {
    Quiz quiz = repository.findById(id).get();
    List<Question> questions = quiz.getQuestions();
    int right = 0;
    int i = 0;
    for (Response response : responses) {
      if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
        right++;
      }
      i++;
    }
    return new ResponseEntity<>(right, HttpStatus.OK);
  }
}
