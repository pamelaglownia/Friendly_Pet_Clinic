package pl.glownia.pamela.FriendlyPetClinic.vet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<VetEntity, Long> {
    VetEntity findByEmail(String email);
}