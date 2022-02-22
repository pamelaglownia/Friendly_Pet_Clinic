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


    public List<PetOwnerDto> getAllPetOwners() {
        List<PetOwnerEntity> owners = petOwnerRepository.findAll();
        return owners.stream().map(this::getPetOwner).collect(Collectors.toList());
    }

    private PetOwnerDto getPetOwner(PetOwnerEntity petOwnerEntity) {
        return new PetOwnerDto(petOwnerEntity.getId(), petOwnerEntity.getFirstName(), petOwnerEntity.getLastName(),
                petOwnerEntity.getEmail(), petOwnerEntity.getPhoneNumber(), petOwnerEntity.getAddress(), petOwnerEntity.getPets());
    }
}