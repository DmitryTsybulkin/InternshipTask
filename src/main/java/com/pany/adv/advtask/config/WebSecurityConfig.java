package com.pany.adv.advtask.config;


import com.pany.adv.advtask.service.security.SecurityDetails;
import com.pany.adv.advtask.service.security.SecurityEncoder;
import com.pany.adv.advtask.web.StatelessAuthFilter;
import com.pany.adv.advtask.web.TokenAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityDetails details;

    private final SecurityEncoder encoder;

    private final TokenAuthService tokenAuthService;

    @Autowired
    public WebSecurityConfig(SecurityDetails details, SecurityEncoder encoder, TokenAuthService tokenAuthRep) {
        this.details = details;
        this.encoder = encoder;
        this.tokenAuthService = tokenAuthRep;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //.addFilterBefore(new StatelessAuthFilter(tokenAuthService), UsernamePasswordAuthenticationFilter.class)

                .authorizeRequests()

                .antMatchers("/").hasRole("ADMIN")
                .antMatchers().hasRole("EDITOR") //здесь не должно быть куда хочет админ
                .antMatchers().hasRole("USER") //здесь не должно быть куда хочет админ и едитор

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
