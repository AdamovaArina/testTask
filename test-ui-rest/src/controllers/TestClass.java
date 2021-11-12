package controllers;

import beans.PersonService;
import dao.PersonMapper;
import dao.Repository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.Reader;

@SpringBootApplication
public class TestClass {

    public static void main(String[] args) {

        try {
            SpringApplication.run(TestClass.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public PersonService personSystem() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        PersonMapper newMapper;
        Reader reader;
        reader = Resources
                .getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession s = sqlSessionFactory.openSession();
        newMapper = s.getMapper(PersonMapper.class);
        Repository r = new Repository(newMapper, s);
        return new PersonService(r);
    }
}
