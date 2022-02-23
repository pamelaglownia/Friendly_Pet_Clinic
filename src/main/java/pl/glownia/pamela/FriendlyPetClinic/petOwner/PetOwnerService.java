package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetOwnerService {
    private final PetOwnerRepository petOwnerRepository;

    @Autowired
    public PetOwnerService(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    public void createPetOwner(PetOwnerDto petOwnerDto) {
        PetOwnerEntity petOwnerEntity = convertToEntity(petOwnerDto);
        petOwnerRepository.save(petOwnerEntity);
    }

    public List<PetOwnerDto> getAllPetOwners() {
        List<PetOwnerEntity> owners = petOwnerRepository.findAll();
        return owners.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //convert to DTO
    private PetOwnerDto convertToDto(PetOwnerEntity petOwnerEntity) {
        return new PetOwnerDto(petOwnerEntity.getId(), petOwnerEntity.getFirstName(), petOwnerEntity.getLastName(),
                petOwnerEntity.getEmail(), petOwnerEntity.getPhoneNumber(), petOwnerEntity.getAddress());
    }

    //convert to entity
    private PetOwnerEntity convertToEntity(PetOwnerDto petOwnerDto) {
        return new PetOwnerEntity(petOwnerDto.getFirstName(), petOwnerDto.getLastName(),
                petOwnerDto.getEmail(), petOwnerDto.getPhoneNumber(), petOwnerDto.getAddress());
    }
}