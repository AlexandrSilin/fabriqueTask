package ru.task.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.task.dto.QuestionDto;
import ru.task.dto.QuizDto;
import ru.task.model.Question;
import ru.task.model.Quiz;
import ru.task.service.QuestionService;
import ru.task.service.QuizService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final QuizService quizService;

    @Autowired
    public QuestionController(QuestionService questionService, @Qualifier("quizServiceImpl") QuizService quizService) {
        this.questionService = questionService;
        this.quizService = quizService;
    }

    private static QuestionDto getQuestionDto(Question question) {
        return new QuestionDto(question.getId(), question.getType(), question.getQuestion(), question.getAnswer());
    }

    @GetMapping("/all")
    public List<QuestionDto> findAll() {
        return questionService.findAll().stream().map(QuestionController::getQuestionDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public QuestionDto findById(@PathVariable Long id) {
        return questionService.findById(id).map(QuestionController::getQuestionDto).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        Question question = questionService.findById(id).orElseThrow();
        List<Quiz> quizList = quizService.findAll();
        for (Quiz quiz : quizList) {
            if (quiz.getQuestions().contains(question)) {
                Set<Question> questions = quiz.getQuestions();
                questions.remove(question);
                QuizDto.QuizDtoBuilder builder = new QuizDto.QuizDtoBuilder();
                builder.withId(quiz.getId())
                        .withStartTime(quiz.getStartTime())
                        .withEndTime(quiz.getEndTime())
                        .withDescription(quiz.getDescription())
                        .withQuestions(quiz.getQuestions());
                quizService.save(builder.build());
            }
        }
        questionService.deleteById(id);
    }

    @PostMapping(produces = "application/json")
    public void save(@RequestBody Question question) {
        questionService.save(question);
    }
}
