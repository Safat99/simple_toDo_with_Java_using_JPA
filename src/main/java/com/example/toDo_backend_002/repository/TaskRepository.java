package com.example.toDo_backend_002.repository;

import com.example.toDo_backend_002.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Query(value = "SELECT * FROM task_table WHERE taskName=:n",nativeQuery = true)
    Task getTaskByCustom(@Param("n") String name);
}
