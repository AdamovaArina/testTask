package test.ui.rest.responses;

import test.beans.Person;

import java.util.ArrayList;
import java.util.List;

public class GetPersonsResponse extends BaseResponse {
    private List<PersonResponse> data;

    public GetPersonsResponse(String message, boolean status, List<Person> data) {
        super(message, status);
        this.data = new ArrayList<PersonResponse>();
        for (int i = 0; i < data.size(); i++){
            this.data.add(new PersonResponse(data.get(i)));
        }
    }

    public List<PersonResponse> getData() {
        return data;
    }

    public void setData(List<PersonResponse> data) {
        this.data = data;
    }
}
