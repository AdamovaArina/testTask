package controllers;

import beans.Person;
import beans.PersonSystem;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import requests.AddPersonRequest;
import requests.UpdatePersonRequest;
import responses.BaseResponse;
import responses.GetPersonsResponse;

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
            Person myPerson = Person.builder()
                    .lastName(person.getLastName())
                    .firstName(person.getFirstName())
                    .patronymic(person.getPatronymic())
                    .dateOfBirth(person.getDateOfBirth())
                    .personGender(person.getPersonGender())
                    .build();
            this.personSystem.addPerson(myPerson);
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
            Person myPerson = Person.builder()
                    .id(person.getId())
                    .lastName(person.getLastName())
                    .firstName(person.getFirstName())
                    .patronymic(person.getPatronymic())
                    .dateOfBirth(person.getDateOfBirth())
                    .personGender(person.getPersonGender())
                    .build();
            this.personSystem.updatePerson(myPerson);
            return new BaseResponse("The person was updated", true);
        } catch (Exception e){
            return new BaseResponse(e.getMessage(), false);
        }
    }
}
