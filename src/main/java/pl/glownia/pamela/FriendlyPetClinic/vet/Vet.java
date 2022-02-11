package pl.glownia.pamela.FriendlyPetClinic.vet;

import pl.glownia.pamela.FriendlyPetClinic.pet.Pet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Vet")
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String kindOfVisit;
    @OneToMany(mappedBy = "vet")
    private List<Pet> pets;

    public Vet() {
    }

    public Vet(String firstName, String lastName, String email, String phoneNumber, String kindOfVisit, List<Pet> pets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.kindOfVisit = kindOfVisit;
        this.pets = pets;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getKindOfVisit() {
        return kindOfVisit;
    }

    public List<Pet> getPets() {
        return new ArrayList<>(pets);
    }
}