package lambda.work.javazoos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zoos")
public class Zoo extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zooid;

    private String zooname;

    @OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"zoo", "zoos"})
    private Set<Telephone> phones = new HashSet<>();

    @OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"zoo", "zoos"})
    private Set<ZooAnimal> animals = new HashSet<>();

    public Zoo() {
    }

    public Zoo(long zooid, String zooname) {
        this.zooid = zooid;
        this.zooname = zooname;
    }

    public long getZooid() {
        return zooid;
    }

    public void setZooid(long zooid) {
        this.zooid = zooid;
    }

    public String getZooname() {
        return zooname;
    }

    public void setZooname(String name) {
        this.zooname = name;
    }

    public Set<Telephone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Telephone> phones) {
        this.phones = phones;
    }

    public Set<ZooAnimal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<ZooAnimal> animals) {
        this.animals = animals;
    }
}
