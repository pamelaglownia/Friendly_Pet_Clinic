package pl.glownia.pamela.FriendlyPetClinic.visit;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;
import pl.glownia.pamela.FriendlyPetClinic.vet.VetEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "visits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_generator")
    @SequenceGenerator(name = "visit_generator", sequenceName = "visit_sequence")
    @JsonView(EntityVisibility.Public.class)
    private long id;

    @Column(nullable = false)
    @JsonView(EntityVisibility.Public.class)
    private VisitType visitType;

    @Column(nullable = false)
    @JsonView(EntityVisibility.Public.class)
    private LocalDateTime dateOfVisit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    @JsonView(EntityVisibility.Public.class)
    private PetEntity pet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vet_id")
    private VetEntity vet;

    public VisitEntity(VisitType visitType, LocalDateTime dateOfVisit) {
        this.visitType = visitType;
        this.dateOfVisit = dateOfVisit;
    }
}