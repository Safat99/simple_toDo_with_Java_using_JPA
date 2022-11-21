package com.example.toDo_backend_002.entity;

import javax.persistence.*;

@Entity
@Table(name = "task_table")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // why Long or long is used >> still unclear... why not integer.

    @Column(name = "timeNeeded")
    private int timeNeeded;
    @Column(name = "taskName")
    private String taskName;

    @Column(name = "shift")
    private String shift;

    @Column(name = "progress")
    private String progress;

    @Column(name = "startDate")
    private String startDate;

    public int getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(int timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
