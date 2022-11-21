package com.example.toDo_backend_002.repository;

import com.example.toDo_backend_002.entity.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Query(value = "select * FROM task_table where taskName=:n",nativeQuery = true)
    Task getTaskByCustom(@Param("n") String name);

    @Modifying
    @Transactional // database er sathe connection related jinish pati handle kore
    // half way te kono jhamela na hoy seta ensure korbe..
    @Query(value = "insert into task_table (timeNeeded, taskName, shift, progress, startDate) values (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void insertTaskCustom(int timeNeeded,
                          String taskName,
                          String shift,
                          String progress,
                          String startDate
                          );




}
