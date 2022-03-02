package pl.glownia.pamela.FriendlyPetClinic.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }


    List<VisitDto> getAllVisits() {
        List<VisitEntity> visits = visitRepository.findAll();
        return visits.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    Optional<VisitDto> getVisitById(long id) {
        VisitEntity visit = visitRepository.findById(id).orElseThrow(() -> new RuntimeException("Visit with id " + " doesn't exist."));
        return Optional.of(convertToDto(visit));
    }

    //convert to DTO
    private VisitDto convertToDto(VisitEntity visitEntity) {
        return new VisitDto(visitEntity.getId(), visitEntity.getVisitType(), visitEntity.getDateOfVisit(),
                visitEntity.getPet(), visitEntity.getVet());
    }

    //convert to entity
    private VisitEntity convertToEntity(VisitDto visitDto) {
        return new VisitEntity(visitDto.getVisitId(), visitDto.getVisitType(),
                visitDto.getDateOfVisit(), visitDto.getPet(), visitDto.getVet());
    }
}