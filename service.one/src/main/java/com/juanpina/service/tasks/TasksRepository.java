package com.juanpina.service.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TasksRepository extends JpaRepository<TasksEntity, Long> {

    @Query("select t from TasksEntity t where t.dueDate <= :deadline and t.notified = false")
    List<TasksEntity> findTasksDueWithinDeadline(LocalDateTime deadline);

}
