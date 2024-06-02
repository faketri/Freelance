package com.hivework.domain.service.auth;

import com.hivework.domain.dto.request.SingInRequest;
import com.hivework.domain.dto.request.SingUpRequest;
import com.hivework.domain.dto.response.UserResponse;
import com.hivework.domain.entity.session.Sessions;
import com.hivework.domain.entity.user.ERole;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.mapper.UsersMapper;
import com.hivework.domain.service.user.UserDetailsServiceIml;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDetailsServiceIml userDetailsServiceIml;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SessionService sessionService;

    public AuthService(UserDetailsServiceIml userDetailsServiceIml, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       SessionService sessionService) {
        this.userDetailsServiceIml = userDetailsServiceIml;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.sessionService = sessionService;
    }

    public UserResponse singUp(SingUpRequest singUpRequest, HttpSession session){
        Users users = new Users();

        users.setLogin(singUpRequest.getLogin());
        users.setEmail(singUpRequest.getEmail());
        users.setPassword(passwordEncoder.encode(singUpRequest.getPassword()));
        users.getRoles().add(ERole.CUSTOMER);

        users = userDetailsServiceIml.getUserService().save(users);

        session.setAttribute("username", users.getLogin());

        Sessions sessions = sessionService.findByUsername(users.getLogin());

        if(sessions == null){
            sessions = new Sessions();
            sessions.setSessionId(session.getId());
            sessions.setUsername(users.getLogin());
        }

        sessionService.save(sessions);

        return UsersMapper.toResponse(users);
    }

    public UserResponse singIn(SingInRequest singInRequest, HttpSession session){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        singInRequest.getLogin(),
                        singInRequest.getPassword(),
                        null
                ));

        final Users users = userDetailsServiceIml.getUserService().findByLogin(singInRequest.getLogin());

        session.setAttribute("username", users.getBalance());

        Sessions sessions = sessionService.findByUsername(users.getLogin());

        if(sessions == null){
            sessions = new Sessions();
            sessions.setSessionId(session.getId());
            sessions.setUsername(users.getLogin());
        }

        sessionService.save(sessions);

        return UsersMapper.toResponse(users);
    }
}
