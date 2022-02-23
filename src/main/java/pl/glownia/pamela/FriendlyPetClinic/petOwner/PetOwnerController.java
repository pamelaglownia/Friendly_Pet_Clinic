package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet-owners")
public class PetOwnerController {
    private final PetOwnerService petOwnerService;

    @Autowired
    public PetOwnerController(PetOwnerService petOwnerService) {
        this.petOwnerService = petOwnerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void createPetOwner(@RequestBody PetOwnerDto petOwnerDto) {
        petOwnerService.createPetOwner(petOwnerDto);
    }

    @GetMapping
    @ResponseBody
    public List<PetOwnerDto> getAllPetOwners() {
        return petOwnerService.getAllPetOwners();
    }
}