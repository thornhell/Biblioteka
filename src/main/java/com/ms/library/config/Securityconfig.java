package com.ms.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("user_name")
                .passwordParameter("user_password")
                .and()
                .logout()
                .logoutUrl("/wyloguj")
                .logoutSuccessUrl("/wyloguj")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.jdbcAuthentication()
                .dataSource(dataSource)
                //.withDefaultSchema()
                .usersByUsernameQuery(
                        "select librarytableuser.user_name as username, librarytableuser.user_password as password, librarytableuser.enabled as enabled from librarytableuser where librarytableuser.user_name=?")
                .authoritiesByUsernameQuery(
                        "select librarytablerole.user_name as username, librarytablerole.role_name as role from librarytablerole where librarytablerole.user_name=?");
    }
}
