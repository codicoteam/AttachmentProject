package com.clinpride.SecurityPostgres.InviteUser.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@Document(collection = "InviteUser")
public class InviteUserModel {
    @Id
    private String id;
    private String message;
    private String invitedEmail;
    private String invitingEmail;
    private String invitingUserName;
    private Boolean showInvite=true;
    private Boolean booleanInvite =false;
    private String dateNow;
    private String invitedTime;
    public InviteUserModel() {
        this.dateNow = LocalDate.now().toString();
        this.invitedTime = LocalTime.now().toString(); // Set the order time to the current time
    }

}
