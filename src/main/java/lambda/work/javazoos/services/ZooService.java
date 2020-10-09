package lambda.work.javazoos.services;

import lambda.work.javazoos.models.Zoo;

import java.util.List;

public interface ZooService {
    List<Zoo> getAll();

    Zoo getById(long id);

    Zoo saveZoo(Zoo zoo);

    Zoo patchZoo(Zoo zoo);

    void deleteZoo(Zoo zoo);
}
