package pl.glownia.pamela.FriendlyPetClinic.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    List<PetDto> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{petId}")
    Optional<PetDto> getPetById(@PathVariable long petId) {
        return petService.getPetById(petId);
    }
}