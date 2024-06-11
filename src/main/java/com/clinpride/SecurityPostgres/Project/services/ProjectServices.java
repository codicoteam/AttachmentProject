package com.clinpride.SecurityPostgres.Project.services;

import com.clinpride.SecurityPostgres.Project.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProjectServices {
    public Project createProject(Project project);
    public List<Project> getAllProject();
    public String deleteProject(String id);
    public Project editProject(Project updatedProject);
    public List<Project> getProjectByMangerEmail(String email);
}
