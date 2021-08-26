package responses;

import beans.Gender;
import beans.Person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonResponse {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String dateOfBirth;
    private String personGender;

    public PersonResponse(int id, String lastName, String firstName, String patronymic,
                          Date dateOfBirth, Gender personGender){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.dateOfBirth = dateFormat.format(dateOfBirth);
        this.personGender = personGender == Gender.MALE ? "м":"ж";
    }

    public PersonResponse(Person person){
        this.id = person.getId();
        this.lastName = person.getLastName();
        this.firstName = person.getFirstName();
        this.patronymic = person.getPatronymic();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.dateOfBirth = dateFormat.format(person.getDateOfBirth());
        this.personGender = person.getPersonGender() == Gender.MALE ? "м":"ж";
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPersonGender() {
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

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.dateOfBirth = dateFormat.format(dateOfBirth);
    }

    public void setPersonGender(Gender personGender) {
        this.personGender = personGender == Gender.MALE ? "м":"ж";
    }
}
