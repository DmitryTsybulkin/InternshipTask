package com.pany.adv.advtask.service.security;

import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.exceptions.ResourceNotFound;
import com.pany.adv.advtask.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;


@Service
public class SecurityDetails implements UserDetailsService {

    private final UserRep userRep;

    @Autowired
    public SecurityDetails(UserRep userRep) {
        this.userRep = userRep;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRep.findUserByLogin(s).orElseThrow(() -> new UsernameNotFoundException("User: " + s + " was not found."));
        return UserDetailsImplBuilder.anUserModel().withUsername(user.getLogin()).withPassword(user.getPassword())
                .withRoles(Collections.singletonList(user.getRole()))
                .withIsAccountNonExpired(true)
                .withIsAccountNonLocked(true)
                .withIsEnabled(true)
                .withIsCredentialsNonExpired(true).build();
    }

    public Optional<UserDetailsImpl> findById(long id) {
        User user = userRep.findUserById(id).orElseThrow(ResourceNotFound::new);
        return Optional.of(UserDetailsImplBuilder.anUserModel().withUsername(user.getLogin()).withPassword(user.getPassword())
                .withRoles(Collections.singletonList(user.getRole()))
                .withIsAccountNonExpired(true)
                .withIsAccountNonLocked(true)
                .withIsEnabled(true)
                .withIsCredentialsNonExpired(true).build());
    }

}
