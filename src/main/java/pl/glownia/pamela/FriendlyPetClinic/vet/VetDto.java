package pl.glownia.pamela.FriendlyPetClinic.vet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class VetDto {
    private final long vetId;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phoneNumber;

    private final Set<String> specialties;

    private final Set<VisitEntity> visits;
}