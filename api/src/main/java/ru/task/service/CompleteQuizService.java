package ru.task.service;

import ru.task.dto.QuizDto;
import ru.task.model.CompleteQuiz;

import java.util.List;
import java.util.Optional;

public interface CompleteQuizService {
    List<CompleteQuiz> findAll();

    Optional<CompleteQuiz> findById(Long id);

    void save(QuizDto quiz);

    void deleteById(Long id);
}
