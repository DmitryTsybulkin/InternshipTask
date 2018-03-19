package com.pany.adv.advtask.service.security;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class SecurityDetails implements UserDetailsService {

    private final UserRep userRep;

    @Autowired
    public SecurityDetails(UserRep userRep) {
        this.userRep = userRep;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRep.findUserByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException("User: " + s + " was not found.");
        }
        return UserDetailsImplBuilder.anUserModel().withUsername(user.getLogin()).withPassword(user.getPassword())
                .withRoles(Collections.singletonList(user.getRole()))
                .withIsAccountNonExpired(true)
                .withIsAccountNonLocked(true)
                .withIsEnabled(true)
                .withIsCredentialsNonExpired(true).build();
    }


}
