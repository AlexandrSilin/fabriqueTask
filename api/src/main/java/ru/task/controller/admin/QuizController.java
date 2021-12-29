package ru.task.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.task.dto.QuizDto;
import ru.task.model.Quiz;
import ru.task.service.QuizService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/admin/quiz")
@RestController
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(@Qualifier("quizServiceImpl") QuizService quizService) {
        this.quizService = quizService;
    }

    private static QuizDto getQuizDto(Quiz quiz) {
        QuizDto.QuizDtoBuilder builder = new QuizDto.QuizDtoBuilder();
        builder.withId(quiz.getId())
                .withStartTime(quiz.getStartTime())
                .withEndTime(quiz.getEndTime())
                .withDescription(quiz.getDescription())
                .withQuestions(quiz.getQuestions());
        return builder.build();
    }

    @GetMapping("/all")
    public List<QuizDto> findAll() {
        return quizService.findAll().stream()
                .map(QuizController::getQuizDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public QuizDto findById(@PathVariable Long id) {
        return quizService.findById(id).map(QuizController::getQuizDto).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        quizService.deleteById(id);
    }

    @PostMapping(produces = "application/json")
    public void save(@RequestBody QuizDto quiz) {
        Optional<Quiz> quiz1 = quizService.findById(quiz.getId());
        if (quiz1.isPresent() && !quiz1.get().getStartTime().equals(quiz.getStartTime())) {
            throw new RuntimeException("Start time cannot be changed");
        }
        quizService.save(quiz);
    }
}
