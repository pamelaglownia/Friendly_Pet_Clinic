package pl.glownia.pamela.FriendlyPetClinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.glownia.pamela.FriendlyPetClinic.entity.PetOwner;

public interface PetOwnerRepository extends CrudRepository<PetOwner, Long> {
}
