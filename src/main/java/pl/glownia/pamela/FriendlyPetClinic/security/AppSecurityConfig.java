package pl.glownia.pamela.FriendlyPetClinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static pl.glownia.pamela.FriendlyPetClinic.model.ClinicUserRole.ADMIN;
import static pl.glownia.pamela.FriendlyPetClinic.model.ClinicUserRole.USER;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private static final String OPENING_HOURS_URL = "/clinic/opening-hours";
    private static final String PET_OWNERS_PAGE = "/clinic/pet-owners/**";

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(OPENING_HOURS_URL).permitAll()
                .antMatchers(PET_OWNERS_PAGE).hasRole(USER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll();
    }

    // example in memory database for testing
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails harry = User.builder()
                .username("harry")
                .password(passwordEncoder.encode("pass"))
                .roles(USER.name())
                .build();
        UserDetails mary = User.builder()
                .username("mary")
                .password(passwordEncoder.encode("pass"))
                .roles(ADMIN.name())
                .build();
        return new InMemoryUserDetailsManager(harry, mary);
    }
}