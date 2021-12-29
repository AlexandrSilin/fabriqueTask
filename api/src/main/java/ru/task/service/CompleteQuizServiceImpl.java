package ru.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.dto.QuizDto;
import ru.task.model.CompleteQuiz;
import ru.task.model.Quiz;
import ru.task.persist.CompleteQuizRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompleteQuizServiceImpl implements CompleteQuizService {
    private final CompleteQuizRepository quizRepository;

    @Autowired
    public CompleteQuizServiceImpl(CompleteQuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<CompleteQuiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<CompleteQuiz> findById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public void save(QuizDto quiz) {
        quizRepository.save(new CompleteQuiz(quiz.getId(), quiz.getUser(), quiz.getStartTime(), quiz.getEndTime(), quiz.getDescription(),
                quiz.getQuestions()));
    }

    @Override
    public void deleteById(Long id) {
        quizRepository.deleteById(id);
    }
}
