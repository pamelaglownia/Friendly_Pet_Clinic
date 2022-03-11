package pl.glownia.pamela.FriendlyPetClinic.pet;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;

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
    @JsonView(EntityVisibility.InternalPet.class)
//    @JsonView(EntityVisibility.Public.class)
    List<PetDto> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{petId}")
    @JsonView(EntityVisibility.InternalPet.class)
//    @JsonView(EntityVisibility.Public.class)
    Optional<PetDto> getPetById(@PathVariable long petId) {
        return petService.getPetById(petId);
    }
}