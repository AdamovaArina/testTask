package controllers;

import beans.PersonSystem;
import dao.MyMapper;
import dao.Repository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.Reader;

@SpringBootApplication
public class TestClass {
    private static PersonSystem personSystem;

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory;
        MyMapper newMapper;
        Reader reader;
        try {
            reader = Resources
                    .getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession s = sqlSessionFactory.openSession();
            newMapper = s.getMapper(MyMapper.class);
            Repository r = new Repository(newMapper, s);
            TestClass.personSystem = new PersonSystem(r);

            SpringApplication.run(TestClass.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public PersonSystem personSystem(){
        return personSystem;
    }
}
