package pl.glownia.pamela.FriendlyPetClinic.pet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.petOwner.PetOwnerEntity;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import java.util.Date;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class PetDto {
    private final long petId;

    private final String name;

    private final Date dateOfBirth;

    private final String type;

    private final PetOwnerEntity owner;

    private final Set<VisitEntity> visits;
}