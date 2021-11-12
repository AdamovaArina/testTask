package dao;

import beans.Person;

import java.util.List;

public interface PersonMapper {
    Person getPersonById(int id);
    List<Person> getPersons();
    List<Person> getPersonsSort();
    void addPerson(Person p);
    void deletePerson(int id);
    void updatePerson(Person p);

}
