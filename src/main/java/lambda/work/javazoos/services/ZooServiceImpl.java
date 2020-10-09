package lambda.work.javazoos.services;

import lambda.work.javazoos.models.Animal;
import lambda.work.javazoos.models.Telephone;
import lambda.work.javazoos.models.Zoo;
import lambda.work.javazoos.models.ZooAnimal;
import lambda.work.javazoos.repositories.AnimalRepository;
import lambda.work.javazoos.repositories.TelephoneRepository;
import lambda.work.javazoos.repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "zooService")
public class ZooServiceImpl implements ZooService{
    @Autowired
    private ZooRepository zooRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private TelephoneRepository telephoneRepository;

    @Override
    public List<Zoo> getAll() {
        List<Zoo> list = new ArrayList<>();
        zooRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Zoo getById(long id) {
        return zooRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find zoo with id '" + id + "'"));
    }

    @Override
    public Zoo saveZoo(Zoo zoo) {
        Zoo newZoo = new Zoo();
        // handle simple fields
        newZoo.setName(zoo.getName());

        // handle ID
        if(zoo.getZooid() != 0)
        {
            zooRepository.findById(zoo.getZooid()).orElseThrow(() -> new EntityNotFoundException("Could not find zoo with id '" + zoo.getZooid() + "'"));
            newZoo.setZooid(zoo.getZooid());
        }

        // handle phones
        for(Telephone phone : zoo.getPhones())
        {
            Telephone newPhone = new Telephone(phone.getPhonenumber(), phone.getPhonetype());
            newPhone.setZoo(newZoo);
            newZoo.getPhones().add(newPhone);
        }

        // handle animals
        for(ZooAnimal zooAnimal : zoo.getAnimals())
        {
            Animal newAnimal = animalRepository.findById(zooAnimal.getAnimal().getAnimalid())
                    .orElseThrow(() -> new EntityNotFoundException("Could not find animal with id '" + zoo.getZooid() + "'"));
            ZooAnimal newZooAnimal = new ZooAnimal();

            newZooAnimal.setIncomingzoo(zooAnimal.getIncomingzoo());
            newZooAnimal.setAnimal(newAnimal);
            newZooAnimal.setZoo(newZoo);
            newZoo.getAnimals().add(newZooAnimal);
            newAnimal.getZoos().add(newZooAnimal);
        }

        return newZoo;
    }

    @Override
    public Zoo patchZoo(Zoo zoo) {
        Zoo newZoo = zooRepository.findById(zoo.getZooid())
                .orElseThrow(() -> new EntityNotFoundException("Could not find zoo with id '" + zoo.getZooid() + "'"));;

        // handle simple fields
        newZoo.setName(zoo.getName());

        // handle phones
        if(zoo.getPhones().size() > 0) {
            newZoo.getPhones().clear();
            for (Telephone phone : zoo.getPhones()) {
                Telephone newPhone = new Telephone(phone.getPhonenumber(), phone.getPhonetype());
                newPhone.setZoo(newZoo);
                newZoo.getPhones().add(newPhone);
            }
        }

        // handle animals
        if(zoo.getAnimals().size() > 0) {
            newZoo.getAnimals().clear();
            for (ZooAnimal zooAnimal : zoo.getAnimals()) {
                Animal newAnimal = animalRepository.findById(zooAnimal.getAnimal().getAnimalid())
                        .orElseThrow(() -> new EntityNotFoundException("Could not find animal with id '" + zoo.getZooid() + "'"));
                ZooAnimal newZooAnimal = new ZooAnimal();

                newZooAnimal.setIncomingzoo(zooAnimal.getIncomingzoo());
                newZooAnimal.setAnimal(newAnimal);
                newZooAnimal.setZoo(newZoo);
                newZoo.getAnimals().add(newZooAnimal);
                newAnimal.getZoos().add(newZooAnimal);
            }
        }

        return newZoo;
    }

    @Override
    public void deleteZoo(long id) {
        zooRepository.deleteById(id);
    }
}
