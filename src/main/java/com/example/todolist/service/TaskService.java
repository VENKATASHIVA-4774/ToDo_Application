package com.example.todolist.service;

import com.example.todolist.dto.TaskRequest;
import com.example.todolist.model.Priority;
import com.example.todolist.model.Task;
import com.example.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> findAll() {
        return repo.findAll();
    }

    public Task findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task create(TaskRequest req) {
        Task t = new Task(req.getDescription(), req.getPriority());
        return repo.save(t);
    }

    public Task update(Long id, TaskRequest req) {
        Task t = findById(id);
        t.setDescription(req.getDescription());
        t.setPriority(req.getPriority());
        return repo.save(t);
    }

    public Task changePriority(Long id, Priority priority) {
        Task t = findById(id);
        t.setPriority(priority);
        return repo.save(t);
    }

    public void delete(Long id) {
        repo.delete(findById(id));
    }
}
