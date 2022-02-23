package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.model.Person;

import javax.persistence.*;

@Entity(name = "pet_owners")
@Getter
@NoArgsConstructor
public class PetOwnerEntity extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    public PetOwnerEntity(String firstName, String lastName, String email, String phoneNumber, String address) {
        super(firstName, lastName, email, phoneNumber);
        this.address = address;
    }
}