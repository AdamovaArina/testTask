package test.dao;

import test.beans.Person;

import java.util.List;

public interface MyMapper {
    Person getPersonById(int id);
    List<Person> getPersons();
    List<Person> getPersonsSort();
    void addPerson(Person p);
    void deletePerson(int id);
    void updatePerson(Person p);

}
