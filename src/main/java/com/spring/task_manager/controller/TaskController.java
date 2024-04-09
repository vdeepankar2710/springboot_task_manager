package com.spring.task_manager.controller;

import com.spring.task_manager.dto.CreateTaskDTO;
import com.spring.task_manager.dto.ErrorResponseDTO;
import com.spring.task_manager.dto.UpdateTaskDTO;
import com.spring.task_manager.entities.TaskEntity;
import com.spring.task_manager.service.TaskService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException {
        var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date format"));
        }
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }
}

