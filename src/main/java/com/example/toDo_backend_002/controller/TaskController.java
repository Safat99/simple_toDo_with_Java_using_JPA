package com.example.toDo_backend_002.controller;

import com.example.toDo_backend_002.entity.Task;
import com.example.toDo_backend_002.service.TaskService;
import org.apache.coyote.Response;
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

    @PostMapping("/saveTaskCustomQuery")
    ResponseEntity<?> addTaskCustom(@RequestBody Task task){
        taskService.addTaskCustom(task);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/insertTaskJPQL")
//    ResponseEntity<?> insertTaskJPQL(@RequestBody Task task){
//        taskService.insertTaskJPQL(task);
//        return ResponseEntity.ok().build();
//    }

    /////////////////////////////////////// read operation ///////////////////////////////////
    @GetMapping("/readTask/{id}")
    ResponseEntity<Task> getTask(@PathVariable("id") Long id){
        return new ResponseEntity<Task>(taskService.getTaskById(id),HttpStatus.OK);
    }

    @GetMapping("/getName/{taskName}")
    public ResponseEntity<Task> getTaskByName(@PathVariable("taskName") String taskName) {
        return new ResponseEntity<Task>(taskService.getTaskByName(taskName),HttpStatus.OK);
    }

    @GetMapping("/getTask/{id}")
    public ResponseEntity<Task> getTaskByName(@PathVariable("id") Long id) {
        return new ResponseEntity<Task>(taskService.getTaskByIdCustom(id),HttpStatus.OK);
    }

    @GetMapping("/getTaskByJPQL/{name}")// must be a unique name
    public ResponseEntity<Task> getTaskByJPQL(@PathVariable("name") String name){
        return new ResponseEntity<Task>(taskService.getTaskByJPQL(name), HttpStatus.OK);
    }

    ////////////////////////////// update operation ////////////////////////////////////////
    @PutMapping("/updateTask/{id}")
    ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task){
        return new ResponseEntity<Task>(taskService.updateTaskById(id,task),HttpStatus.OK);
    }

    @PutMapping("/updateCustomTask/{id}")
    ResponseEntity<Integer> updateTaskCustom(@PathVariable("id") Long id, @RequestBody Task task){
        return new ResponseEntity<Integer>(taskService.updateTaskCustom(id,task),HttpStatus.OK);
    }

    @PutMapping("/updateCustomTaskJPQL/{id}")
    ResponseEntity<Integer> updateTaskCustomJPQL(@PathVariable("id") Long id, @RequestBody Task task){
        return new ResponseEntity<Integer>(taskService.updateTaskCustomJPQL(id,task),HttpStatus.OK);
    }
    //////////////////////////////// delete operation /////////////////////////////////////////
    @DeleteMapping("/deleteTask/{id}")
    ResponseEntity<Task> deleteTask(@PathVariable("id") Long id){
        return new ResponseEntity<Task>(taskService.deleteTaskById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTaskCustom/{id}")
    ResponseEntity<?> deleteTaskCustom(@PathVariable("id") Long id){
        taskService.deleteTaskByIdCustom(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteJPQL/{id}")
    ResponseEntity<?> deleteTaskCustomJPQL(@PathVariable("id") Long id){
        taskService.deleteTaskCustomJPQL(id);
        return ResponseEntity.ok().build();
    }


}
