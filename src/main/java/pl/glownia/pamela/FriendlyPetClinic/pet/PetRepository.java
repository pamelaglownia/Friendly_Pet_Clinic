package pl.glownia.pamela.FriendlyPetClinic.pet;

import org.springframework.data.repository.CrudRepository;
import pl.glownia.pamela.FriendlyPetClinic.pet.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>{
}
