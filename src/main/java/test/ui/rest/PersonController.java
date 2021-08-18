package test.ui.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.beans.PersonSystem;
import test.ui.rest.requests.AddPersonRequest;
import test.ui.rest.requests.UpdatePersonRequest;
import test.ui.rest.responses.BaseResponse;
import test.ui.rest.responses.GetPersonsResponse;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonSystem personSystem;

    public PersonController(PersonSystem personSystem) {
        this.personSystem = personSystem;
    }

    @RequestMapping(value = "/getPersons",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BaseResponse getPersons() {
        try{
            return new GetPersonsResponse("The list was formed", true, this.personSystem.getPersons());
        } catch (Exception e){
            return new BaseResponse(e.getMessage(), false);
        }
    }

    @RequestMapping(value = "/getPersonsSort",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BaseResponse getPersonsSort() {
        try{
            return new GetPersonsResponse("The list was formed", true, this.personSystem.getPersonsSort());
        } catch (Exception e){
            return new BaseResponse(e.getMessage(), false);
        }
    }

    @RequestMapping(value = "/addPerson",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BaseResponse addPerson(@RequestBody AddPersonRequest person) {
        try{
            this.personSystem.addPerson(person.getLastName(), person.getFirstName(), person.getPatronymic(), person.getDateOfBirth(), person.getPersonGender());
            return new BaseResponse("The person was added", true);
        } catch (Exception e){
            return new BaseResponse(e.getMessage(), false);
        }
    }

    @RequestMapping(value = "/deletePerson",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BaseResponse deletePerson(@RequestBody int id) {
        try{
            this.personSystem.deletePerson(id);
            return new BaseResponse("The person was deleted", true);
        } catch (Exception e){
            return new BaseResponse(e.getMessage(), false);
        }
    }

    @RequestMapping(value = "/updatePerson",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BaseResponse updatePerson(@RequestBody UpdatePersonRequest person) {
        try{
            this.personSystem.updatePerson(person.getId(), person.getLastName(), person.getFirstName(), person.getPatronymic(), person.getDateOfBirth(), person.getPersonGender());
            return new BaseResponse("The person was updated", true);
        } catch (Exception e){
            return new BaseResponse(e.getMessage(), false);
        }
    }
}
