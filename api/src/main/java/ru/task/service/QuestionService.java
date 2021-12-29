package ru.task.service;

import ru.task.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> findAll();

    Optional<Question> findById(Long id);

    void save(Question quiz);

    void deleteById(Long id);
}
