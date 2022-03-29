package pl.glownia.pamela.FriendlyPetClinic.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String OPENING_HOURS_URL = "/clinic/opening-hours";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(OPENING_HOURS_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}