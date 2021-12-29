package ru.task.controller.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.task.model.*;
import ru.task.persist.*;

import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class UserQuizControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private QuestionsRepository questionsRepository;
//
//    @Autowired
//    private QuizRepository quizRepository;
//
//    @Autowired
//    private UsersRepository usersRepository;
//
//    @Autowired
//    private CompleteQuizRepository completeQuizRepository;
//
//    @Autowired
//    private RolesRepository rolesRepository;
//
//    private static final LocalDateTime time = LocalDateTime.now();
//
//    @BeforeEach
//    public void init() {
//        Set<Question> questions = new HashSet<>();
//        questions.add(new Question(1L, QuestionType.TEXT, "testQuestion", "testAnswer"));
//        questions.add(new Question(2L, QuestionType.TEXT, "testQuestion", "testAnswer"));
//        questionsRepository.saveAll(questions);
//        User user = getExpectedUser(1L);
//        usersRepository.save(user);
//        Role role = new Role(1L, "ADMIN", null);
//        rolesRepository.save(role);
//        user.setRoles(List.of(role));
//        usersRepository.save(user);
//        quizRepository.save(new Quiz(1L, null, null, "testDescription", questions));
//        CompleteQuiz quiz = new CompleteQuiz(1L, user, time, time, "testDescription", questions);
//        completeQuizRepository.save(quiz);
//        user.setQuizPassed(List.of(quiz));
//        usersRepository.save(user);
//    }
//
//    private static User getExpectedUser(Long id) {
//        return new User(id, "testUser_" + id, "testPassword", null, null, null);
//    }
//
//    @Test
//    public void testQuestionDetails() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/admin/quiz/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$.[0].user", nullValue()))
//                .andExpect(jsonPath("$.[0].startTime", nullValue()))
//                .andExpect(jsonPath("$.[0].endTime", nullValue()))
//                .andExpect(jsonPath("$.[0].description", is("testDescription")))
//                .andExpect(jsonPath("$.[0].questions[0].question", is("testQuestion")))
//                .andExpect(jsonPath("$.[0].questions[0].type", is("TEXT")))
//                .andExpect(jsonPath("$.[0].questions[0].answer", nullValue()));
//    }
}
