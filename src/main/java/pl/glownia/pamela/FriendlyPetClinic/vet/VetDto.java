package pl.glownia.pamela.FriendlyPetClinic.vet;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class VetDto {
    @JsonView(EntityVisibility.Public.class)
    private final long vetId;

    @JsonView(EntityVisibility.Public.class)
    private final String firstName;

    @JsonView(EntityVisibility.Public.class)
    private final String lastName;

    @JsonView(EntityVisibility.InternalVet.class)
    private final String email;

    private final String password;
    private final String matchingPassword;

    @JsonView(EntityVisibility.InternalVet.class)
    private final String phoneNumber;

    @JsonView(EntityVisibility.Public.class)
    private final Set<String> specialties;

    @JsonView(EntityVisibility.InternalVet.class)
    private final Set<VisitEntity> visits;
}