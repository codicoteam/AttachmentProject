package com.clinpride.SecurityPostgres.Task.controllers;

import com.clinpride.SecurityPostgres.Project.model.Project;
import com.clinpride.SecurityPostgres.Project.services.ProjectServices;
import com.clinpride.SecurityPostgres.Task.controllers.request.CustomerErrorMessage;
import com.clinpride.SecurityPostgres.Task.model.Task;
import com.clinpride.SecurityPostgres.Task.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/kaributech/tasks")
public class TaskController {
    final TaskService taskService;
    @GetMapping("/get-All")
    public List<Task> getAllTask(){
        return taskService.getAllTask();
    }

    @PostMapping("/create-task")
    public ResponseEntity<?> CreateTask(@RequestBody Task task ){
        Task savedTask = taskService.createTask(task);
        if (savedTask != null) {
            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
        } else {
            CustomerErrorMessage errorMessage = new CustomerErrorMessage("Project not found");
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam String id){
        return taskService.deleteTask(id);
    }

    @PostMapping("/edit-task")
    public ResponseEntity<?> editTask(@RequestBody Task task){
        Task savedTask = taskService.editTask(task);
        if (savedTask != null) {
            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
        } else {
            CustomerErrorMessage errorMessage = new CustomerErrorMessage("Project not found");
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-by-assignee-email")
    public List<Task>  findAllTaskByAssigneeEmail(@RequestParam String email){
        return taskService.getAssigneeEmail(email);
    }

    @GetMapping("/get-all-by-project-id")
    public List<Task>  findAllTaskByProjectId(@RequestParam String projectId){
        return taskService.getAllTaskByProjectId(projectId);
    }

    @GetMapping("/get-all-by-task-id")
    public ResponseEntity<?> findByTaskId(@RequestParam String taskId){
        Task task = taskService.getTaskId(taskId);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
        } else {
            CustomerErrorMessage errorMessage = new CustomerErrorMessage("task not found");
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/get-by-task-id-and-email")
    public ResponseEntity<?> findAllTaskIdAndEmail(@RequestParam String taskId, String employeeEmail){
        Task task = taskService.getTaskIdAndEmail(taskId, employeeEmail);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
        } else {
            CustomerErrorMessage errorMessage = new CustomerErrorMessage("task not found");
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/delete-by-task-id")
    public ResponseEntity<?> deleteByTaskId(@RequestParam String taskId){
        String task = taskService.deleteByTaskId(taskId);
        if (task != null) {
            CustomerErrorMessage errorMessage = new CustomerErrorMessage("task deleted successfully");
            return new ResponseEntity<>(errorMessage, HttpStatus.ACCEPTED);
        } else {
            CustomerErrorMessage errorMessage = new CustomerErrorMessage("task not found");
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }
}
