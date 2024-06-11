package com.clinpride.SecurityPostgres.Task.controllers.request;

import lombok.Data;

@Data
public class CustomerErrorMessage {
    private String message;
    public CustomerErrorMessage(String message) {
        this.message = message;
    }
}
