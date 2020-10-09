package lambda.work.javazoos.services;

import lambda.work.javazoos.models.Animal;
import lambda.work.javazoos.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "animalService")
public class AnimalServiceImpl implements AnimalService{
    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public List<Animal> getAll() {
        List<Animal> list = new ArrayList<>();
        animalRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
