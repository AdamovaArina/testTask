package controllers;

import beans.Person;
import beans.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import requests.AddPersonRequest;
import requests.UpdatePersonRequest;
import responses.BaseResponse;
import responses.GetPersonsResponse;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/getPersons",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public GetPersonsResponse getPersons() {
        try{
            return new GetPersonsResponse("The list was formed", true, personService.getPersons());
        } catch (Exception e){
            e.printStackTrace();
            return new GetPersonsResponse(e.getMessage(), false, null);
        }
    }

    @RequestMapping(value = "/getPersonsSort",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public GetPersonsResponse getPersonsSort() {
        try{
            return new GetPersonsResponse("The list was formed", true, personService.getPersonsSort());
        } catch (Exception e){
            e.printStackTrace();
            return new GetPersonsResponse(e.getMessage(), false, null);
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
            personService.addPerson(myPerson);
            return new BaseResponse("The person was added", true);
        } catch (Exception e){
            e.printStackTrace();
            return new BaseResponse(e.getMessage(), false);
        }
    }

    @RequestMapping(value = "/deletePerson",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BaseResponse deletePerson(@RequestBody int id) {
        try{
            personService.deletePerson(id);
            return new BaseResponse("The person was deleted", true);
        } catch (Exception e){
            e.printStackTrace();
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
            personService.updatePerson(myPerson);
            return new BaseResponse("The person was updated", true);
        } catch (Exception e){
            e.printStackTrace();
            return new BaseResponse(e.getMessage(), false);
        }
    }
}
