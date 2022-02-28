package pl.glownia.pamela.FriendlyPetClinic.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void addPet(PetDto petDto) {
        PetEntity petEntity = convertToEntity(petDto);
        petRepository.save(petEntity);
    }

    public List<PetDto> getAllPets() {
        List<PetEntity> pets = petRepository.findAll();
        return pets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<PetDto> getPetById(long id) {
        PetEntity petEntity = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet with id" + id + " doesn't exist."));
        return Optional.of(convertToDto(petEntity));
    }

    //convert to DTO
    private PetDto convertToDto(PetEntity petEntity) {
        return new PetDto(petEntity.getId(), petEntity.getName(), petEntity.getDateOfBirth(), petEntity.getType(), petEntity.getOwner(), petEntity.getVisits());
    }

    //convert to entity
    private PetEntity convertToEntity(PetDto petDto) {
        return new PetEntity(petDto.getName(), petDto.getDateOfBirth(), petDto.getType());
    }
}