package pl.glownia.pamela.FriendlyPetClinic.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.glownia.pamela.FriendlyPetClinic.petOwner.PetOwnerEntity;
import pl.glownia.pamela.FriendlyPetClinic.petOwner.PetOwnerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PetOwnerDetailsService implements UserDetailsService {

    private final PetOwnerRepository petOwnerRepository;

    @Autowired
    public PetOwnerDetailsService(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PetOwnerEntity petOwner = petOwnerRepository.findByEmail(email);
        if (petOwner == null) {
            throw new UsernameNotFoundException("No user found with email: " + email + ". Try again or contact with administrator.");
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialNonExpired = true;
        boolean accountNonLocked = true;
        List<? extends GrantedAuthority> authorities = getAuthorities(petOwner);
        return new ClinicUser(authorities, petOwner.getEmail(), petOwner.getPassword(), enabled, accountNonExpired, credentialNonExpired, accountNonLocked);

    }

    public List<? extends GrantedAuthority> getAuthorities(PetOwnerEntity petOwnerDto) {
        return petOwnerDto.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }
}