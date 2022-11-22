package com.example.toDo_backend_002.repository;

import com.example.toDo_backend_002.entity.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends CrudRepository<Task, Long> {

    //////////////////////////////////////  read   /////////////////////////////////////////////
    @Query(value = "select * FROM task_table where taskName=:n",nativeQuery = true)
    Task getTaskByCustom(@Param("n") String name);

    @Query(value = "select t FROM Task t where taskName=:name")
    Task getTaskByJPQL(@Param("name") String name);

    @Query(value = "select * from task_table where id=?1", nativeQuery = true)
    Task getTaskByIdCustom(Long id);

    ///////////////////////////////// insert  //////////////////////////////////////
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

//    @Modifying
//    @Transactional
//    @Query("insert into Task (timeNeeded, taskName, shift, progress, startDate) values (?1, ?2, ?3, ?4, ?5)")
//    void insertTaskJPQL(
//            int timeNeeded,
//            String taskName,
//            String shift,
//            String progress,
//            String startDate
//    );

    //////////////////////////////////// update  /////////////////////////////////////////////
    @Modifying
    @Transactional
    @Query(value = "update task_table set timeNeeded=?1, shift=?2, progress=?3 where id=?4", nativeQuery = true)
    int updateTaskCustom(
            int timeNeeded,
            String shift,
            String progress,
            Long id
            );

    @Modifying
    @Transactional
    @Query("update Task set timeNeeded=?1, shift=?2, progress=?3 where id=?4")
    int updateTaskCustomJPQL(
            int timeNeeded,
            String shift,
            String progress,
            Long id
    );


    /////////////////////////////////////////  delete  //////////////////////////////////////////
    @Modifying
    @Transactional
    @Query(value = "delete from task_table where id=?1", nativeQuery = true)
    void deleteTaskByIdCustom(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from Task where id=?1")
    void deleteTaskByIdCustomJPQL(Long id);




}
