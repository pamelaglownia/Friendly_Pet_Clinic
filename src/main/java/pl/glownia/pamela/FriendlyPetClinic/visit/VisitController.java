package pl.glownia.pamela.FriendlyPetClinic.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visits")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    List<VisitDto> getAllVisits() {
        return visitService.getAllVisits();
    }

    @GetMapping("/{visitId}")
    Optional<VisitDto> getVisitById(@PathVariable long visitId) {
        return visitService.getVisitById(visitId);
    }
}