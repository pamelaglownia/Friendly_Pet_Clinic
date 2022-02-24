package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

    public Optional<PetOwnerDto> getPetOwnerById(long id) {
        PetOwnerEntity petOwnerEntity = petOwnerRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet owner with id " + id + " doesn't exist."));
        return Optional.of(convertToDto(petOwnerEntity));
    }

    public List<PetOwnerDto> getAllPetOwners() {
        List<PetOwnerEntity> owners = petOwnerRepository.findAll();
        return owners.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public void updatePetOwnerData(PetOwnerDto petOwnerDto) {
        PetOwnerEntity petOwnerEntity = petOwnerRepository.findById(petOwnerDto.getPetOwnerId()).orElseThrow(() -> new RuntimeException("Pet owner with id " + petOwnerDto.getPetOwnerId() + " doesn't exist."));
        petOwnerEntity.setFirstName(petOwnerDto.getFirstName());
        petOwnerEntity.setLastName(petOwnerDto.getLastName());
        petOwnerEntity.setEmail(petOwnerDto.getEmail());
        petOwnerEntity.setPhoneNumber(petOwnerDto.getPhoneNumber());
        petOwnerEntity.setAddress(petOwnerDto.getAddress());
        petOwnerRepository.save(petOwnerEntity);
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