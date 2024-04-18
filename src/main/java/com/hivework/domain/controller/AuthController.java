package com.hivework.domain.controller;

import com.hivework.domain.dto.request.SingInRequest;
import com.hivework.domain.dto.request.SingUpRequest;
import com.hivework.domain.dto.response.UserResponse;
import com.hivework.domain.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/sing-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse singUp(@RequestBody SingUpRequest singUpRequest, HttpSession session){
        System.out.println("auth");
        return authService.singUp(singUpRequest, session);
    }

    @RequestMapping(value = "/sing-in", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse singIn(@RequestBody SingInRequest singInRequest, HttpSession session){
        System.out.println("auth");
        return authService.singIn(singInRequest, session);
    }
}
