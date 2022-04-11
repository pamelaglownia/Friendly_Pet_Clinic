package pl.glownia.pamela.FriendlyPetClinic.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ClinicUserRole {

    ADMIN("Admin"), USER("User");

    String name;

    ClinicUserRole(String name) {
        this.name = name;
    }
}
