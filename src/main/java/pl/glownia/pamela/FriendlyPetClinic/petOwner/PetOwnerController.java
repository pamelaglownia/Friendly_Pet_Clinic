package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    List<PetOwnerDto> getAllPetOwners() {
        return petOwnerService.getAllPetOwners();
    }

    @GetMapping("/{id}")
    Optional<PetOwnerDto> getPetOwnerById(@PathVariable long id) {
        return petOwnerService.getPetOwnerById(id);
    }
}