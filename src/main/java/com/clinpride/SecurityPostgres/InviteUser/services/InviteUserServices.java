package com.clinpride.SecurityPostgres.InviteUser.services;

import com.clinpride.SecurityPostgres.InviteUser.models.InviteUserModel;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface InviteUserServices {
    String createInvite(InviteUserModel inviteUser);
    List<InviteUserModel> getByInvitingEmail(String Email);
    List<InviteUserModel> getAllInvites();
    boolean deleteManyByIds(List<String> packageIds);
}
