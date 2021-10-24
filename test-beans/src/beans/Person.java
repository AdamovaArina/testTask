package beans;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Person {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Date dateOfBirth;
    private Gender personGender;
}
