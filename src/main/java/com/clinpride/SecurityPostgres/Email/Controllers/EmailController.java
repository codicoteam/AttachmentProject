package com.clinpride.SecurityPostgres.Email.Controllers;

import com.clinpride.SecurityPostgres.Email.Services.EmailService;
import com.clinpride.SecurityPostgres.Email.models.Email;
import com.clinpride.SecurityPostgres.Task.controllers.request.CustomerErrorMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/kaributech")

public class EmailController {
    private final EmailService emailService;
    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody Email email){
        Email returnedEmail = emailService.sendEmail(email);
        if(returnedEmail !=null){
            return new ResponseEntity<>(returnedEmail, HttpStatus.CREATED);
        }else {
            CustomerErrorMessage errorMessage = new CustomerErrorMessage("Server Error try again");
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
