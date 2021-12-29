package ru.task.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task.model.CompleteQuiz;

public interface CompleteQuizRepository extends JpaRepository<CompleteQuiz, Long> {
}
