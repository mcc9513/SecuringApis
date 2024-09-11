package org.example.securingapis.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private UserAuthProvider userAuthProvider;

    public JwtAuthFilter(UserAuthProvider userAuthProvider) {
        this.userAuthProvider = userAuthProvider;
    }

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith("Bearer ")) {
            try {
                String token = header.split(" ")[1];
                SecurityContextHolder.getContext().setAuthentication(
                        userAuthProvider.validateToken(token)
                );
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}