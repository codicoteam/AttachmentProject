package com.clinpride.SecurityPostgres.Project.model;

import com.clinpride.SecurityPostgres.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "Project")
public class Project {
    @Id
    private String id;
    private String name;
    private String description;
    private String start_date;
    private String end_date;
    private String meeting_url;
    private String manager;
    private Boolean showProject;
    private String managerEmail;
    private String projectId;
    public Project() {
        String randomDigits = String.format("%06d", (int) (Math.random() * 1000000));
        this.projectId= "PRO-" + randomDigits;
    }
}



