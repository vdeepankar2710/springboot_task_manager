package com.spring.task_manager.service;
import com.spring.task_manager.entities.TaskEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask (String title, String description, String deadline) throws ParseException {
        TaskEntity task =  new TaskEntity();
        task.setId(taskId);
        task.setDescription(description);
        task.setTitle(title);
        task.setDeadline(deadlineFormatter.parse(String.valueOf(deadline)));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;
    }

    public ArrayList<TaskEntity> getTasks(){
        return tasks;
    }

    public TaskEntity getTaskById(int id){
        for(TaskEntity ele:tasks){
            if(ele.getId()==id){
                return ele;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int id, String description, String deadline, Boolean completed) throws ParseException {
        TaskEntity task = getTaskById(id);
        if(task==null){
            return null;
        }
        if(deadline!=null){
            task.setDeadline(deadlineFormatter.parse(deadline));
        }
        if(completed!=null){
            task.setCompleted(completed);
        }
        if (description!=null){
            task.setDescription(description);
        }
        return task;
    }
}
