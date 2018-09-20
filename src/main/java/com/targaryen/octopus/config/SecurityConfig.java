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

                .antMatchers("/css/**",
                        "/js/**",
                        "/fonts/**",
                        "/pdf/**",
                        "/new-vendor/**",
                        "/img/**", "/",
                        "/new/pdf",
                        "/postDetail/**",
                        "/pub/billboard/**",
                        "/register",
                        "/userRegister",
                        // Unleash WebSocket URIs from Authentication
                        "/octopus/ws/**").permitAll()
                .antMatchers("/applicant/**").hasRole("APPLICANT")
                .antMatchers("/hr/**").hasRole("HR")
                .antMatchers("/interviewer/**").hasRole("INTERVIEWER")
                .antMatchers("/dpt/**").hasRole("DPT")
                .antMatchers("/actuator/**").hasRole("MAINTENANCE")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/loginCheck").failureUrl("/loginError")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        /* Zhao: Read this if you need help */
        /* https://docs.spring.io/spring-security/site/docs/current/reference/html/headers.html#headers-frame-options */

        http
                .csrf()
                // Unleash WebSocket Endpoint from CSRF protection: Ignore our stomp endpoints since they are protected using Stomp headers
                .ignoringAntMatchers("/octopus/ws/**")
                .ignoringAntMatchers("/applicant/uploadFile/**")
                .ignoringAntMatchers("/pdf/**")
                .ignoringAntMatchers("/applicant/resume/pdf/**")
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
