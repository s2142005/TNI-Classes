package com.arms.domain.service;

import com.arms.domain.entity.User;
import com.arms.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(email);
        GrantedAuthority authority = new SimpleGrantedAuthority("emitter");

        boolean isEnabled = true;
        boolean isAccountNonExpired = true;
        boolean isCredentialsNonExpired = true;
        boolean isAccountNonLocked = true;

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), isEnabled, isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked, Arrays.asList(authority));

        return userDetails;
    }
}
