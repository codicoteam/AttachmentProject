package com.clinpride.SecurityPostgres.Project.services.Imp;

import com.clinpride.SecurityPostgres.Project.Repo.ProjectRepo;
import com.clinpride.SecurityPostgres.Project.model.Project;
import com.clinpride.SecurityPostgres.Project.services.ProjectServices;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class ServiceImpl  implements ProjectServices {
    final ProjectRepo projectRepo;
    @Override
    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public List<Project> getAllProject() {
        return projectRepo.findAll();
    }

    @Override
    public String deleteProject(String id) {
        Project existingProject = projectRepo.findById(id).orElse(null);
        if (existingProject != null) {
            return "Deleted";
        }else {
            return "Project with id: "+id +" does not exist";
        }
    }


    @Override
    public Project editProject(Project updatedProject) {
        Project existingProject = projectRepo.findById(updatedProject.getId()).orElse(null);
        if (existingProject != null) {
            existingProject.setName(updatedProject.getName());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setProjectId(updatedProject.getProjectId());
            existingProject.setShowProject(updatedProject.getShowProject());
            existingProject.setMeeting_url(updatedProject.getMeeting_url());
            existingProject.setEnd_date(updatedProject.getEnd_date());
            existingProject.setStart_date(updatedProject.getStart_date());
            existingProject.setManagerEmail(updatedProject.getManagerEmail());
            existingProject.setManager(updatedProject.getManager());
            return projectRepo.save(existingProject);
        }
        return null;
    }

    @Override
    public List<Project> getProjectByMangerEmail(String email) {
        return projectRepo.findByManagerEmail(email);
    }
}
