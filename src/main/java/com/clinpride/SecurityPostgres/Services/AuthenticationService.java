package com.clinpride.SecurityPostgres.Services;

import com.clinpride.SecurityPostgres.Config.Auth.AuthenticationResponse;
import com.clinpride.SecurityPostgres.Config.JwtService;
import com.clinpride.SecurityPostgres.UserRepo.UserRepository;
import com.clinpride.SecurityPostgres.models.AppUser;
import com.clinpride.SecurityPostgres.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender javaMailSender;


    public AuthenticationResponse register(AppUser appUser) {
        Optional<AppUser> existingUser = repository.findByEmail(appUser.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        else {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(appUser.getEmail());
            message.setSubject("Welcome to Mapmak Furniture - Thank You for Joining!");
            message.setText(" Dear " + appUser.getFirstName()+ "\n" +
                    "\n" +
                    "Thank you for joining Mapmak Furniture! We're thrilled to have you as part of our online furniture shopping " +
                    "community.\n Discover a wide range of exquisite furniture pieces designed to transform your living spaces into personalized havens.\n" +
                    " Enjoy a seamless shopping experience, personalized recommendations, exclusive offers, and dedicated customer support. " +
                    "We value your trust and look forward to providing you with exceptional service.\n " +
                    "Start exploring our collection at mapmak.com and create the home of your dreams. For any assistance, reach out to our support team at [Support Email Address]. Welcome to Mapmak Furniture!");
            javaMailSender.send(message);


            var user = appUser.builder()
                    .firstName(appUser.getFirstName())
                    .lastName(appUser.getLastName())
                    .email(appUser.getEmail())
                    .authenticateUser(appUser.getAuthenticateUser())
                    .phoneNumber(appUser.getPhoneNumber())
                    .showUser(appUser.getShowUser())
                    .password(passwordEncoder.encode(appUser.getPassword()))
                    .role(Role.USER)
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }
    }

    public AuthenticationResponse authenticate(AppUser appUser) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    appUser.getEmail(),
                    appUser.getPassword()
            )
    );
            var user = repository.findByEmail(appUser.getEmail())
                    .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
