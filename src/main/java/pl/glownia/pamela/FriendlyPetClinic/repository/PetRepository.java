package pl.glownia.pamela.FriendlyPetClinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.glownia.pamela.FriendlyPetClinic.entity.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>{
}
