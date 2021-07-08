package lambda.work.javazoos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="zooanimals")
@IdClass(ZooAnimalID.class)
public class ZooAnimal extends Auditable implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "zooid")
    @JsonIgnoreProperties(value = "animals", allowSetters = true)
    private Zoo zoo;

    @Id
    @ManyToOne
    @JoinColumn(name = "animalid")
    @JsonIgnoreProperties(value = "zoos", allowSetters = true)
    private Animal animal;

    private String incomingzoo;

    public ZooAnimal(Zoo zoo, Animal animal, String incomingzoo) {
        this.zoo = zoo;
        this.animal = animal;
        this.incomingzoo = incomingzoo;
    }

    public ZooAnimal(String incomingzoo) {
        this.incomingzoo = incomingzoo;
    }

    public ZooAnimal() {
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getIncomingzoo() {
        return incomingzoo;
    }

    public void setIncomingzoo(String incomingzoo) {
        this.incomingzoo = incomingzoo;
    }
}
