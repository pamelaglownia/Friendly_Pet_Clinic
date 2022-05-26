package pl.glownia.pamela.FriendlyPetClinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import pl.glownia.pamela.FriendlyPetClinic.authentication.PetOwnerAuthProvider;
import pl.glownia.pamela.FriendlyPetClinic.authentication.VetAuthProvider;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private static final String OPENING_HOURS_URL = "/clinic/opening-hours";
    private PetOwnerAuthProvider petOwnerAuthProvider;
    private VetAuthProvider vetAuthProvider;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder, PetOwnerAuthProvider petOwnerAuthProvider, VetAuthProvider vetAuthProvider) {
        this.passwordEncoder = passwordEncoder;
        this.petOwnerAuthProvider = petOwnerAuthProvider;
        this.vetAuthProvider = vetAuthProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(OPENING_HOURS_URL).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(petOwnerAuthProvider);
        auth.authenticationProvider(vetAuthProvider);
    }
}