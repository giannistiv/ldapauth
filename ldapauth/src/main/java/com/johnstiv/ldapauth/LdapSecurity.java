package com.johnstiv.ldapauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
@EnableWebSecurity
public class LdapSecurity extends WebSecurityConfigurerAdapter {


    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.ldapAuthentication()
                .contextSource().url("ldap://minedu.gov.gr:389/dc=minedu,dc=gov,dc=gr")
                .managerDn("uid=digisignsa@minedu").managerPassword("j#5A2U!fdqQ=AgM.")
                .and()
                .userSearchBase("ou=users")
                .userSearchFilter("(cn={0})");
    }
}
