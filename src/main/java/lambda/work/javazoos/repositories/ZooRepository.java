package lambda.work.javazoos.repositories;

import lambda.work.javazoos.models.Zoo;
import org.springframework.data.repository.CrudRepository;

public interface ZooRepository extends CrudRepository<Zoo, Long> {
}
