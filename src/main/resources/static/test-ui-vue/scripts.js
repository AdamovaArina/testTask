function sendAjaxRequest(url, httpMethod, data, successCallback) {
    $.ajax(url, {
        type: httpMethod,
        data: data,
        success: successCallback,
        contentType: 'application/json'
    });
}

var app = new Vue({
    el: '#app',
    data: {
        persons: [],
        isActive: false,
        isWarning: false,
        isUpdate: false,
        currentIndex: 0,

        addLastName: "",
        addFirstName: "",
        addPatronymic: "",
        addDateOfBirth: null,
        addPersonGender: null,

        updateLastName: "",
        updateFirstName: "",
        updatePatronymic: "",
        updateDateOfBirth: null,
        updatePersonGender: null
    },
    methods: {
        getPersons: function(){
            sendAjaxRequest("/person/getPersons", "GET", null, successGetPersons)
        },

        getPersonsSort: function(){
            sendAjaxRequest("/person/getPersonsSort", "GET", null, successGetPersons)
        },

        showAddPerson: function(){
            app.isActive = true
            app.isWarning = false
        },

        addPerson: function (){
            sendAjaxRequest("/person/addPerson", "POST",
                JSON.stringify({lastName: app.addLastName, firstName: app.addFirstName, patronymic: app.addPatronymic,
                    dateOfBirth: app.addDateOfBirth, personGender: app.addPersonGender}),
                function (){sendAjaxRequest("/person/getPersons", "GET", null, successGetPersons)})
            this.cancelAddPerson()
        },

        cancelAddPerson: function(){
            app.addLastName = ""
            app.addFirstName = ""
            app.addPatronymic = ""
            app.addDateOfBirth = null
            app.addPersonGender = null
            app.isActive = false
        },

        showDeletePerson: function(index){
            app.isWarning = true
            app.isUpdate = false
            this.cancelAddPerson()
            app.currentIndex = index
        },

        deletePerson: function(){
            var index = app.currentIndex
            app.currentIndex = 0
            sendAjaxRequest("/person/deletePerson", "DELETE", JSON.stringify(app.persons[index].id),
                function (){sendAjaxRequest("/person/getPersons", "GET", null, successGetPersons)})
            app.isWarning = false
        },

        showUpdatePerson: function(index){
            app.isWarning = false
            app.isUpdate = true
            app.currentIndex = index
            app.updateLastName =  app.persons[index].lastName
            app.updateFirstName =  app.persons[index].firstName
            app.updatePatronymic =  app.persons[index].patronymic
            var parts = app.persons[index].dateOfBirth.split('-')
            app.updateDateOfBirth = parts[2] + "-" + parts[1] + "-" + parts[0]
            if (app.persons[index].personGender == "м"){
                app.updatePersonGender = "MALE"
            } else {
                app.updatePersonGender = "FEMALE"
            }
        },

        updatePerson: function(){
            sendAjaxRequest("/person/updatePerson", "PUT",
                JSON.stringify({id: app.persons[app.currentIndex].id, lastName: app.updateLastName,
                    firstName: app.updateFirstName, patronymic: app.updatePatronymic,
                    dateOfBirth: app.updateDateOfBirth, personGender: app.updatePersonGender}),
                function (){sendAjaxRequest("/person/getPersons", "GET", null, successGetPersons)})
            this.cancelUpdatePerson()
        },

        cancelUpdatePerson: function(){
            app.updateLastName = ""
            app.updateFirstName = ""
            app.updatePatronymic = ""
            app.updateDateOfBirth = null
            app.updatePersonGender = null
            app.isUpdate = false
        }
    }
});

function successGetPersons(response) {
    app.persons = response.data
    console.log(response)
}
sendAjaxRequest("/person/getPersons", "GET", null, successGetPersons)