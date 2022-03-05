package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.glownia.pamela.FriendlyPetClinic.model.Person;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "pet_owners")
@Getter
@Setter
@NoArgsConstructor

public class PetOwnerEntity extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<PetEntity> pets = new ArrayList<>();

    public PetOwnerEntity(String firstName, String lastName, String email, String phoneNumber, String address, List<PetEntity> pets) {
        super(firstName, lastName, email, phoneNumber);
        this.address = address;
        this.pets = pets;
    }

    public void addPet(PetEntity petEntity) {
        petEntity.setOwner(this);
        pets.add(petEntity);
    }

    public List<PetEntity> getPets() {
        return new ArrayList<>(pets);
    }
}