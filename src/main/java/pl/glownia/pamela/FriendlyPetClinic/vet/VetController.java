package pl.glownia.pamela.FriendlyPetClinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vets")
public class VetController {

    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void createVet(@RequestBody VetDto vetDto) {
        vetService.createVet(vetDto);
    }
}