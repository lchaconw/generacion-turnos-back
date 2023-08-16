package com.reservas.controller;

import com.reservas.models.dto.LoginRequest;
import com.reservas.models.dto.LoginResponse;
import com.reservas.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping()
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        boolean login = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
        loginResponse.setLogin(login);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
