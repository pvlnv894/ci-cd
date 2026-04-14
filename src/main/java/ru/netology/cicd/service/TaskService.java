package ru.netology.cicd.service;

import ru.netology.cicd.entity.Task;
import ru.netology.cicd.exception.TaskAlreadyExists;
import ru.netology.cicd.exception.TaskNotFound;
import ru.netology.cicd.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public void addTask(Task task) {
        Task t = repository.add(task);
        if (t == null) {
            throw new TaskAlreadyExists("Task " + task.getId() + " already exists");
        }
    }

    public void updateTask(Task task, Long id) {
        Task t = repository.update(task, id);
        if (t == null) {
            throw new TaskNotFound("Task " + id + " not found");
        }
    }

    public void deleteTask(Long id) {
        Task t = repository.deleteById(id);
        if (t == null) {
            throw new TaskNotFound("Task " + id + " not found");
        }
    }
}
