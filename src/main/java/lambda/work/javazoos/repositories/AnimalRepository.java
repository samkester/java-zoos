package lambda.work.javazoos.repositories;

import lambda.work.javazoos.models.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
}
