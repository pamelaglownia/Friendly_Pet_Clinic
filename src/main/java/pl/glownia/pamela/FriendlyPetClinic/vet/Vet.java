package pl.glownia.pamela.FriendlyPetClinic.vet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.pet.Pet;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "vets")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "First name is required.")
    @Size(min = 3)
    private String firstName;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Last name is required.")
    @Size(min = 3)
    private String lastName;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email.")
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 7)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Kind of visit should be determined.")
    private String kindOfVisit;

    @OneToMany(mappedBy = "vet")
    private List<Pet> pets;

    public List<Pet> getPets() {
        return new ArrayList<>(pets);
    }
}