package com.pany.adv.advtask.service.security;

import com.pany.adv.advtask.domain.Roles;
import com.pany.adv.advtask.domain.User;
import com.pany.adv.advtask.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


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
        List<Roles> roles = new ArrayList<>();
        roles.addAll(roles);
        return UserModelBuilder.anUserModel().withUsername(user.getLogin()).withPassword(user.getPassword())
                .withRoles(roles).withIsAccountNonExpired(true).withIsAccountNonLocked(true).withIsEnabled(true)
                .withIsCredentialsNonExpired(true).build();
    }


}
