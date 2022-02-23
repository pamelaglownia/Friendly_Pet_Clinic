package pl.glownia.pamela.FriendlyPetClinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "First name is required.")
    @Size(min = 3)
    protected String firstName;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Last name is required.")
    @Size(min = 3)
    protected String lastName;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email.")
    protected String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 7)
    protected String phoneNumber;
}