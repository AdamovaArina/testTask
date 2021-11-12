package dao;

import beans.IRepository;
import beans.Person;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Repository implements IRepository {
    final private PersonMapper personMapper;
    final private SqlSession mySession;

    public Repository(PersonMapper personMapper, SqlSession mySession) {
        this.personMapper = personMapper;
        this.mySession = mySession;
    }

    public Person getPersonById(int id) {
        return personMapper.getPersonById(id);
    }

    public List<Person> getPersons() {
        return personMapper.getPersons();
    }

    public List<Person> getPersonsSort() {
        return personMapper.getPersonsSort();
    }

    public void addPerson(Person myPerson) {
        try{
            personMapper.addPerson(myPerson);
        } finally{
            mySession.commit();
        }
    }

    public void deletePerson(int id) {
        try{
            personMapper.deletePerson(id);
        } finally {
            mySession.commit();
        }
    }

    public void updatePerson(Person myPerson) {
        try{
            personMapper.updatePerson(myPerson);
        } finally{
            mySession.commit();
        }
    }
}
