package pl.glownia.pamela.FriendlyPetClinic.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.glownia.pamela.FriendlyPetClinic.petOwner.PetOwnerEntity;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "pets")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT DEFAULT 'unnamed'")
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateOfBirth;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Type should be determined.")
    private String type;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private PetOwnerEntity owner;

    @OneToMany(mappedBy = "pet")
    private Set<VisitEntity> visits;

    public Set<VisitEntity> getVisits() {
        return new HashSet<>(visits);
    }
}