package com.clinpride.SecurityPostgres.Email.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Data
public class Email {
    private String to;
    private String cc;
    private String header;
    private String message;
}
