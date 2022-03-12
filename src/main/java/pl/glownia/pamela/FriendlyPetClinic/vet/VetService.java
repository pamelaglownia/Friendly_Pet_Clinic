package pl.glownia.pamela.FriendlyPetClinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetRepository;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitEntity;
import pl.glownia.pamela.FriendlyPetClinic.visit.VisitRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VetService {
    private final VetRepository vetRepository;
    private final PetRepository petRepository;
    private final VisitRepository visitRepository;

    @Autowired
    public VetService(VetRepository vetRepository, PetRepository petRepository, VisitRepository visitRepository) {
        this.vetRepository = vetRepository;
        this.petRepository = petRepository;
        this.visitRepository = visitRepository;
    }

    public void createVet(VetDto vetDto) {
        VetEntity vetEntity = convertToEntity(vetDto);
        vetRepository.save(vetEntity);
    }

    public void addVisit(long vetId, VisitEntity visitEntity, long petId) {
        VetEntity vetEntity = findVetById(vetId);
        PetEntity petEntity = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Visit with id " + " doesn't exist.'"));
        vetEntity.addVisit(visitEntity, petEntity);
        visitRepository.save(visitEntity);
    }

    List<VetDto> getAllVets() {
        List<VetEntity> vets = vetRepository.findAll();
        return vets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    Optional<VetDto> getVetById(long id) {
        VetEntity vetEntity = findVetById(id);
        return Optional.of(convertToDto(vetEntity));
    }

    void updateVetData(long vetId, VetDto vetDto) {
        VetEntity vetEntity = findVetById(vetId);
        vetEntity.setFirstName(vetDto.getFirstName());
        vetEntity.setLastName(vetDto.getLastName());
        vetEntity.setEmail(vetDto.getEmail());
        vetEntity.setPhoneNumber(vetDto.getPhoneNumber());
        vetEntity.setSpecialties(vetDto.getSpecialties());
        vetEntity.setVisits(vetDto.getVisits());
        vetRepository.save(vetEntity);
    }

    private VetEntity findVetById(long id) {
        return vetRepository.findById(id).orElseThrow(() -> new RuntimeException("Vet with id " + id + " doesn't exist."));
    }

    //convert to DTO
    private VetDto convertToDto(VetEntity vetEntity) {
        return new VetDto(vetEntity.getId(), vetEntity.getFirstName(), vetEntity.getLastName(),
                vetEntity.getEmail(), vetEntity.getPhoneNumber(), vetEntity.getSpecialties(), vetEntity.getVisits());
    }

    //convert to entity
    private VetEntity convertToEntity(VetDto vetDto) {
        return new VetEntity(vetDto.getFirstName(), vetDto.getLastName(),
                vetDto.getEmail(), vetDto.getPhoneNumber(), vetDto.getSpecialties(), vetDto.getVisits());
    }
}