package pl.glownia.pamela.FriendlyPetClinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VetService {
    private final VetRepository vetRepository;

    @Autowired
    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    public void createVet(VetDto vetDto) {
        VetEntity vetEntity = convertToEntity(vetDto);
        vetRepository.save(vetEntity);
    }

    List<VetDto> getAllVets() {
        List<VetEntity> vets = vetRepository.findAll();
        return vets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    Optional<VetDto> getVetById(long id) {
        VetEntity vetEntity = vetRepository.findById(id).orElseThrow(() -> new RuntimeException("Vet with id " + id + " doesn't exist."));
        return Optional.of(convertToDto(vetEntity));
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