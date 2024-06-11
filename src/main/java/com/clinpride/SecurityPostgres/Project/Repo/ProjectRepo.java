package com.clinpride.SecurityPostgres.Project.Repo;

import com.clinpride.SecurityPostgres.Project.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectRepo extends MongoRepository<Project, String> {
    List<Project> findByManagerEmail(String manager);
    Project findByProjectId(String projectId);

}
