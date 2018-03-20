package com.pany.adv.advtask.web;

import com.pany.adv.advtask.exceptions.ResourceNotFound;
import com.pany.adv.advtask.repository.UserRep;
import com.pany.adv.advtask.service.security.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class TokenAuthService {

    private static final String AUTH_HEADER_NAME = "X-Auth-Token";

    private final TokenHandler tokenHandler;

    private final UserRep userRep;

    @Autowired
    public TokenAuthService(TokenHandler tokenHandler, UserRep userRep) {
        this.tokenHandler = tokenHandler;
        this.userRep = userRep;
    }

//    public Optional<Authentication> getAuthentication(HttpServletRequest httpServletRequest) {
//        return Optional
//                .ofNullable(httpServletRequest.getHeader(AUTH_HEADER_NAME))
//                .map(tokenHandler::extractUserId)
//                .map(item -> Long.valueOf(item.get()))
//                .map(userRep::findUserById)
//                .map(item -> item.get().getId())
//                .map(UserAuthentication::new);
//    }

}
