package pl.glownia.pamela.FriendlyPetClinic.vet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.model.Person;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "vets")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VetEntity extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "At least one specialty should be determined.")
    @ElementCollection(targetClass = String.class)
    private Set<String> specialties;

    @OneToMany(mappedBy = "vet")
    private Set<VisitEntity> visits;

    public Set<String> getSpecialties() {
        return new HashSet<>(specialties);
    }

    public Set<VisitEntity> getVisits() {
        return new HashSet<>(visits);
    }
}