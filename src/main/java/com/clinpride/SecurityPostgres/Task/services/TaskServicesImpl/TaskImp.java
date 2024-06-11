package com.clinpride.SecurityPostgres.Task.services.TaskServicesImpl;


import com.clinpride.SecurityPostgres.Project.Repo.ProjectRepo;
import com.clinpride.SecurityPostgres.Project.model.Project;
import com.clinpride.SecurityPostgres.Task.model.Task;
import com.clinpride.SecurityPostgres.Task.repo.TaskRep;
import com.clinpride.SecurityPostgres.Task.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@AllArgsConstructor
public class TaskImp implements TaskService {
    final TaskRep taskRepo;
    final ProjectRepo projectRepo;

    @Override
    public Task createTask(Task task) {
        try {
            Project project = projectRepo.findByProjectId(task.getProjectId());
            if (project != null) {
                task.setProjectName(project.getName());
                return taskRepo.save(task);
            } else {
                System.out.println("Project not found!");
                return null;
            }
        } catch (Exception e) {
            // Handle the exception or log an error message
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Task> getAllTask() {
        try {
            return taskRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public String deleteTask(String id) {
        try {
            Task existingTask = taskRepo.findById(id).orElse(null);
            if (existingTask != null) {
                return "Deleted Successfully";
            } else {
                return "Project with id: " + id + " does not exist";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while deleting the task";
        }
    }

    @Override
    public Task editTask(Task updatedTask) {
        try {
            Project project = projectRepo.findByProjectId(updatedTask.getProjectId());
            if (project != null) {
                Task existingTask = taskRepo.findById(updatedTask.getId()).orElse(null);
                if (existingTask != null) {
                    existingTask.setAssignerEmail(updatedTask.getAssignerEmail());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setProjectId(updatedTask.getProjectId());
                    existingTask.setAssignerName(updatedTask.getAssignerName());
                    existingTask.setLastName(updatedTask.getLastName());
                    existingTask.setEnd_date(updatedTask.getEnd_date());
                    existingTask.setViewedStatus(updatedTask.getViewedStatus());
                    existingTask.setStatus(updatedTask.getStatus());
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setTaskId(updatedTask.getTaskId());
                    existingTask.setStarting_date(updatedTask.getStarting_date());
                    existingTask.setProjectName(updatedTask.getProjectName());
                    return taskRepo.save(existingTask);
                }
            } else {
                System.out.println("Project not found!");
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Task> getAllTaskByProjectId(String id) {
        try {
            return taskRepo.findAllByProjectId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Task getTaskId(String taskId) {
        try {
            Task task =  taskRepo.findOneByTaskId(taskId);
            return task;
            } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Task> getAssigneeEmail(String Email) {
        try {
            return taskRepo.findAllByAssignerEmail(Email);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Task getTaskIdAndEmail(String taskId, String employeeEmail) {
        try {
            return taskRepo.findOneByTaskIdAndEmployeeEmail(taskId, employeeEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String deleteByTaskId(String taskId) {
        try {
            Task task =  taskRepo.findOneByTaskId(taskId);
            if (task!=null){
                taskRepo.deleteByTaskId(taskId);
                return "Deleted Successfully";
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}