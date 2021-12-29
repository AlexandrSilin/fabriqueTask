package ru.task.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.task.model.*;
import ru.task.persist.CompleteQuizRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompleteQuizServiceTest {
    private CompleteQuizService quizService;
    private static LocalDateTime time = LocalDateTime.now();

    private static User getExpectedUser(Long id) {
        User user = new User(id, "testUser_" + id, "testPassword", null, null, null);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L, "ADMIN", List.of(user)));
        roles.add(new Role(1L, "USER", List.of(user)));
        user.setRoles(roles);
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1L, QuestionType.TEXT, "testQuestion", "testAnswer"));
        questions.add(new Question(2L, QuestionType.CHOOSE_MANY, "testQuestion", "testAnswer"));
        Set<Quiz> quizzes = new HashSet<>();
        Quiz quiz = new Quiz(1L, time, time, "testDescription", new HashSet<>(questions));
        quizzes.add(quiz);
        user.setUserQuiz(quizzes);
        return user;
    }

    private static CompleteQuiz getExpectedCompleteQuiz(Long id) {
        User user = getExpectedUser(1L);
        CompleteQuiz quiz = new CompleteQuiz(id, null, time, time, "testDescription", null);
        Set<Question> questions = new HashSet<>();
        questions.add(new Question(1L, QuestionType.TEXT, "testQuestion", "testAnswer"));
        questions.add(new Question(2L, QuestionType.CHOOSE_MANY, "testQuestion", "testAnswer"));
        quiz.setQuestions(questions);
        quiz.setUser(user);
        user.setQuizPassed(List.of(quiz));
        return quiz;
    }

    @BeforeEach
    public void init() {
        quizService = new CompleteQuizServiceImpl(mock(CompleteQuizRepository.class));
        time = LocalDateTime.now();
    }

    @Test
    public void testFindAll() {
        List<CompleteQuiz> expectedQuiz = new ArrayList<>(Arrays.asList(getExpectedCompleteQuiz(1L),
                getExpectedCompleteQuiz(2L)));
        when(quizService.findAll()).thenReturn(new ArrayList<>(Arrays.asList(getExpectedCompleteQuiz(1L),
                getExpectedCompleteQuiz(2L))));
        List<CompleteQuiz> actualQuiz = quizService.findAll();
        assertNotNull(actualQuiz);
        assertEquals(expectedQuiz, actualQuiz);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    public void testFindById(Long id) {
        CompleteQuiz expectedQuiz = getExpectedCompleteQuiz(id);
        when(quizService.findById(id)).thenReturn(Optional.of(getExpectedCompleteQuiz(id)));
        Optional<CompleteQuiz> actualQuiz = quizService.findById(id);
        assertNotNull(actualQuiz.get());
        CompleteQuiz actual = actualQuiz.get();
        assertEquals(expectedQuiz.getId(), actual.getId());
        assertEquals(expectedQuiz.getQuestions(), actual.getQuestions());
        assertEquals(expectedQuiz.getDescription(), actual.getDescription());
        assertEquals(expectedQuiz.getEndTime(), actual.getEndTime());
        assertEquals(expectedQuiz.getStartTime(), actual.getStartTime());
        assertEquals(expectedQuiz.getUser(), actual.getUser());
    }
}
