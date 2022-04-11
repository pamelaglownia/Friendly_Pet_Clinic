package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.glownia.pamela.FriendlyPetClinic.clinic.tools.PasswordMatcher;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;
import pl.glownia.pamela.FriendlyPetClinic.model.ClinicUserRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@PasswordMatcher
public class PetOwnerDto {

    @JsonView(EntityVisibility.InternalOwner.class)
    private final long petOwnerId;

    private final String firstName;

    private final String lastName;

    @JsonView(EntityVisibility.InternalOwner.class)
    private final String email;

    private final String password;
    private final String matchingPassword;

    @JsonView(EntityVisibility.InternalOwner.class)
    private final String phoneNumber;

    @JsonView(EntityVisibility.InternalOwner.class)
    private final String address;

    @JsonView(EntityVisibility.InternalOwner.class)
    private final List<PetEntity> pets;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Enumerated(EnumType.STRING)
    private ClinicUserRole role;
}