package pl.glownia.pamela.FriendlyPetClinic.petOwner;

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

@Entity(name = "pet_owners")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class PetOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "First name is required.")
    private String firstName;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Last name is required.")
    private String lastName;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email.")
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 7)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

    public List<Pet> getPets() {
        return new ArrayList<>(pets);
    }
}