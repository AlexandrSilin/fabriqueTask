package ru.task.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task.model.Question;

public interface QuestionsRepository extends JpaRepository<Question, Long> {
}
