package pl.glownia.pamela.FriendlyPetClinic.vet;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.FriendlyPetClinic.model.EntityVisibility;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clinic/vets")
public class VetController {

    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @PostMapping
    ResponseEntity<String> createVet(@RequestBody @Valid VetDto vetDto) {
        vetService.createVet(vetDto);
        return ResponseEntity.ok("Vet data is valid.");
    }

    @PostMapping("/{vetId}/visits/{petId}")
    ResponseEntity<String> addVisit(@PathVariable long vetId, @PathVariable long petId, @RequestBody @Valid VisitEntity visitEntity) {
        vetService.addVisit(vetId, visitEntity, petId);
        return ResponseEntity.ok("Visit data is valid.");
    }

    @GetMapping
    @JsonView(EntityVisibility.Public.class)
    List<VetDto> getAllVets() {
        return vetService.getAllVets();
    }

    @GetMapping("/{vetId}")
    @JsonView(EntityVisibility.InternalVet.class)
    Optional<VetDto> getVetById(@PathVariable long vetId) {
        return vetService.getVetById(vetId);
    }

    @PutMapping("/{vetId}")
    ResponseEntity<String> updateVetData(@PathVariable long vetId, @RequestBody @Valid VetDto vetDto) {
        vetService.updateVetData(vetId, vetDto);
        return ResponseEntity.ok("Vet data is valid.");
    }
}