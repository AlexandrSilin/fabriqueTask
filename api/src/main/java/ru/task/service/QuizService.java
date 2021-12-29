package ru.task.service;

import ru.task.dto.QuizDto;
import ru.task.model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    List<Quiz> findAll();

    Optional<Quiz> findById(Long id);

    void save(QuizDto quiz);

    void deleteById(Long id);
}
