package com.arms.config;

import com.arms.domain.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@ComponentScan("com.concretepage")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    
	@Autowired
    AuthenticationService authenticationService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        auth.userDetailsService(authenticationService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .and().authorizeRequests().antMatchers("/static/**","/javascripts/**", "/images/**", "/**/favicon.ico","/fonts/**","/stylesheets/**").permitAll()
            .and().authorizeRequests().antMatchers("/", "/user/login","/user/signUp", "/user/add", "/help", "/contact", "/about").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf()
            .disable()
            .formLogin()
            .loginPage("/user/login")
            .defaultSuccessUrl("/user/successLogin", true)            
      	  	.failureUrl("/user/failLogin")
      	  	.usernameParameter("email")
      	  	.passwordParameter("password")
      	  	.permitAll()
      	  	.and()
      	  	.logout()
      	  	.logoutUrl("/user/logout")
      	  	.logoutSuccessUrl("/")
      	  	.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"));
  }
}
