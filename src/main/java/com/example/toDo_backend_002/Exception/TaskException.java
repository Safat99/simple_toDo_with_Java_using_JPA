package com.example.toDo_backend_002.Exception;

public class TaskException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    // constructor declaration
    public TaskException(String resourceName, String fieldName, Object fieldValue){
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}
