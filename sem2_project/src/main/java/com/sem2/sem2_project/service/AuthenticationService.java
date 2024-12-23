package com.sem2.sem2_project.service;

import com.sem2.sem2_project.dto.request.LoginRequest;
import com.sem2.sem2_project.dto.request.RegisterRequest;
import com.sem2.sem2_project.dto.response.LoginResponse;
import com.sem2.sem2_project.model.User;

public interface AuthenticationService {
    LoginResponse login(LoginRequest loginRequest);

    String register(RegisterRequest request);

    boolean logout(String token);

    User getCurrenAuthenticatedUser();
}
