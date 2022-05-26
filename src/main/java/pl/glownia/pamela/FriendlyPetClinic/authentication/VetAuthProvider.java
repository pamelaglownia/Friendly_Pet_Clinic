package pl.glownia.pamela.FriendlyPetClinic.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.glownia.pamela.FriendlyPetClinic.vet.VetEntity;
import pl.glownia.pamela.FriendlyPetClinic.vet.VetRepository;

@Component
public class VetAuthProvider implements AuthenticationProvider {

    private final VetRepository vetRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public VetAuthProvider(VetRepository vetRepository, PasswordEncoder passwordEncoder) {
        this.vetRepository = vetRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        VetEntity vetEntity = vetRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!(passwordEncoder.matches(password, vetEntity.getPassword()))) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}