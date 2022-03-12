package pl.glownia.pamela.FriendlyPetClinic.model;

public interface EntityVisibility {
    class Public {
    }

    class InternalOwner extends Public {
    }

    class InternalPet extends Public {
    }

    class InternalVet extends Public {
    }
}