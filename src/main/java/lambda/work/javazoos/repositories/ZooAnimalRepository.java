package lambda.work.javazoos.repositories;

import lambda.work.javazoos.models.ZooAnimal;
import lambda.work.javazoos.models.ZooAnimalID;
import org.springframework.data.repository.CrudRepository;

public interface ZooAnimalRepository extends CrudRepository<ZooAnimal, ZooAnimalID> {
}
