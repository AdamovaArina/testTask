package beans;

import java.util.List;

public class PersonService {
    final private IRepository repository;

    public PersonService(IRepository repository) {
        this.repository = repository;
    }

    public List<Person> getPersons(){
        return repository.getPersons();
    }

    public List<Person> getPersonsSort(){
        return repository.getPersonsSort();
    }

    public void addPerson(Person myPerson) throws Exception {
        repository.addPerson(myPerson);
    }

    public void deletePerson(int id) throws Exception {
        Person myPerson = repository.getPersonById(id);
        if (myPerson == null){
            throw new Exception("beans.Person is not existed");
        }
        repository.deletePerson(id);
    }

    public void updatePerson(Person myPerson) throws Exception {
        if (repository.getPersonById(myPerson.getId()) == null){
            throw new Exception("beans.Person is not existed");
        }
        repository.updatePerson(myPerson);
    }
}
