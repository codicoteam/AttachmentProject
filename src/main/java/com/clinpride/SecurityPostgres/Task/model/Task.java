package com.clinpride.SecurityPostgres.Task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "Task")
public class Task {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private String description;
    private String employeeEmail;
    private String assignerName;
    private String assignerEmail;
    private Boolean status;
    private String starting_date;
    private String end_date;
    private String viewedStatus;
    private String projectId;
    private String projectName;
    private String taskId;
    public Task() {
        String randomDigits = String.format("%07d", (int) (Math.random() * 10000000));
        this.taskId= "TSK-" + randomDigits;
    }
}
