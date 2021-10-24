package requests;

import beans.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class AddPersonRequest {
    private String lastName;
    private String firstName;
    private String patronymic;
    private Date dateOfBirth;
    private Gender personGender;
}
