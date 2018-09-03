package com.targaryen.octopus.config;


import com.targaryen.octopus.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailService userDetailService;

    @Autowired
    public void setUserDetailService(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/applicant/**").hasRole("APPLICANT")
                .antMatchers("/hr/**").hasRole("HR")
                .antMatchers("/interviewer/**").hasRole("INTERVIEWER")
                .antMatchers("/dpt/**").hasRole("DPT")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/loginCheck").failureUrl("/login-error")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
