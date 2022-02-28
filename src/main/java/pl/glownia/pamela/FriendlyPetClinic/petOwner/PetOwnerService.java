package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetEntity;
import pl.glownia.pamela.FriendlyPetClinic.pet.PetRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetOwnerService {
    private final PetOwnerRepository petOwnerRepository;
    private final PetRepository petRepository;

    @Autowired
    public PetOwnerService(PetOwnerRepository petOwnerRepository, PetRepository petRepository) {
        this.petOwnerRepository = petOwnerRepository;
        this.petRepository = petRepository;
    }

    public void createPetOwner(PetOwnerDto petOwnerDto) {
        PetOwnerEntity petOwnerEntity = convertToEntity(petOwnerDto);
        petOwnerRepository.save(petOwnerEntity);
    }

    public void addPet(long petOwnerId, PetEntity petEntity) {
        PetOwnerEntity petOwnerEntity = findPetOwnerById(petOwnerId);
        petOwnerEntity.addPet(petEntity);
        petRepository.save(petEntity);
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
        PetOwnerEntity petOwnerEntity = findPetOwnerById(petOwnerDto.getPetOwnerId());
        petOwnerEntity.setFirstName(petOwnerDto.getFirstName());
        petOwnerEntity.setLastName(petOwnerDto.getLastName());
        petOwnerEntity.setEmail(petOwnerDto.getEmail());
        petOwnerEntity.setPhoneNumber(petOwnerDto.getPhoneNumber());
        petOwnerEntity.setAddress(petOwnerDto.getAddress());
        petOwnerEntity.setPets(petOwnerDto.getPets());
        petOwnerRepository.save(petOwnerEntity); // when we use transactional annotation, it is not necessary to use save() method
    }

    public void deletePetOwner(long id) {
        Optional<PetOwnerEntity> petOwnerEntity = Optional.ofNullable(findPetOwnerById(id));
        if (petOwnerEntity.isPresent()) {
            petOwnerRepository.deleteById(id);
        }
    }

    private PetOwnerEntity findPetOwnerById(long id) {
        return petOwnerRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet owner with id " + id + " doesn't exist."));
    }

    //convert to DTO
    private PetOwnerDto convertToDto(PetOwnerEntity petOwnerEntity) {
        return new PetOwnerDto(petOwnerEntity.getId(), petOwnerEntity.getFirstName(), petOwnerEntity.getLastName(),
                petOwnerEntity.getEmail(), petOwnerEntity.getPhoneNumber(), petOwnerEntity.getAddress(), petOwnerEntity.getPets());
    }

    //convert to entity
    private PetOwnerEntity convertToEntity(PetOwnerDto petOwnerDto) {
        return new PetOwnerEntity(petOwnerDto.getFirstName(), petOwnerDto.getLastName(),
                petOwnerDto.getEmail(), petOwnerDto.getPhoneNumber(), petOwnerDto.getAddress(), petOwnerDto.getPets());
    }
}