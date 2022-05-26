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
import pl.glownia.pamela.FriendlyPetClinic.petOwner.PetOwnerEntity;
import pl.glownia.pamela.FriendlyPetClinic.petOwner.PetOwnerRepository;

@Component
public class PetOwnerAuthProvider implements AuthenticationProvider {
    private final PetOwnerRepository petOwnerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PetOwnerAuthProvider(PetOwnerRepository petOwnerRepository, PasswordEncoder passwordEncoder) {
        this.petOwnerRepository = petOwnerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        PetOwnerEntity petOwnerEntity = petOwnerRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!(passwordEncoder.matches(password, petOwnerEntity.getPassword()))) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}