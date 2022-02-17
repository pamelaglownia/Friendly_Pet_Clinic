package pl.glownia.pamela.FriendlyPetClinic.pet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.glownia.pamela.FriendlyPetClinic.petOwner.PetOwner;
import pl.glownia.pamela.FriendlyPetClinic.vet.Vet;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Entity(name = "pets")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "First name is required.")
    private String name;

    @Column(nullable = false)
    @PositiveOrZero(message = "Age cannot be lower than zero.")
    private int age;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Type should be determined.")
    private String type;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private PetOwner owner;

    @ManyToOne
    @JoinColumn(name = "vet_id", nullable = false)
    private Vet vet;
}