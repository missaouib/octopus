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

                .antMatchers("/octopus/css/**", "/octopus/js/**", "/octopus/fonts/**", "/octopus/pdf/**", "/octopus/new-vendor/**", "/octopus/img/**", "/octopus/", "/octopus/new/pdf", "/octopus/postDetail/**", "/octopus/pub/billboard/**", "/octopus/register", "/octopus/userRegister",
                        // Unleash WebSocket URIs from Authentication
                        "/octopus/ws/**").permitAll()
                .antMatchers("/octopus/applicant/**").hasRole("APPLICANT")
                .antMatchers("/octopus/hr/**").hasRole("HR")
                .antMatchers("/octopus/interviewer/**").hasRole("INTERVIEWER")
                .antMatchers("/octopus/dpt/**").hasRole("DPT")
                .antMatchers("/actuator/**").hasRole("MAINTENANCE")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/octopus/login").permitAll().defaultSuccessUrl("/octopus/loginCheck").failureUrl("/octopus/loginError")
                .and().logout().logoutUrl("/octopus/logout").logoutSuccessUrl("/octopus/login");

        /* Zhao: Read this if you need help */
        /* https://docs.spring.io/spring-security/site/docs/current/reference/html/headers.html#headers-frame-options */

        http
                .csrf()
                // Unleash WebSocket Endpoint from CSRF protection: Ignore our stomp endpoints since they are protected using Stomp headers
                .ignoringAntMatchers("/octopus/ws/**")
                .ignoringAntMatchers("/octopus/applicant/uploadFile/**")
                .ignoringAntMatchers("/octopus/pdf/**")
                .ignoringAntMatchers("/octopus/applicant/resume/pdf/**")
                .and()
                .headers()
                .frameOptions()
                .sameOrigin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
