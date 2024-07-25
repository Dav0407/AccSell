package com.igriss.AkkSell.service_impl;

import com.igriss.AkkSell.entities.User;
import com.igriss.AkkSell.repositories.UserRepository;
import com.igriss.AkkSell.dtos.requests.ChangePasswordRequest;
import com.igriss.AkkSell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        //check if users password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        //check if the confirmation password matches with the new password
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new BadCredentialsException("Passwords does not match");
        }
        //update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        //save user back to database
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
