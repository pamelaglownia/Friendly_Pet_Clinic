package pl.glownia.pamela.FriendlyPetClinic.pet;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;
import pl.glownia.pamela.FriendlyPetClinic.petOwner.PetOwnerEntity;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import java.util.Date;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class PetDto {
    private final long petId;

    @JsonView(EntityVisibility.Public.class)
    private final String name;

    @JsonView(EntityVisibility.InternalPet.class)
    private final Date dateOfBirth;

    @JsonView(EntityVisibility.Public.class)
    private final String type;

    @JsonView(EntityVisibility.InternalPet.class)
    private final PetOwnerEntity owner;

    @JsonView(EntityVisibility.InternalPet.class)
    private final Set<VisitEntity> visits;
}