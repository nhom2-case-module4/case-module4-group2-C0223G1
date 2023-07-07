package com.example.project_book.sercurity;

import com.example.project_book.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/welcome", "/welcome/login", "/welcome/logout", "welcome/register"
                ,"/css/**","/icomoon/**","/images/**","/js/**","/static/**","cart/add/{id}/{num}"
                ,"/cart/show-cart","/cart/plus/{id}","/cart/minus/{id}","/cart/delete/{id}").permitAll();
        http.authorizeRequests().antMatchers("/cart/send","welcome/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/users", "/order", "/product").access("hasRole('ROLE_ADMIN')");

//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.authorizeRequests()
                .and().formLogin()//

                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/welcome/login")//
                .defaultSuccessUrl("/welcome")//
                .failureUrl("/login?error=true")
                .usernameParameter("email")//
                .passwordParameter("password")

                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/welcome/logout").logoutSuccessUrl("/welcome/login?logout");

    }
}
