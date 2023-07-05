package com.androsov.trackingservice.service;

import com.androsov.trackingservice.entity.User;
import com.androsov.trackingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    public User getUserFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id = (Long)( ( (Map<String, Object>)authentication.getDetails() ).get("id") );
        String username = authentication.getName();
        String authorities = getAuthoritiesAsString(authentication.getAuthorities());

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setAuthorities(authorities);
        user.setPassword("[PROTECTED]");

        return user;
    }

    public String getAuthoritiesAsString(Collection<? extends GrantedAuthority> authorities) {
        StringBuilder authoritiesBuilder = new StringBuilder();

        int count = 0;
        for (GrantedAuthority authority : authorities) {
            authoritiesBuilder.append(authority.getAuthority());

            if (count != authorities.size() - 1) {
                authoritiesBuilder.append(";");
            }

            count++;
        }

        return authoritiesBuilder.toString();
    }
}
