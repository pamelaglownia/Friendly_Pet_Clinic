package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class PetOwnerDto {
    private final long petOwnerId;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phoneNumber;

    private final String address;

    private final List<PetEntity> pets;

}