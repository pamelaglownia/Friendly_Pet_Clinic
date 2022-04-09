package pl.glownia.pamela.FriendlyPetClinic.clinic;

import org.springframework.stereotype.Service;

@Service
public class ClinicService {

    String displayOpeningHours() {
        return """
                Friendly Pet Clinic opening hours:
                    Monday-Saturday: 8:00 - 20:00
                    Sunday: 8:00 - 15:00""";
    }
}