package com.leacappi.todoapp.service;

import com.leacappi.todoapp.mapper.TaskInDTOToTask;
import com.leacappi.todoapp.persistence.entity.Task;
import com.leacappi.todoapp.persistence.entity.TaskStatus;
import com.leacappi.todoapp.persistence.repository.TaskRepository;
import com.leacappi.todoapp.service.dto.TaskInDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask (TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }

    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status) {
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished (Long id) {
        this.repository.markTaskAsFinished(id);
    }
}
