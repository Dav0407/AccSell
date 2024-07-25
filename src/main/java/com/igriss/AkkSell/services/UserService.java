package com.igriss.AkkSell.services;

import com.igriss.AkkSell.entities.User;
import com.igriss.AkkSell.dtos.requests.ChangePasswordRequest;

import java.security.Principal;

public interface UserService {
    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    User findByEmail(String username);

    User findById(Long id);
}
