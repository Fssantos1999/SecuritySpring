package com.felipe.auth.AuthenticatorSpring.Security;

import com.felipe.auth.AuthenticatorSpring.Model.User;
import com.felipe.auth.AuthenticatorSpring.Model.UserRepository;
import com.felipe.auth.AuthenticatorSpring.Security.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class CustomBasicAuthenticationFilter extends OncePerRequestFilter {
    private static final String BASIC_AUTH = "Basic ";

    @Autowired
    private UserRepository userRepository;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isBasicAuthentication(request)) {
            String[] credentials = decodeBase64(getHeaderValue(request).replace(BASIC_AUTH, "")).split(":");
            String username = credentials[0];
            String password = credentials[1];
            User user = userRepository.findByUsername(username);

            if (user == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }

            boolean valid = checkPassword(password, user.getPassword());

            if (!valid) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Password Incorrect");
                return;
            }

            setAuthentication(user);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(User user) {
        Authentication auth = createAuthenticationToken(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private Authentication createAuthenticationToken(User user) {
        UserPrincipal principal = new UserPrincipal(user);
        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
    }

    private boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder().matches(rawPassword, encodedPassword);
    }

    private String decodeBase64(String base64) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        return new String(decodedBytes);
    }

    private boolean isBasicAuthentication(HttpServletRequest request) {
        String header = getHeaderValue(request);
        return header != null && header.startsWith(BASIC_AUTH);
    }

    private String getHeaderValue(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}
