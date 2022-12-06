package sg.edu.nus.iss.demoDay1a.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import sg.edu.nus.iss.demoDay1a.model.Person;

@Service
public class PersonService {

    private List<Person> persons = new ArrayList<Person>();

    public PersonService(){
        persons.add(new Person("Mark","Kwan"));
        persons.add(new Person("Darryl","Eddie"));
    }

    public List<Person> getPersons(){
        return this.persons;
    }
    
    //----- DAY 2 -----//
    //Create
    public void addPerson(Person newPerson){
        persons.add(new Person(newPerson.getFirstName(), newPerson.getLastName()));
        // will use the 2 arg constructor
    }

    //Delete
    public void removePerson(Person personToDelete){
        Person foundPerson = persons.stream().filter(p -> p.getId().equals(personToDelete.getId())).findAny().orElse(null);
        //p = persons
        persons.remove(foundPerson);
    }

    //Update
    public void updatePerson(Person personToUpdate){
        Person foundPerson = persons.stream().filter(p -> p.getId().equals(personToUpdate.getId())).findAny().orElse(null);

        Person updatePerson = new Person();
        updatePerson.setId(foundPerson.getId());
        updatePerson.setFirstName(personToUpdate.getFirstName());
        updatePerson.setLastName(personToUpdate.getLastName());
        persons.remove(foundPerson);
        persons.add(updatePerson);
        
    }

}
