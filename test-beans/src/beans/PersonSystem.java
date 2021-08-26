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

    public void addPerson(String lastName, String firstName, String patronymic, Date dateOfBirth, Gender personGender) throws Exception {
        if (lastName == null || firstName == null || patronymic == null || dateOfBirth == null){
            throw new Exception("Incorrect arguments");
        }
        Person myPerson = new Person(0, lastName, firstName, patronymic, dateOfBirth, personGender);
        this.repository.addPerson(myPerson);
    }

    public void deletePerson(int id) throws Exception {
        Person myPerson = this.repository.getPersonById(id);
        if (myPerson == null){
            throw new Exception("beans.Person is not existed");
        }
        this.repository.deletePerson(id);
    }

    public void updatePerson(int id, String lastName, String firstName, String patronymic, Date dateOfBirth, Gender personGender) throws Exception {
        Person myPerson = this.repository.getPersonById(id);
        if (myPerson == null){
            throw new Exception("beans.Person is not existed");
        }
        if (lastName == null || firstName == null || patronymic == null || dateOfBirth == null){
            throw new Exception("Incorrect arguments");
        }
        myPerson.setLastName(lastName);
        myPerson.setFirstName(firstName);
        myPerson.setPatronymic(patronymic);
        myPerson.setDateOfBirth(dateOfBirth);
        myPerson.setPersonGender(personGender);

        this.repository.updatePerson(myPerson);
    }
}
