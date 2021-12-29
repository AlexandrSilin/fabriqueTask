package ru.task.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.task.model.*;
import ru.task.persist.UsersRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private static final LocalDateTime time = LocalDateTime.now();
    private UserService userService;

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
        Set<CompleteQuiz> completeQuiz = new HashSet<>();
        completeQuiz.add(new CompleteQuiz(quiz.getId(), user, quiz.getStartTime(), quiz.getEndTime(),
                quiz.getDescription(), quiz.getQuestions()));
        user.setUserQuiz(quizzes);
        user.setQuizPassed(new ArrayList<>(completeQuiz));
        return user;
    }

    @BeforeEach
    public void init() {
        userService = new UserServiceImpl(mock(UsersRepository.class));
    }

    @Test
    public void testFindAll() {
        List<User> expectedUsers = new ArrayList<>(Arrays.asList(getExpectedUser(1L), getExpectedUser(2L)));
        when(userService.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userService.findAll();
        assertNotNull(actualUsers);
        assertEquals(expectedUsers, actualUsers);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    public void testFindById(Long id) {
        when(userService.findById(id)).thenReturn(Optional.of(getExpectedUser(id)));
        Optional<User> user = userService.findById(id);
        User expectedUser = getExpectedUser(id);
        assertNotNull(user.get());
        User actualUser = user.get();
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getRoles(), actualUser.getRoles());
        assertEquals(expectedUser.getUserQuiz(), actualUser.getUserQuiz());
        assertEquals(expectedUser.getQuizPassed(), actualUser.getQuizPassed());
    }
}
