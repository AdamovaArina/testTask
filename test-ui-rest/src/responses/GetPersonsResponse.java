package responses;

import beans.Person;

import java.util.ArrayList;
import java.util.List;

public class GetPersonsResponse extends BaseResponse {
    private List<Person> data;

    public GetPersonsResponse(String message, boolean status, List<Person> data) {
        super(message, status);
        this.data = data;
    }

    public List<Person> getData() {
        return data;
    }

    public void setData(List<Person> data) {
        this.data = data;
    }
}
