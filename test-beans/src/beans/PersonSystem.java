package beans;

import java.util.Date;
import java.util.List;

public class PersonSystem {
    final private IRepository repository;

    public PersonSystem(IRepository repository) {
        this.repository = repository;
    }

    public List<Person> getPersons(){
        return this.repository.getPersons();
    }

    public List<Person> getPersonsSort(){
        return this.repository.getPersonsSort();
    }

    public void addPerson(Person myPerson) throws Exception {
        this.repository.addPerson(myPerson);
    }

    public void deletePerson(int id) throws Exception {
        Person myPerson = this.repository.getPersonById(id);
        if (myPerson == null){
            throw new Exception("beans.Person is not existed");
        }
        this.repository.deletePerson(id);
    }

    public void updatePerson(Person myPerson) throws Exception {
        if (this.repository.getPersonById(myPerson.getId()) == null){
            throw new Exception("beans.Person is not existed");
        }
        this.repository.updatePerson(myPerson);
    }
}
