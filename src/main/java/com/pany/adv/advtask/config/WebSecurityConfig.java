package com.pany.adv.advtask.config;


import com.pany.adv.advtask.service.security.SecurityDetails;
import com.pany.adv.advtask.service.security.SecurityEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityDetails details;

    private final SecurityEncoder encoder;

    @Autowired
    public WebSecurityConfig(SecurityDetails details, SecurityEncoder encoder) {
        this.details = details;
        this.encoder = encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/users/**", "/h2-console/***", "/constructions/**",
                                                            "/places/**", "/municipalities/**", "/photos/**", "/archive/**")
                .hasAuthority("ADMIN").anyRequest().authenticated()

                .antMatchers("/constructions/**", "/places/**", "/municipalities/**",
                                            "/requests/**", "/archive/**", "/photos/**")
                .hasAuthority("EDITOR").anyRequest().authenticated()

                .antMatchers("/requests/**", "/archive/**", "/photos/**")
                .hasAuthority("USER").anyRequest().authenticated()

                .antMatchers("/requests", "/archive").permitAll()

                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/templates/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(details).passwordEncoder(encoder.passwordEncoder());
    }
}
