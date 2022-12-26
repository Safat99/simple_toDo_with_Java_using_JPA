package com.example.toDo_backend_002.service;
import com.example.toDo_backend_002.projection.TaskProjection;
import com.example.toDo_backend_002.projection.TaskProjectionInterface;
import com.example.toDo_backend_002.repository.TaskRepository;
import com.example.toDo_backend_002.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void addTaskCustom(Task task){
         taskRepository.insertTaskCustom(
                task.getTimeNeeded(),
                task.getTaskName(),
                task.getShift(),
                task.getProgress(),
                task.getStartDate()
                );
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

    public Task getTaskByIdCustom(Long id){
        return taskRepository.getTaskByIdCustom(id);
    }

    public List<TaskProjectionInterface> getTaskByJPQL(String name){
        return taskRepository.getTaskByJPQL(name);
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

    public int updateTaskCustom(Long id, Task givenTask){
        Task task = taskRepository.getTaskByIdCustom(id);
        int result;
        if (task != null){
            result = taskRepository.updateTaskCustom(givenTask.getTimeNeeded(),givenTask.getShift(),givenTask.getProgress(),id);
        }
        else{
            result = -1;
            System.out.println("Invalid Id given");
        }
        return result;
    }

    public int updateTaskCustomJPQL(Long id, Task givenTask){
        Task task = taskRepository.getTaskByIdCustom(id);
        int result;
        if (task != null){
            result = taskRepository.updateTaskCustomJPQL(givenTask.getTimeNeeded(),givenTask.getShift(),givenTask.getProgress(),id);
        }
        else{
            result = -1;
            System.out.println("Invalid Id given");
        }
        return result;
    }



    /////////////////////////////////// <<delete task>> /////////////////////////////////////////
    public Task deleteTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
        }
        else{
            System.out.println("Invalid Id given");
        }
        return null;
    }

    public void deleteTaskByIdCustom(Long id){
        Task task = taskRepository.getTaskByIdCustom(id);
        if (task != null){
            taskRepository.deleteTaskByIdCustom(id);
        }
    }

    public void deleteTaskCustomJPQL(Long id){
        Task task = taskRepository.getTaskByIdCustom(id);
        if (task != null){
            taskRepository.deleteTaskByIdCustomJPQL(id);
        }
    }

}
