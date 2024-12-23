package com.shimokawa.task_management.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  /** PasswordEncorder */
  private final PasswordEncoder passwordEncoder;
  
  /** UserDetailsService */
  private final UserDetailsService userDetailsService;
  
  /** MessageSource */
  private final MessageSource messageSource;
  
  /** ユーザー名のname属性 */
  private final String USERNAME_PARAMETER = "mailAddress";

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http
      .authorizeHttpRequests(
        authorize -> authorize.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                              .requestMatchers("/").permitAll()
                              .requestMatchers("/login").permitAll()
                              .requestMatchers("/signup").permitAll()
                              .requestMatchers("/top").permitAll()
                              .anyRequest().authenticated()
      )
      .formLogin(
        login -> login.loginPage("/login") // LoginControllerのviewメソッドを呼び出す
              .usernameParameter(USERNAME_PARAMETER) // ログイン画面のメールアドレスを取得
              .defaultSuccessUrl("/menu", true) // ログイン成功時のリダイレクト先
              .failureUrl("/login?error=true")
      )
      .logout(
        logout -> logout.logoutSuccessUrl("/").permitAll()
      );
    
    return http.build();
  }

  @Bean
  AuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    provider.setMessageSource(messageSource);
    
    return provider;
  }
}
