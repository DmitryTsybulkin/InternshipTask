package com.pany.adv.advtask.config;


import com.pany.adv.advtask.domain.Roles;
import com.pany.adv.advtask.service.security.SecurityDetails;
import com.pany.adv.advtask.web.TokenAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityDetails details;

    private final PasswordEncoder encoder;

    @Autowired
    public WebSecurityConfig(SecurityDetails details, PasswordEncoder encoder) {
        this.details = details;
        this.encoder = encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/requests/**", "/archive/**", "/photos/**").hasAnyRole(Roles.USER.name(), Roles.ADMIN.name(), Roles.EDITOR.name())
                .antMatchers("/municipalities/**", "/places/**", "/constructions/**").hasAnyRole(Roles.ADMIN.name(), Roles.EDITOR.name())
                .antMatchers("/users/**", "/h2-console").hasRole(Roles.ADMIN.name())

                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(details)
                .passwordEncoder(encoder);
    }
}
