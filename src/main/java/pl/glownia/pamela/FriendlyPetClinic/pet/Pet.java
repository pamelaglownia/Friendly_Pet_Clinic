package pl.glownia.pamela.FriendlyPetClinic.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.glownia.pamela.FriendlyPetClinic.petOwner.PetOwner;
import pl.glownia.pamela.FriendlyPetClinic.visit.Visit;

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
public class Pet {
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
    private PetOwner owner;

    @OneToMany(mappedBy = "pet")
    private Set<Visit> visits;

    public Set<Visit> getVisits() {
        return new HashSet<>(visits);
    }
}