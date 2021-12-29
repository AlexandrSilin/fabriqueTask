package ru.task.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.task.model.Question;
import ru.task.model.QuestionType;
import ru.task.model.Quiz;
import ru.task.persist.QuizRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuizServiceTest {
    private QuizService quizService;
    private static LocalDateTime time = LocalDateTime.now();

    private static Quiz getExpectedQuiz(Long id) {
        Quiz quiz = new Quiz(id, time, time, "testDescription", null);
        Set<Question> questions = new HashSet<>();
        questions.add(new Question(1L, QuestionType.TEXT, "testQuestion", null));
        questions.add(new Question(2L, QuestionType.CHOOSE_MANY, "testQuestion", null));
        quiz.setQuestions(questions);
        return quiz;
    }

    @BeforeEach
    public void init() {
        quizService = new QuizServiceImpl(mock(QuizRepository.class));
        time = LocalDateTime.now();
    }

    @Test
    public void testFindAll() {
        List<Quiz> expectedQuiz = new ArrayList<>(Arrays.asList(getExpectedQuiz(1L),
                getExpectedQuiz(2L)));
        when(quizService.findAll()).thenReturn(new ArrayList<>(Arrays.asList(getExpectedQuiz(1L),
                getExpectedQuiz(2L))));
        List<Quiz> actualQuiz = quizService.findAll();
        assertNotNull(actualQuiz);
        assertEquals(expectedQuiz, actualQuiz);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    public void testFindById(Long id) {
        Quiz expectedQuiz = getExpectedQuiz(id);
        when(quizService.findById(id)).thenReturn(Optional.of(getExpectedQuiz(id)));
        Optional<Quiz> actualQuiz = quizService.findById(id);
        assertNotNull(actualQuiz.get());
        Quiz actual = actualQuiz.get();
        assertEquals(expectedQuiz.getId(), actual.getId());
        assertEquals(expectedQuiz.getQuestions(), actual.getQuestions());
        assertEquals(expectedQuiz.getDescription(), actual.getDescription());
        assertEquals(expectedQuiz.getEndTime(), actual.getEndTime());
        assertEquals(expectedQuiz.getStartTime(), actual.getStartTime());
    }
}
