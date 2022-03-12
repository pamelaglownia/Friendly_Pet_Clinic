package pl.glownia.pamela.FriendlyPetClinic.visit;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;
import pl.glownia.pamela.FriendlyPetClinic.vet.VetEntity;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@JsonView(EntityVisibility.Public.class)
public class VisitDto {

    private final long visitId;

    private final VisitType visitType;

    private final LocalDateTime dateOfVisit;

    private final PetEntity pet;

    private final VetEntity vet;
}