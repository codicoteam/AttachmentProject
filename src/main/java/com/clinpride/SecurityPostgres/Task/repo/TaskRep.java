package com.clinpride.SecurityPostgres.Task.repo;

import com.clinpride.SecurityPostgres.Project.model.Project;
import com.clinpride.SecurityPostgres.Task.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRep extends MongoRepository<Task, String> {
    List<Task> findAllByProjectId(String projectId);
    Task findOneByTaskId(String taskId);
    List<Task> findAllByAssignerEmail(String assignerEmail);
    Task findOneByTaskIdAndEmployeeEmail(String projectId, String employeeEmail);
    void deleteByTaskId(String taskId);
}
