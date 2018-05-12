package com.care.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebMvcConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.queries.employers-query}")
    private String employersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/uploader/**").permitAll()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/resume").permitAll()
                    .antMatchers("/registrationConfirm").permitAll()
                    .antMatchers("/test/**").permitAll()
                    .antMatchers("/accounts/forgetPassword",
                                             "/accounts/changePassword").permitAll()
                    .antMatchers("/admin").hasAuthority("ADMIN")
                    .antMatchers("/employer").hasAuthority("EMPLOYER")
                    .antMatchers("/caregiver").hasAuthority("CAREGIVER")
                    .antMatchers("/accounts/updatePassword*",
                                             "/accounts/savePassword*")
                                    .hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                    .anyRequest().authenticated()
                    .and()
                    .csrf().disable()
                .formLogin()
                    .loginPage("/login").failureUrl("/login-error").defaultSuccessUrl("/profile")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                    .usersByUsernameQuery(employersQuery)
                    .authoritiesByUsernameQuery(rolesQuery)
                    .dataSource(dataSource)
                    .passwordEncoder(bCryptPasswordEncoder);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("users").password("password").roles("EMPLOYER");
    }
}
