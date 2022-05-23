package com.new_sapplication_microservice.authmicroservice.config;

import com.new_sapplication_microservice.authmicroservice.enums.RoleEnum;
import com.new_sapplication_microservice.authmicroservice.util.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()

                .and()
                /*
                * this could be removed if the browser pop-up is not needed
                **/
                .httpBasic()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()

                .antMatchers("/**/free")
                .permitAll()

                .antMatchers(Api.PREFIX + Api.SIGN_IN)
                .permitAll()

                .antMatchers(Api.PREFIX + Api.REGISTER)
                .hasAuthority(RoleEnum.CREATOR.name())

                .antMatchers(Api.PREFIX + Api.LOGOUT)
                .permitAll()

                .antMatchers("/**/creator")
                .hasAuthority(RoleEnum.CREATOR.name())

                .antMatchers("/**/manager")
                .hasAuthority(RoleEnum.MANAGER.name())

                .antMatchers("/**/admin")
                .hasAuthority(RoleEnum.ADMIN.name())

                .antMatchers("/**/users/all")
                .hasAuthority(RoleEnum.CREATOR.name())

                .anyRequest().authenticated();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
