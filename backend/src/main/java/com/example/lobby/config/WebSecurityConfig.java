package com.example.lobby.config;

import com.example.lobby.repo.PlayerRepository;
import com.example.lobby.security.DatabaseAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .formLogin()
            .loginPage("/index.html")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/lobby.html")
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/index.html")
            .permitAll()
            .and()
            .authorizeRequests()
            .antMatchers( "/api-docs").permitAll()
            .antMatchers( "/swagger-ui.html").permitAll()
            .antMatchers( "/v1/**").permitAll()
            .antMatchers("/js/**", "/lib/**", "/images/**", "/css/**", "/index.html", "/").permitAll()
            .antMatchers("/websocket").hasRole("ADMIN")
            .anyRequest().authenticated();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(PlayerRepository playerRepository){
        return new DatabaseAuthenticationProvider(playerRepository);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, AuthenticationProvider authenticationProvider) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

}
