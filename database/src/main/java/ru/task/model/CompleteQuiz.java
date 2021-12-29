package ru.task.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "complete_quiz")
public class CompleteQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_time")
    @NotNull
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @NotNull
    private LocalDateTime endTime;

    @Column(name = "description")
    private String description;

    @Column(name = "question")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "quiz_questions",
            joinColumns = @JoinColumn(name = "questions_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    private Set<Question> questions = new HashSet<>();

    public CompleteQuiz() {

    }

    public CompleteQuiz(Long id, User user, LocalDateTime startTime, LocalDateTime endTime, String description,
                        Set<Question> questions) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.questions = questions;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompleteQuiz that = (CompleteQuiz) o;
        return id.equals(that.id) &&
                user.equals(that.user) &&
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime) &&
                description.equals(that.description) &&
                questions.equals(that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, startTime, endTime, description);
    }
}