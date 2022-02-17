package pl.glownia.pamela.FriendlyPetClinic.vet;

import pl.glownia.pamela.FriendlyPetClinic.pet.Pet;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "vets")
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
    @Size(min =7)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Kind of visit should be determined.")
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