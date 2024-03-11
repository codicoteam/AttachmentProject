package com.clinpride.SecurityPostgres.InviteUser.conrollers;

import com.clinpride.SecurityPostgres.InviteUser.models.InviteUserModel;
import com.clinpride.SecurityPostgres.InviteUser.services.InviteUserServices;
import com.clinpride.SecurityPostgres.Quatation.Model.QuotationModel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/invite-user")
public class InviteUserController {
    private final InviteUserServices inviteUserServices;
    @PostMapping("/invite-user-map-mak")
    public String sendInvite(@RequestBody InviteUserModel inviteUserModel) {
        return inviteUserServices.createInvite(inviteUserModel);
    }
    @GetMapping("/find-By-inviting-email")
    public ResponseEntity<List<InviteUserModel>> getInvitesByInvitingEmail(@RequestParam String email) {
        List<InviteUserModel> orders = inviteUserServices.getByInvitingEmail(email);
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getAll")
    public List<InviteUserModel> getAllInvites() {
        return inviteUserServices.getAllInvites();
    }
    @DeleteMapping("/delete-invites")
    public ResponseEntity<String> deleteInvites(@RequestBody List<String> packageIds) {
        inviteUserServices.deleteManyByIds(packageIds);
        return ResponseEntity.ok("Invites deleted successfully");
    }
}
