package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PetOwnerDto {

    @JsonView(EntityVisibility.InternalOwner.class)
    private final long petOwnerId;

    private final String firstName;

    private final String lastName;

    @JsonView(EntityVisibility.InternalOwner.class)
    private final String email;

    @JsonView(EntityVisibility.InternalOwner.class)
    private final String phoneNumber;

    @JsonView(EntityVisibility.InternalOwner.class)
    private final String address;

    @JsonView(EntityVisibility.InternalOwner.class)
    private final List<PetEntity> pets;
}