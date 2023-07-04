package com.androsov.trackingservice.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private final String USERNAME_HEADER = "Username";
    private final String AUTHORITIES_HEADER = "Authorities";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String username = request.getHeader(USERNAME_HEADER);
        final String authoritiesString = request.getHeader(AUTHORITIES_HEADER);

        if (username == null || authoritiesString == null) {
            chain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    "[PROTECTED]",
                    parseAuthorities(authoritiesString)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            Logger logger = Logger.getLogger(AuthenticationFilter.class.getName());
            logger.log(Level.WARNING, ex.getMessage());
        }

        chain.doFilter(request, response);
    }

    private List<GrantedAuthority> parseAuthorities(String authoritiesString) {
        return Arrays.stream(authoritiesString.split(";"))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
