package ru.task.dto;

import ru.task.model.Question;
import ru.task.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class QuizDto {

    private Long id;
    private User user;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private Set<Question> questions = new HashSet<>();

    private QuizDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class QuizDtoBuilder {
        private final QuizDto quizDto = new QuizDto();

        public QuizDtoBuilder withId(Long id) {
            quizDto.setId(id);
            return this;
        }

        public QuizDtoBuilder withUser(User user) {
            quizDto.setUser(user);
            return this;
        }

        public QuizDtoBuilder withStartTime(LocalDateTime startTime) {
            quizDto.setStartTime(startTime);
            return this;
        }

        public QuizDtoBuilder withEndTime(LocalDateTime endTime) {
            quizDto.setEndTime(endTime);
            return this;
        }

        public QuizDtoBuilder withDescription(String description) {
            quizDto.setDescription(description);
            return this;
        }

        public QuizDtoBuilder withQuestions(Set<Question> questions) {
            quizDto.setQuestions(questions);
            return this;
        }

        public QuizDto build() {
            return quizDto;
        }
    }
}

