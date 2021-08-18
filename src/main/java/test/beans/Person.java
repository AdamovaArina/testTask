package test.beans;

import java.util.Date;

public class Person {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Date dateOfBirth;
    private Gender personGender;

    public Person(int id, String lastName, String firstName, String patronymic, Date dateOfBirth, Gender personGender){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.personGender = personGender;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Gender getPersonGender() {
        return personGender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPersonGender(Gender personGender) {
        this.personGender = personGender;
    }

}
