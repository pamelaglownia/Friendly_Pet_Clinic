package pl.glownia.pamela.FriendlyPetClinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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