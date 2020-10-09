package lambda.work.javazoos.services;

import lambda.work.javazoos.models.*;
import lambda.work.javazoos.repositories.AnimalRepository;
import lambda.work.javazoos.repositories.TelephoneRepository;
import lambda.work.javazoos.repositories.ZooAnimalRepository;
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

    @Transactional
    @Override
    public Zoo saveZoo(Zoo zoo) {
        Zoo newZoo = new Zoo();
        // handle simple fields
        newZoo.setZooname(zoo.getZooname());

        // handle ID
        if(zoo.getZooid() != 0)
        {
            zooRepository.findById(zoo.getZooid()).orElseThrow(() -> new EntityNotFoundException("Could not find zoo with id '" + zoo.getZooid() + "'"));
            newZoo.setZooid(zoo.getZooid());
        }

        // handle phones
        //newZoo.getPhones().clear();
        for(Telephone phone : zoo.getPhones())
        {
            Telephone newPhone = new Telephone(phone.getPhonenumber(), phone.getPhonetype());
            newPhone.setZoo(newZoo);
            newZoo.getPhones().add(newPhone);
        }

        // handle animals
        //newZoo.getAnimals().clear();
        for(ZooAnimal zooAnimal : zoo.getAnimals())
        {
            Animal newAnimal = animalRepository.findById(zooAnimal.getAnimal().getAnimalid())
                    .orElseThrow(() -> new EntityNotFoundException("Could not find animal with id '" + zoo.getZooid() + "'"));

            newZoo.getAnimals().add(new ZooAnimal(newZoo, newAnimal, zooAnimal.getIncomingzoo()));

            /*// find the existing ZooAnimal map, if any; otherwise make a new one
            ZooAnimal newZooAnimal = zooAnimalRepository.findById(
                    new ZooAnimalID(zooAnimal.getAnimal().getAnimalid(), zoo.getZooid()))
                    .orElse(new ZooAnimal());

            Animal newAnimal = animalRepository.findById(zooAnimal.getAnimal().getAnimalid())
                    .orElseThrow(() -> new EntityNotFoundException("Could not find animal with id '" + zoo.getZooid() + "'"));

            newZooAnimal.setIncomingzoo(zooAnimal.getIncomingzoo());
            newZooAnimal.setAnimal(newAnimal);
            newZooAnimal.setZoo(newZoo);
            newZoo.getAnimals().add(newZooAnimal);
            newAnimal.getZoos().add(newZooAnimal);*/
        }

        return zooRepository.save(newZoo);
    }

    @Transactional
    @Override
    public Zoo patchZoo(Zoo zoo) {
        Zoo newZoo = zooRepository.findById(zoo.getZooid())
                .orElseThrow(() -> new EntityNotFoundException("Could not find zoo with id '" + zoo.getZooid() + "'"));;

        // handle simple fields
        if(zoo.getZooname() != null) newZoo.setZooname(zoo.getZooname());

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

                newZoo.getAnimals().add(new ZooAnimal(newZoo, newAnimal, zooAnimal.getIncomingzoo()));
            }
        }

        return zooRepository.save(newZoo);
    }

    @Transactional
    @Override
    public void deleteZoo(long id) {
        zooRepository.deleteById(id);
    }
}
