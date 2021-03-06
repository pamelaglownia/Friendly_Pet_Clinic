package pl.glownia.pamela.FriendlyPetClinic.petOwner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwnerEntity, Long> {
    PetOwnerEntity findByEmail(String email);
}