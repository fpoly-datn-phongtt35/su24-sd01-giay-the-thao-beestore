package com.example.datnsum24sd01.security.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@Order(2)
@EnableWebSecurity
public class CustomerSecurityConfig {
    @Bean
    public UserDetailsService userDetailsServiceCustomer(){
        return new CustomKhachHangDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoderCustomer(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider providerCustomer(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceCustomer());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoderCustomer());
        return daoAuthenticationProvider;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandlerCustomer() {
        return new CustomerAccessDeniedHandler();
    }

    @Bean

    public SecurityFilterChain filterChainCustomer(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                        rq ->
                                rq.requestMatchers("/beestore/dang-ky").permitAll()
                                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                )
                .formLogin(
                        f-> f.loginPage("/beestore/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .loginProcessingUrl("/user/login")
                                .defaultSuccessUrl("/beestore/trang-chu")
                                .permitAll()
                )
                .authenticationProvider(providerCustomer())
                .exceptionHandling(
                        ex -> ex.accessDeniedHandler(accessDeniedHandlerCustomer())
                )
        ;
        return http.build();
    }

}
