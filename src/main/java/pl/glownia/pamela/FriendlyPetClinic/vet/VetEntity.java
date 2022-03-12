package pl.glownia.pamela.FriendlyPetClinic.vet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;
import pl.glownia.pamela.FriendlyPetClinic.model.Person;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "vets")
@Getter
@Setter
@NoArgsConstructor
public class VetEntity extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vet_generator")
    @SequenceGenerator(name = "vet_generator", sequenceName = "vet_sequence")
    @JsonView(EntityVisibility.Public.class)
    private long id;

    @Column(nullable = false)
    @ElementCollection()
    @JsonView(EntityVisibility.InternalVet.class)
    private Set<String> specialties = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "vet")
    @JsonView(EntityVisibility.InternalVet.class)
    private Set<VisitEntity> visits = new HashSet<>();

    public VetEntity(String firstName, String lastName, String email, String phoneNumber, Set<String> specialties, Set<VisitEntity> visits) {
        super(firstName, lastName, email, phoneNumber);
        this.specialties = specialties;
        this.visits = visits;
    }

    public void addVisit(VisitEntity visitEntity, PetEntity petEntity) {
        visitEntity.setVet(this);
        visitEntity.setPet(petEntity);
        visits.add(visitEntity);
    }

    public Set<String> getSpecialties() {
        return new HashSet<>(specialties);
    }

    public Set<VisitEntity> getVisits() {
        return new HashSet<>(visits);
    }
}