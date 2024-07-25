package com.igriss.AkkSell.controllers.user_endpoints;

import com.igriss.AkkSell.dtos.requests.ChangePasswordRequest;
import com.igriss.AkkSell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser){
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

}