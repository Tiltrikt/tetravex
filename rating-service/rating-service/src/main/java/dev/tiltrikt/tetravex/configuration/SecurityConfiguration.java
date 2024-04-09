package dev.tiltrikt.tetravex.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

//  @Bean
//  public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
//    return http
//        .sessionManagement(
//            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        .formLogin(AbstractHttpConfigurer::disable)
//        .httpBasic(Customizer.withDefaults())
//        .oauth2Client(Customizer.withDefaults())
//        .csrf(AbstractHttpConfigurer::disable)
//        .cors(AbstractHttpConfigurer::disable)
//        .authorizeHttpRequests(registry -> registry
//            .requestMatchers("/v1/authentication/**").permitAll()
//            .anyRequest().authenticated())
//        .build();
//  }

//  @Bean
//  public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
//
//    SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
//    simpleClientHttpRequestFactory.s
//
//    BufferingClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(simpleClientHttpRequestFactory);
//  }
}
