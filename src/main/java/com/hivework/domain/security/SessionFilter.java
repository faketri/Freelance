package com.hivework.domain.security;

import com.hivework.domain.entity.session.Sessions;
import com.hivework.domain.service.auth.SessionService;
import com.hivework.domain.service.user.UserDetailsServiceIml;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class SessionFilter extends OncePerRequestFilter {

    private final SessionService sessionService;

    private final UserDetailsServiceIml userDetailsServiceIml;

    public SessionFilter(SessionService sessionService, UserDetailsServiceIml userDetailsServiceIml) {
        this.sessionService = sessionService;
        this.userDetailsServiceIml = userDetailsServiceIml;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Авторизация");
        // Получаем массив куки
        Cookie[] cookies = request.getCookies();

        // Ищем куки с именем "JSESSIONID"
        Cookie cookieSessions = null;
        if (cookies != null) {
            cookieSessions = Arrays.stream(cookies)
                    .filter(x -> x.getName().equals("JSESSIONID"))
                    .findFirst()
                    .orElse(null);
        }

        Sessions sessions = null;
        var requestSessions = request.getSession(false);


        if (cookieSessions != null) {
            sessions = sessionService.findBySessionsId(cookieSessions.getValue());
            System.out.println("КУКА " + cookieSessions.getValue());
            if (requestSessions != null) System.out.println("SESSIONS " + requestSessions.getId());
        }

        if (SecurityContextHolder.getContext().getAuthentication() != null)
            System.out.println("NOT NULL KENT");
        // Если сессия найдена и пользователь еще не аутентифицирован
        if (sessions != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try{
                // Загружаем UserDetails по имени пользователя из сессии
                UserDetails userDetails = userDetailsServiceIml.loadUserByUsername(sessions.getUsername());

                // Создаем контекст безопасности и устанавливаем в него аутентификацию
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            finally {
                // Устанавливаем атрибуты куки
                cookieSessions.setHttpOnly(true);
                cookieSessions.setSecure(true);
                response.addCookie(cookieSessions);
            }
        }


        filterChain.doFilter(request, response);
    }
}
