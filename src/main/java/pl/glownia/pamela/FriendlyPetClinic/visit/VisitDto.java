package pl.glownia.pamela.FriendlyPetClinic.visit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;
import pl.glownia.pamela.FriendlyPetClinic.vet.VetEntity;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class VisitDto {

    private final long visitId;

    private final VisitType visitType;

    private final LocalDateTime dateOfVisit;

    private final PetEntity pet;

    private final VetEntity vet;
}