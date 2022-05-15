package pl.glownia.pamela.FriendlyPetClinic.pet;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clinic/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    ResponseEntity<String> addPet(@RequestBody @Valid PetDto petDto) {
        petService.addPet(petDto);
        return ResponseEntity.ok("Pet data is valid.");
    }

    @GetMapping
    @JsonView(EntityVisibility.InternalPet.class)
    List<PetDto> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{petId}")
    @JsonView(EntityVisibility.InternalPet.class)
    Optional<PetDto> getPetById(@PathVariable long petId) {
        return petService.getPetById(petId);
    }
}