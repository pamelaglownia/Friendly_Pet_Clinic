package pl.glownia.pamela.FriendlyPetClinic.visit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;
import pl.glownia.pamela.FriendlyPetClinic.vet.VetEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity(name = "visits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Type should be determined.")
    private VisitType visitType;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime dateOfVisit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private PetEntity pet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vet_id")
    private VetEntity vet;

    public VisitEntity(VisitType visitType, LocalDateTime dateOfVisit) {
        this.visitType = visitType;
        this.dateOfVisit = dateOfVisit;
    }
}