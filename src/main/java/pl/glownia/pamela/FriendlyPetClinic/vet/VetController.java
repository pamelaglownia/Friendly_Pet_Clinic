package pl.glownia.pamela.FriendlyPetClinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/{vetId}/visits/{petId}")
    void addVisit(@PathVariable long vetId, @PathVariable long petId, @RequestBody VisitEntity visitEntity) {
        vetService.addVisit(visitEntity, vetId, petId);
    }

    @GetMapping
    List<VetDto> getAllVets() {
        return vetService.getAllVets();
    }

    @GetMapping("/{vetId}")
    Optional<VetDto> getVetById(@PathVariable long vetId) {
        return vetService.getVetById(vetId);
    }

    @PutMapping("/{vetId}")
    void updateVetData(@PathVariable long vetId, @RequestBody VetDto vetDto) {
        vetService.updateVetData(vetId, vetDto);
    }
}