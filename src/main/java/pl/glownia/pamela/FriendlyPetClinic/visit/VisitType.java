package pl.glownia.pamela.FriendlyPetClinic.visit;

public enum VisitType {
    PREVENTIVE("Routine preventive visit"),
    TREATMENT("Long-term treatment"),
    SURGERY("Surgical visit"),
    INJURY("Injuries, aches and pains"),
    EMERGENCY("Emergency visit"),
    HOUSECALL("Home visit");

    private final String name;

    VisitType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}