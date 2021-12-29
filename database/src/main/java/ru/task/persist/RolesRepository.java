package ru.task.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task.model.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
