package ru.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.task.model.Question;
import ru.task.persist.QuestionsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionsRepository questionsRepository;

    @Autowired
    public QuestionServiceImpl(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Override
    public List<Question> findAll() {
        return questionsRepository.findAll();
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionsRepository.findById(id);
    }

    @Override
    public void save(Question question) {
        questionsRepository.save(question);
    }

    @Override
    public void deleteById(Long id) {
        questionsRepository.deleteById(id);
    }
}
