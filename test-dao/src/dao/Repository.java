package dao;

import beans.IRepository;
import beans.Person;
import org.apache.ibatis.session.SqlSession;

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
        try{
            this.myMapper.addPerson(myPerson);
        } finally{
            this.mySession.commit();
        }
    }

    public void deletePerson(int id) {
        try{
            this.myMapper.deletePerson(id);
        } finally {
            this.mySession.commit();
        }
    }

    public void updatePerson(Person myPerson) {
        try{
            this.myMapper.updatePerson(myPerson);
        } finally{
            this.mySession.commit();
        }
    }
}
