package com.clinpride.SecurityPostgres.Project.controllers;
import com.clinpride.SecurityPostgres.Project.model.Project;
import com.clinpride.SecurityPostgres.Project.services.ProjectServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/kaributech")
public class Controllers {
    final ProjectServices projectServices;
    @GetMapping("/get-All")
    public List<Project> getAllProjects(){
        return projectServices.getAllProject();
    }

    @PostMapping("/create-project")
    public ResponseEntity<Project> CreateProject(@RequestBody Project project){
        Project savedProject = projectServices.createProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/delete-project")
    public String deleteProjects(@RequestParam String id){
        return projectServices.deleteProject(id);
    }

    @PostMapping("/edit-project")
    public ResponseEntity<Project> editProject(@RequestBody Project project){
        Project savedProject = projectServices.editProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @PostMapping("/manager")
    public List<Project>  findManagerProject(@RequestParam String email){
        return projectServices.getProjectByMangerEmail(email);
    }


}
