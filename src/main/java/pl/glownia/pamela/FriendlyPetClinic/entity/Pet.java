package pl.glownia.pamela.FriendlyPetClinic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int age;
    private String type;
    private String kindOfVisit;

    public Pet() {
    }

    public Pet(String name, int age, String type, String kindOfVisit) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.kindOfVisit = kindOfVisit;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public String getKindOfVisit() {
        return kindOfVisit;
    }
}