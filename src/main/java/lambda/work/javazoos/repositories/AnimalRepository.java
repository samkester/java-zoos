package lambda.work.javazoos.repositories;

import lambda.work.javazoos.models.Animal;
import lambda.work.javazoos.views.AnimalCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
    @Query(value =  "SELECT a.animaltype, a.animalid, count(z.zooid) as countzoos " +
                    "FROM animals a LEFT JOIN zooanimals z " +
                    "ON a.animalid = z.animalid " +
                    "GROUP BY a.animaltype " +
                    "ORDER BY countzoos desc", nativeQuery = true)
    List<AnimalCount> findAnimalCounts();
}
