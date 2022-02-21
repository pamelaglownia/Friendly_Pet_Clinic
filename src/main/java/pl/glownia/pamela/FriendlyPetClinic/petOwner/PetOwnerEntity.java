package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.model.Person;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "pet_owners")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetOwnerEntity extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    @OneToMany(mappedBy = "owner")
    private List<PetEntity> pets;

    public List<PetEntity> getPets() {
        return new ArrayList<>(pets);
    }
}