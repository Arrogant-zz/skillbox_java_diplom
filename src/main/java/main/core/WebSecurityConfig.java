package main.core;

import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        httpSecurity
                .csrf()
                    .disable()
                .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/auth/login", "/api/auth/register").not().authenticated()
                .antMatchers(
                        "/api/post/my",
                        "/api/profile/my",
                        "/api/statistics/my",
                        "/api/post/like",
                        "/api/post/dislike",
                        "/api/auth/logout"
                ).authenticated()
                .antMatchers(HttpMethod.POST,
                        "/api/post",
                        "/api/image",
                        "/api/comment"
                ).authenticated()
                .antMatchers(HttpMethod.PUT, "/api/post/**").authenticated()
                .antMatchers("/api/post/moderation", "/api/moderation").hasRole("MODERATOR")
                .antMatchers(HttpMethod.PUT, "/api/settings").hasRole("MODERATOR")
                .antMatchers("/api/**").permitAll()
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
