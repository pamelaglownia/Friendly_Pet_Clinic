package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet-owners")
public class PetOwnerController {
    private final PetOwnerService petOwnerService;

    @Autowired
    PetOwnerController(PetOwnerService petOwnerService) {
        this.petOwnerService = petOwnerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void createPetOwner(@RequestBody PetOwnerDto petOwnerDto) {
        petOwnerService.createPetOwner(petOwnerDto);
    }

    @PatchMapping("/{petOwnerId}")
    @ResponseStatus(HttpStatus.OK)
    void addPet(@PathVariable long petOwnerId, @RequestBody PetEntity petEntity) {
        petOwnerService.addPet(petOwnerId, petEntity);
    }

    @GetMapping
    List<PetOwnerDto> getAllPetOwners() {
        return petOwnerService.getAllPetOwners();
    }

    @GetMapping("/{petOwnerId}")
    Optional<PetOwnerDto> getPetOwnerById(@PathVariable long petOwnerId) {
        return petOwnerService.getPetOwnerById(petOwnerId);
    }

    @PutMapping("/{petOwnerId}")
    void updatePetOwnerData(@RequestBody PetOwnerDto petOwnerDto, @PathVariable long petOwnerId) {
        petOwnerService.updatePetOwnerData(petOwnerId, petOwnerDto);
    }

    @DeleteMapping("/{petOwnerId}")
    void deletePetOwner(@PathVariable long petOwnerId) {
        petOwnerService.deletePetOwner(petOwnerId);
    }
}