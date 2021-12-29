package ru.task.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "question")
    private String question;

    @NotNull
    @Column(name = "type")
    private QuestionType type;

    @Column(name = "answer")
    private String answer;

    public Question() {

    }

    public Question(Long id, QuestionType type, String question, String answer) {
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return id.equals(question1.id) &&
                question.equals(question1.question) &&
                type == question1.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, type, answer);
    }
}
