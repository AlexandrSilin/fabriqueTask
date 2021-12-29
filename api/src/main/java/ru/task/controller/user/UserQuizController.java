package ru.task.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.task.dto.QuizDto;
import ru.task.model.Quiz;
import ru.task.model.User;
import ru.task.service.CompleteQuizService;
import ru.task.service.QuizService;
import ru.task.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/quiz")
public class UserQuizController {
    private final QuizService quizService;
    private final UserService userService;
    private final CompleteQuizService completeQuizService;

    @Autowired
    public UserQuizController(QuizService quizService, UserService userService,
                              CompleteQuizService completeQuizService) {
        this.quizService = quizService;
        this.userService = userService;
        this.completeQuizService = completeQuizService;
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

    @PostMapping(produces = "application/json")
    public void completeQuiz(@RequestParam("userId") Optional<Long> userId, @RequestBody Quiz quiz) {
        QuizDto.QuizDtoBuilder builder = new QuizDto.QuizDtoBuilder();
        builder.withId(quiz.getId())
                .withStartTime(quiz.getStartTime())
                .withEndTime(quiz.getEndTime())
                .withDescription(quiz.getDescription())
                .withQuestions(quiz.getQuestions());
        if (userId.isPresent()) {
            Optional<User> user = userService.findById(userId.get());
            user.ifPresent(value -> completeQuizService.save(builder.withUser(value).build()));
        } else {
            completeQuizService.save(builder.build());
        }
    }

    @GetMapping("/all")
    public List<QuizDto> getAllQuiz() {
        return quizService.findAll().stream().map(UserQuizController::getQuizDto).collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public List<QuizDto> getUserQuiz(@PathVariable Long userId) {
        return userService.findById(userId).orElseThrow().getUserQuiz().stream()
                .map(UserQuizController::getQuizDto).collect(Collectors.toList());
    }
}
