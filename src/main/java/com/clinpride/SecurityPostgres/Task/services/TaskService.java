package com.clinpride.SecurityPostgres.Task.services;

import com.clinpride.SecurityPostgres.Project.model.Project;
import com.clinpride.SecurityPostgres.Task.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {

    public Task createTask(Task task);
    public List<Task> getAllTask();
    public String deleteTask(String id);
    public Task editTask(Task updatedTask);
    public List<Task> getAllTaskByProjectId(String id);
    public Task getTaskId(String taskId);
    public List<Task>getAssigneeEmail(String Email);
    public Task getTaskIdAndEmail(String taskId, String employeeEmail);
    public String deleteByTaskId(String taskId);
}
