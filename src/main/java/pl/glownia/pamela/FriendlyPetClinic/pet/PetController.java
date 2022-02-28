package pl.glownia.pamela.FriendlyPetClinic.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void addPet(@RequestBody PetDto petDto) {
        petService.addPet(petDto);
    }

    @GetMapping
    List<PetDto> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{petId}")
    Optional<PetDto> getPetById(@PathVariable long petId) {
        return petService.getPetById(petId);
    }
}