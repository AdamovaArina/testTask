package test.dao;

import org.apache.ibatis.session.SqlSession;
import test.beans.IRepository;
import test.beans.Person;

import java.util.List;

public class Repository implements IRepository {
    final private MyMapper myMapper;
    final private SqlSession mySession;

    public Repository(MyMapper myMapper, SqlSession mySession) {
        this.myMapper = myMapper;
        this.mySession = mySession;
    }

    public Person getPersonById(int id) {
        return this.myMapper.getPersonById(id);
    }

    public List<Person> getPersons() {
        return this.myMapper.getPersons();
    }

    public List<Person> getPersonsSort() {
        return this.myMapper.getPersonsSort();
    }

    public void addPerson(Person myPerson) {
        this.myMapper.addPerson(myPerson);
        this.mySession.commit();
    }

    public void deletePerson(int id) {
        this.myMapper.deletePerson(id);
        this.mySession.commit();
    }

    public void updatePerson(Person myPerson) {
        this.myMapper.updatePerson(myPerson);
        this.mySession.commit();
    }
}
