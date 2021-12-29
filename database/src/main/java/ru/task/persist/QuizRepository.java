package ru.task.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
