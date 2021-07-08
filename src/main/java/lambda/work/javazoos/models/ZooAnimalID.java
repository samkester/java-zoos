package lambda.work.javazoos.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ZooAnimalID implements Serializable {
    private long animal;
    private long zoo;

    public ZooAnimalID(long animal, long zoo) {
        this.animal = animal;
        this.zoo = zoo;
    }

    public ZooAnimalID() {
    }

    public long getAnimal() {
        return animal;
    }

    public void setAnimal(long animal) {
        this.animal = animal;
    }

    public long getZoo() {
        return zoo;
    }

    public void setZoo(long zoo) {
        this.zoo = zoo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZooAnimalID that = (ZooAnimalID) o;
        return getAnimal() == that.getAnimal() &&
                getZoo() == that.getZoo();
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
