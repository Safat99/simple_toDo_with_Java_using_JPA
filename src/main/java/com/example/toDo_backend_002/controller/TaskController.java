package com.example.toDo_backend_002.controller;

import com.example.toDo_backend_002.entity.Task;
import com.example.toDo_backend_002.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    ////////////////////////////////////// create operation ///////////////////////////////////
    @PostMapping("/saveTask")
    ResponseEntity<Task> addTask(@RequestBody Task task){
        return new ResponseEntity<Task>(taskService.addTask(task), HttpStatus.CREATED);
    }

    /////////////////////////////////////// read operation ///////////////////////////////////
    @GetMapping("/readTask/{id}")
    ResponseEntity<Task> getTask(@PathVariable("id") Long id){
        return new ResponseEntity<Task>(taskService.getTaskById(id),HttpStatus.OK);
    }

    @GetMapping("/getName/{taskName}")
    public ResponseEntity<Task> getTaskByName(@PathVariable("taskName") String taskName) {
        return new ResponseEntity<Task>(taskService.getTaskByName(taskName),HttpStatus.OK);
    }


    ////////////////////////////// update operation ////////////////////////////////////////
    @PutMapping("/updateTask/{id}")
    ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task){
        return new ResponseEntity<Task>(taskService.updateTaskById(id,task),HttpStatus.OK);
    }

    //////////////////////////////// delete operation /////////////////////////////////////////
    @DeleteMapping("/deleteTask/{id}")
    ResponseEntity<Task> deleteTask(@PathVariable("id") Long id){
        return new ResponseEntity<Task>(taskService.deleteTaskById(id), HttpStatus.OK);
    }

}
