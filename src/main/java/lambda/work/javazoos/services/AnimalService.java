package lambda.work.javazoos.services;

import lambda.work.javazoos.models.Animal;
import lambda.work.javazoos.views.AnimalCount;

import java.util.List;

public interface AnimalService {
    List<Animal> getAll();

    List<AnimalCount> getAnimalsByCount();
}
