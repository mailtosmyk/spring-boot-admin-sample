package ru.freecode.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * User: Vitali_Smyk
 * Date: 1/28/14
 * Time: 6:47 PM
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
@EnableAutoConfiguration
@Configuration
public class SeverAdminApplication extends SpringBootServletInitializer {
    
	public static void main(String[] args) {
        SpringApplication.run(SeverAdminApplication.class, args);
    }
	
    @Configuration
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
            http.logout().logoutUrl("/logout");
            http.csrf().disable();
            http.authorizeRequests()
                    .antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
                    .permitAll();
            http.authorizeRequests().antMatchers("/**").authenticated();
            http.httpBasic();
        }
    }
}

