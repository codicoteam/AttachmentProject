package com.clinpride.SecurityPostgres.Email.Services;

import com.clinpride.SecurityPostgres.Email.models.Email;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public Email sendEmail(Email email);
}

