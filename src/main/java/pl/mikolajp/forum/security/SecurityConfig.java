package pl.mikolajp.forum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    JdbcUserDetailsManager users(DataSource dataSource, PasswordEncoder passwordEncoder){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        return jdbcUserDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()
                .authorizeRequests(auth -> {
                            try {
                                auth
                                        .requestMatchers("/register*").permitAll()
                                        .requestMatchers("/signup").permitAll()
                                        .requestMatchers("/register_request").permitAll()
                                        .requestMatchers("/username").permitAll()
                                        .requestMatchers("api/users/**").permitAll()
                                        .requestMatchers("api/users").permitAll()
                                        .anyRequest().authenticated()
                                        .and()
                                        .formLogin().loginPage("/login").defaultSuccessUrl("/index.html", true).permitAll();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .build();
    }
}
