package bykov.polikek.kursach.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import baranow.polikek.kursach.model.UserAuthority;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
//                expressionInterceptUrlRegistry
//                .requestMatchers("/registration", "/login").permitAll()
//                .requestMatchers(HttpMethod.POST, "/product").hasAnyAuthority(UserAuthority.EMPLOYEE.getAuthority())
//                .requestMatchers(HttpMethod.GET, "/product/**").hasAnyAuthority(UserAuthority.EMPLOYEE.getAuthority(), UserAuthority.BUYER.getAuthority())
//                .requestMatchers(HttpMethod.PUT, "/product/**").hasAnyAuthority(UserAuthority.EMPLOYEE.getAuthority())
//                .requestMatchers(HttpMethod.DELETE, "/product/**").hasAnyAuthority(UserAuthority.EMPLOYEE.getAuthority())
//                .requestMatchers(HttpMethod.POST, "/purchase").hasAnyAuthority(UserAuthority.EMPLOYEE.getAuthority(), UserAuthority.BUYER.getAuthority())
//                .requestMatchers(HttpMethod.GET, "/purchase/**").hasAnyAuthority(UserAuthority.EMPLOYEE.getAuthority(), UserAuthority.BUYER.getAuthority())
//                .requestMatchers(HttpMethod.PUT, "/purchase/**").hasAnyAuthority(UserAuthority.EMPLOYEE.getAuthority())
//                .requestMatchers(HttpMethod.DELETE, "/purchase/**").hasAnyAuthority(UserAuthority.EMPLOYEE.getAuthority())
//                .requestMatchers(HttpMethod.POST, "/employee").hasAuthority(UserAuthority.EMPLOYEE.getAuthority())
//                .requestMatchers(HttpMethod.GET, "/employee/**").hasAuthority(UserAuthority.EMPLOYEE.getAuthority())
//                .requestMatchers(HttpMethod.PUT, "/employee/**").hasAuthority(UserAuthority.EMPLOYEE.getAuthority())
//                .requestMatchers(HttpMethod.DELETE, "/employee/**").hasAuthority(UserAuthority.EMPLOYEE.getAuthority())
//                        .anyRequest().hasAuthority(UserAuthority.FULL.getAuthority()))
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}