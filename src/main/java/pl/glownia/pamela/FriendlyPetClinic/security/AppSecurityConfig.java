package pl.glownia.pamela.FriendlyPetClinic.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String OPENING_HOURS_URL = "/clinic/opening-hours";
    private static final String VETS_LIST = "/clinic/vets";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(OPENING_HOURS_URL, VETS_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}