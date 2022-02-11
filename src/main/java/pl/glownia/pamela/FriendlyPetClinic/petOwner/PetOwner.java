package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import pl.glownia.pamela.FriendlyPetClinic.pet.Pet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pet_Owner")
public class PetOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private int age;
    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

    public PetOwner() {
    }

    public PetOwner(String firstName, String lastName, String email, String phoneNumber, String address, int age, List<Pet> pets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
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

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public List<Pet> getPets() {
        return new ArrayList<>(pets);
    }
}