package ru.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.dto.QuizDto;
import ru.task.model.Quiz;
import ru.task.persist.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public void save(QuizDto quiz) {
        if (quiz.getQuestions().isEmpty()) {
            throw new RuntimeException("questions is empty");
        }
        quizRepository.save(new Quiz(quiz.getId(), quiz.getStartTime(), quiz.getEndTime(), quiz.getDescription(),
                quiz.getQuestions()));
    }

    @Override
    public void deleteById(Long id) {
        quizRepository.deleteById(id);
    }
}
