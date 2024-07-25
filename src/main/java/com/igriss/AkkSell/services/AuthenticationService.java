package com.igriss.AkkSell.services;

import com.igriss.AkkSell.dtos.requests.AuthenticationRequest;
import com.igriss.AkkSell.dtos.requests.RegisterRequest;
import com.igriss.AkkSell.dtos.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
