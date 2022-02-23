package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PetOwnerDto {
    private final long petOwnerId;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phoneNumber;

    private final String address;
}