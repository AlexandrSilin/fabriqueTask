package ru.task.dto;

import ru.task.model.QuestionType;

public class QuestionDto {
    private Long id;
    private String question;
    private QuestionType type;
    private String answer;

    public QuestionDto() {

    }

    public QuestionDto(Long id, QuestionType type, String question, String answer) {
        this.id = id;
        this.type = type;
        this.question = question;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String text) {
        this.question = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

