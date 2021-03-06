package pl.glownia.pamela.FriendlyPetClinic.pet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;
import pl.glownia.pamela.FriendlyPetClinic.petOwner.PetOwnerEntity;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "pets")
@Getter
@Setter
@NoArgsConstructor
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_generator")
    @SequenceGenerator(name = "pet_generator", sequenceName = "pet_sequence")
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT DEFAULT 'unnamed'")
    @JsonView(EntityVisibility.Public.class)
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonView(EntityVisibility.InternalPet.class)
    private Date dateOfBirth;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Type should be determined.")
    @JsonView(EntityVisibility.Public.class)
    private String type;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    @JsonView(EntityVisibility.InternalPet.class)
    private PetOwnerEntity owner;

    @JsonIgnore
    @OneToMany(mappedBy = "pet")
    @JsonView(EntityVisibility.InternalPet.class)
    private Set<VisitEntity> visits;

    public PetEntity(String name, Date dateOfBirth, String type) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
    }

    public Set<VisitEntity> getVisits() {
        return new HashSet<>(visits);
    }
}