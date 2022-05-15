package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clinic/pet-owners")
public class PetOwnerController {
    private final PetOwnerService petOwnerService;

    @Autowired
    PetOwnerController(PetOwnerService petOwnerService) {
        this.petOwnerService = petOwnerService;
    }

    @PostMapping
    ResponseEntity<String> createPetOwner(@RequestBody @Valid PetOwnerDto petOwnerDto) {
        petOwnerService.createPetOwner(petOwnerDto);
        return ResponseEntity.ok("Pet owner data is valid.");
    }

    @PatchMapping("/{petOwnerId}")
    void addPet(@PathVariable long petOwnerId, @RequestBody @Valid PetEntity petEntity) {
        petOwnerService.addPet(petOwnerId, petEntity);
    }

    @GetMapping
    @JsonView(EntityVisibility.InternalOwner.class)
    List<PetOwnerDto> getAllPetOwners() {
        return petOwnerService.getAllPetOwners();
    }

    @GetMapping("/{petOwnerId}")
    @JsonView(EntityVisibility.InternalOwner.class)
    Optional<PetOwnerDto> getPetOwnerById(@PathVariable long petOwnerId) {
        return petOwnerService.getPetOwnerById(petOwnerId);
    }

    @PutMapping("/{petOwnerId}")
    void updatePetOwnerData(@RequestBody @Valid PetOwnerDto petOwnerDto, @PathVariable long petOwnerId) {
        petOwnerService.updatePetOwnerData(petOwnerId, petOwnerDto);
    }

    @DeleteMapping("/{petOwnerId}")
    void deletePetOwner(@PathVariable long petOwnerId) {
        petOwnerService.deletePetOwner(petOwnerId);
    }
}