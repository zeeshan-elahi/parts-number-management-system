package com.partsnumbersystem.app.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.InetOrgPersonContextMapper;

@Configuration
@EnableWebSecurity
public class LdapSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    AppGlobalProperties appGlobalProperties;

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                        .antMatchers("/assets/**").permitAll()
                        .anyRequest()
                        .fullyAuthenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                        .successForwardUrl("/processlogin")
                        .and()
                    .logout()
                        .permitAll()
                        .and()
                    .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        try {

            System.out.println(
                    "WebSecurityConfig configure(AuthenticationManagerBuilder auth)");

            authenticationManagerBuilder
                    .ldapAuthentication()
                    .userDetailsContextMapper(inetOrgPersonContextMapper())
                    .userSearchFilter(appGlobalProperties.getLdapUserSearchFilter())
                    .userSearchBase(appGlobalProperties.getLdapUserSearchBase())
                    .groupSearchBase(appGlobalProperties.getLdapGroupSearchBase())
                    .groupSearchFilter(appGlobalProperties.getLdapGroupSearchFilter())
                    .contextSource()
                    .url(appGlobalProperties.getLdapUrl())
                    .port(appGlobalProperties.getLdapPort())
                    .managerDn(appGlobalProperties.getLdapBaseDn())
                    .managerPassword(appGlobalProperties.getLdapBasePassword());

            /*authenticationManagerBuilder
                    .ldapAuthentication()
                    .userDetailsContextMapper(inetOrgPersonContextMapper())
                    .userSearchFilter(appGlobalProperties.getLdapUserSearchFilter())
                    .userSearchBase(appGlobalProperties.getLdapUserSearchBase())
                    .groupSearchBase(appGlobalProperties.getLdapGroupSearchBase())
                    .groupSearchFilter(appGlobalProperties.getLdapGroupSearchFilter())
                    .contextSource()
                    .url(appGlobalProperties.getLdapUrl())
                    .port(appGlobalProperties.getLdapPort());*/

        }catch(Exception ex){
            System.out.println(ex);
        }

    }

    @Bean
    public InetOrgPersonContextMapper inetOrgPersonContextMapper(){
        return new InetOrgPersonContextMapper();
    }

}
