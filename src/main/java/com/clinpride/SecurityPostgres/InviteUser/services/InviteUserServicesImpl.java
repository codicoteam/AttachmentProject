package com.clinpride.SecurityPostgres.InviteUser.services;

import com.clinpride.SecurityPostgres.InviteUser.models.InviteUserModel;
import com.clinpride.SecurityPostgres.InviteUser.repository.InviteUserRepo;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InviteUserServicesImpl implements InviteUserServices {
    private final JavaMailSender javaMailSender;

    private final InviteUserRepo inviteUserRepo;

    @Override
    public String createInvite(InviteUserModel inviteUser) {
        try {

            inviteUserRepo.save(inviteUser);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(inviteUser.getInvitedEmail());
            message.setSubject("Invitation to MapMak Furnitures");
            message.setText("Dear User,\n\n" +
                    "This Email has been send to you by "+inviteUser.getInvitingUserName()+ ",\n"+
                    "You have been invited to try MapMak Furnitures, a web-based system for furniture management. We believe that you will find it useful for your furniture-related tasks.\n\n" +
                    "To get started, please visit the following link:\n" +
                    "https://mapmak.co.zw\n\n" +
                    "If you have any questions or need assistance, feel free to reach out to our support team.\n\n" +
                    "Thank you for considering MapMak Furnitures!\n\n");
            javaMailSender.send(message);

            return "Invitation Send Successfully";
        } catch (Exception e) {
            return "Order saving failed";
        }
    }

    @Override
    public List<InviteUserModel> getByInvitingEmail(String Email) {
        return inviteUserRepo.findByInvitingEmail(Email);
    }

    @Override
    public List<InviteUserModel> getAllInvites() {
        return inviteUserRepo.findAll();
    }

    @Override
    public boolean deleteManyByIds(List<String> packageIds) {
        try {
            inviteUserRepo.deleteByIdIn(packageIds);
            return true; // Deletion successful
        } catch (Exception e) {
            return false; // Deletion failed
        }
    }
}
