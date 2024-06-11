package com.clinpride.SecurityPostgres.Email.Services.ServicesImp;

import com.clinpride.SecurityPostgres.Email.Services.EmailService;
import com.clinpride.SecurityPostgres.Email.models.Email;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Override
    public Email sendEmail(Email email) {
       try {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setTo(email.getTo());
           message.setCc(email.getCc());
           message.setSubject(email.getMessage());
           message.setText(email.getMessage());
           javaMailSender.send(message);
           return email;
       }catch (Exception e){
           System.out.println(e);
           return null;
       }
    }
}
