package ru.netology.cicd.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import ru.netology.cicd.entity.Task;
import ru.netology.cicd.exception.TaskAlreadyExists;
import ru.netology.cicd.exception.TaskNotFound;
import ru.netology.cicd.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTask(@Valid @RequestBody Task task) {
        service.addTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @PutMapping("/{id}")
    public void updateTask(@Valid @RequestBody Task task, @PathVariable Long id) {
        service.updateTask(task, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        service.deleteTask(id);
    }

    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<String> tnfHandler(TaskNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskAlreadyExists.class)
    public ResponseEntity<String> iiHandler(TaskAlreadyExists e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
