package com.example.toDo_backend_002.service;
import com.example.toDo_backend_002.repository.TaskRepository;
import com.example.toDo_backend_002.entity.Task;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    /////////////////////////////// main operation ////////////////////////////////////////
    /////////////// add task //////////////////////
    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    //////////////////////////////// read task ///////////////////////////////////////
    public Task getTaskById(Long id){
        try{
            Optional<Task> task = taskRepository.findById(id);
            return task.get();
        }
        catch (Exception e){
            System.out.println("<<<<<<<<<<<<<<< Error >>>>>>>>>>>>>>>>>>>>");
            System.out.println(e.getMessage());
            System.out.println();
            return null;
        }
    }
    public Task getTaskByName(String name) {
        return taskRepository.getTaskByCustom(name);
    }


    ////////////////////////////// update task ////////////////////////////////////////
    public Task updateTaskById(Long id, Task givenTask){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()){
            Task changeTask = task.get();
            changeTask.setProgress(givenTask.getProgress());
            changeTask.setTimeNeeded(givenTask.getTimeNeeded());
            changeTask.setShift(givenTask.getShift());
            taskRepository.save(changeTask);
            return changeTask;
        }
        else{
            System.out.println("Invalid Id given");
            return null;
        }
    }

    //////////////////////////// delete task /////////////////////////////


}
