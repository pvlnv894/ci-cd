package ru.netology.cicd.repository;

import ru.netology.cicd.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private List<Task> tasks = new ArrayList<>();

    public List<Task> findAll() {
        return tasks;
    }

    public Task findById(Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public Task add(Task task) {
        if (findById(task.getId()) != null) return null;
        tasks.add(task);
        return task;
    }

    public Task update(Task task, Long id) {
        Task t = findById(id);
        if (t == null) return null;
        t.setTitle(task.getTitle());
        t.setCreatedAt(task.getCreatedAt());
        t.setDone(task.isDone());
        return t;
    }

    public Task deleteById(Long id) {
        Task t = findById(id);
        if (t != null) {
            tasks.remove(t);
            return t;
        }
        return null;
    }

    public void clear() {
        tasks.clear();
    }
}
