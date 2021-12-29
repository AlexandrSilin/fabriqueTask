package ru.task.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.task.model.Question;
import ru.task.model.QuestionType;
import ru.task.persist.QuestionsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionServiceTest {
    private QuestionService questionService;

    private static Question getExpectedQuestion(Long id) {
        return new Question(id, QuestionType.TEXT, "testQuestion", null);
    }

    @BeforeEach
    public void init() {
        questionService = new QuestionServiceImpl(mock(QuestionsRepository.class));
    }

    @Test
    public void testFindAll() {
        List<Question> expectedQuestions = new ArrayList<>(Arrays.asList(getExpectedQuestion(1L), getExpectedQuestion(2L)));
        when(questionService.findAll()).thenReturn(new ArrayList<>(Arrays.asList(getExpectedQuestion(1L),
                getExpectedQuestion(2L))));
        List<Question> actualQuestions = questionService.findAll();
        assertNotNull(actualQuestions);
        assertEquals(expectedQuestions, actualQuestions);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    public void findById(Long id) {
        Question expectedQuestion = getExpectedQuestion(id);
        when(questionService.findById(id)).thenReturn(Optional.of(getExpectedQuestion(id)));
        Optional<Question> question = questionService.findById(id);
        assertNotNull(question.get());
        Question actualQuestion = question.get();
        assertNull(actualQuestion.getAnswer());
        assertEquals(expectedQuestion.getId(), actualQuestion.getId());
        assertEquals(expectedQuestion.getType(), actualQuestion.getType());
        assertEquals(expectedQuestion.getAnswer(), actualQuestion.getAnswer());
        assertEquals(expectedQuestion.getQuestion(), actualQuestion.getQuestion());
    }
}
