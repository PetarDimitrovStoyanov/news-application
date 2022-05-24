package com.newsAapplicationMicroservice.authmicroservice.config;

import com.newsAapplicationMicroservice.authmicroservice.enums.RoleEnum;
import com.newsAapplicationMicroservice.authmicroservice.util.Api;
import com.newsAapplicationMicroservice.authmicroservice.util.token.JwtRequestFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private JwtRequestFilter jwtRequestFilter;

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
                .authorizeRequests()

                .antMatchers(Api.PREFIX + Api.LOGIN)
                .permitAll()

                .antMatchers(Api.PREFIX + Api.REGISTER)
                .permitAll()

                .antMatchers(Api.PREFIX + Api.LOGOUT)
                .hasAuthority(RoleEnum.CREATOR.name())

                .antMatchers(Api.PREFIX + Api.USERS + "/**")
                .hasAuthority(RoleEnum.CREATOR.name())

                .anyRequest().authenticated()

                //ADDED FILTER FOR TOKEN - EACH REQUEST:
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
