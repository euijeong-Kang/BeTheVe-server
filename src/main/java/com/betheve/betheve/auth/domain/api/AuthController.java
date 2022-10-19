package com.betheve.betheve.auth.domain.api;

import com.betheve.betheve.auth.domain.entity.dto.LoginRequestDto;
import com.betheve.betheve.auth.domain.service.AuthService;
import com.betheve.betheve.member.domain.entity.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final LoginRequestDto loginRequestDto) {

        return new ResponseEntity<>(authService.login(loginRequestDto), HttpStatus.OK);
    }

    @Operation
    @PostMapping()
    public ResponseEntity<?> signUp(@RequestBody final MemberDto memberDto) {

        return new ResponseEntity<>(authService.signup(memberDto), HttpStatus.OK);
    }
}
