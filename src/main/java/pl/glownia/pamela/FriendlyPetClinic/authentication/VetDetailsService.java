package pl.glownia.pamela.FriendlyPetClinic.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.glownia.pamela.FriendlyPetClinic.vet.VetEntity;
import pl.glownia.pamela.FriendlyPetClinic.vet.VetRepository;

import java.util.List;
import java.util.stream.Collectors;

public class VetDetailsService implements UserDetailsService {
    private final VetRepository vetRepository;

    @Autowired
    public VetDetailsService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        VetEntity vet = vetRepository.findByEmail(email);
        if (vet == null) {
            throw new UsernameNotFoundException("No user found with email: " + email + ". Try again or contact with administrator.");
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialNonExpired = true;
        boolean accountNonLocked = true;
        List<? extends GrantedAuthority> authorities = getAuthorities(vet);
        return new ClinicUser(authorities, vet.getEmail(), vet.getPassword(), enabled, accountNonExpired, credentialNonExpired, accountNonLocked);

    }

    public List<? extends GrantedAuthority> getAuthorities(VetEntity vet) {
        return vet.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }
}