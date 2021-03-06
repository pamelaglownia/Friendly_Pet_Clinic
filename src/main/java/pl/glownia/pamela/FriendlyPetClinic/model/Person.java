package pl.glownia.pamela.FriendlyPetClinic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "First name is required.")
    @Size(min = 3)
    @JsonView(EntityVisibility.Public.class)
    protected String firstName;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Last name is required.")
    @Size(min = 3)
    @JsonView(EntityVisibility.Public.class)
    protected String lastName;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email.")
    protected String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    @Size(min = 8, message = "Password should have minimum 8 characters.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String matchingPassword;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 7)
    protected String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Enumerated(EnumType.STRING)
    private ClinicUserRole role;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}