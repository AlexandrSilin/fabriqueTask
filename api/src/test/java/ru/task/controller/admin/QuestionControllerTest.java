package ru.task.controller.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.task.model.Question;
import ru.task.model.QuestionType;
import ru.task.persist.QuestionsRepository;

import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionsRepository questionsRepository;

//    @Autowired
//    private QuizRepository quizRepository;

    @BeforeEach
    public void init() {
        Set<Question> questions = new HashSet<>();
        questions.add(new Question(1L, QuestionType.TEXT, "testQuestion", null));
        questions.add(new Question(2L, QuestionType.TEXT, "testQuestion", null));
        questionsRepository.saveAll(questions);
//        LocalDateTime time = LocalDateTime.now();
//        quizRepository.save(new Quiz(1L, time, time, "testDescription", questions));
    }

    @Test
    public void testQuestionDetails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/admin/questions/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].question", is("testQuestion")))
                .andExpect(jsonPath("$.[0].type", is("TEXT")))
                .andExpect(jsonPath("$.[0].answer", nullValue()));
    }
}
