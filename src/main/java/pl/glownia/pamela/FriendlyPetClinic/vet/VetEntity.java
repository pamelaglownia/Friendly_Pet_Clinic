package pl.glownia.pamela.FriendlyPetClinic.vet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.model.Person;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "vets")
@Getter
@NoArgsConstructor
public class VetEntity extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @ElementCollection()
    private Set<String> specialties = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "vet")
    private Set<VisitEntity> visits = new HashSet<>();

    public VetEntity(String firstName, String lastName, String email, String phoneNumber, Set<String> specialties, Set<VisitEntity> visits) {
        super(firstName, lastName, email, phoneNumber);
        this.specialties = specialties;
        this.visits = visits;
    }

    public Set<String> getSpecialties() {
        return new HashSet<>(specialties);
    }

    public Set<VisitEntity> getVisits() {
        return new HashSet<>(visits);
    }
}