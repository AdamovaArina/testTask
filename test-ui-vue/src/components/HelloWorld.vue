<template>
  <div>
    <div class="window add-window" v-if="isActive" v-bind:class="{active: isActive}">
      <div class="window-title">Добавить человека</div>
      <p><div class="family">Фамилия: </div><input type="text" v-model="addLastName">
      <p><div class="family">Имя: </div><input type="text" v-model="addFirstName">
      <p><div class="family">Отчество: </div><input type="text" v-model="addPatronymic">
      <p><div class="family">Дата рождения: </div><input type="date" v-model="addDateOfBirth">
      <p><div class="family">Пол: </div><input type="radio" value="MALE" v-model="addPersonGender">
      <label>муж</label>
      <input type="radio" value="FEMALE" v-model="addPersonGender">
      <label>жен</label>
      <div class="window-buttons-block">
        <button class="button"  v-on:click="cancelAddPerson()">Отмена</button>
        <button class="button"  v-on:click="addPerson">Добавить</button>
      </div>
    </div>

    <div class="window delete-window" v-if="isWarning" v-bind:class="{active: isWarning}">
      <p>Удалить человека {{persons[currentIndex].lastName}} {{persons[currentIndex].firstName}}
        {{persons[currentIndex].patronymic}}?</p>
      <div class="window-buttons-block">
        <button class="button"  v-on:click="isWarning=false">Отмена</button>
        <button class="button"  v-on:click="deletePerson()">Ок</button>
      </div>
    </div>

    <div class="window update-window" v-if="isUpdate" v-bind:class="{active: isUpdate}">
      <div class="window-title">Изменить человека</div>
      <p><div class="family">id: {{persons[currentIndex].id}}</div>
      <p><div class="family">Фамилия: </div><input type="text" v-model="updateLastName">
      <p><div class="family">Имя: </div><input type="text" v-model="updateFirstName">
      <p><div class="family">Отчество: </div><input type="text" v-model="updatePatronymic">
      <p><div class="family">Дата рождения: </div><input type="date" v-model="updateDateOfBirth">
      <p><div class="family">Пол: </div><input type="radio" value="MALE" v-model="updatePersonGender">
      <label>муж</label>
      <input type="radio" value="FEMALE" v-model="updatePersonGender">
      <label>жен</label>
      <div class="window-buttons-block">
        <button class="button"  v-on:click="cancelUpdatePerson()">Отмена</button>
        <button class="button"  v-on:click="updatePerson()">Изменить</button>
      </div>
    </div>

    <div class="table">
      <div class="table-title">
        <div class="line">id</div>
        <div class="line">Фамилия</div>
        <div class="line">Имя</div>
        <div class="line">Отчество</div>
        <div class="line">Дата рождения</div>
        <div class="line">Пол</div>
        <div class="line"> </div>
      </div>
      <div v-for="(person, index) in persons" class="table-row" :key="person.id">
        <div class="line">{{person.id}}</div>
        <div class="line">{{person.lastName}}</div>
        <div class="line">{{person.firstName}}</div>
        <div class="line">{{person.patronymic}}</div>
        <div class="line">{{person.dateOfBirth}}</div>
        <div class="line">{{person.personGender}}</div>
        <div class="line">
          <div class="update-buttons-block">
            <button v-on:click="showDeletePerson(index)" title="удалить" class="button">×</button>
            <button v-on:click="showUpdatePerson(index)" title="изменить" class="button">✎</button>
          </div>
        </div>
      </div>
    </div>
    <div class="buttons-block">
      <button v-on:click="getPersons" class="button">Показать в произвольном порядке</button>
      <button v-on:click="getPersonsSort" class="button">Отсортировать по дате рождения</button>
      <button v-on:click="showAddPerson()" class="button add-button">Добавить человека</button>
    </div>
  </div>
</template>

<style src="./../style.css"></style>

<script>
export default {
  name: 'HelloWorld',
  data: function(){
    return {
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
    }
  },

  created() {
    this.getPersons();
  },

  methods: {
    getPersons: function(){
      this.sendAjaxRequest("/person/getPersons", "GET", null, this.successGetPersons)
    },

    getPersonsSort: function(){
      this.sendAjaxRequest("/person/getPersonsSort", "GET", null, this.successGetPersons)
    },

    showAddPerson: function(){
      this.isActive = true
      this.isWarning = false
    },

    addPerson: function (){
      this.sendAjaxRequest("/person/addPerson", "POST",
          JSON.stringify({lastName: this.addLastName, firstName: this.addFirstName, patronymic: this.addPatronymic,
            dateOfBirth: this.addDateOfBirth, personGender: this.addPersonGender}),
          function (){this.sendAjaxRequest("/person/getPersons", "GET", null, this.successGetPersons)}.bind(this))
      this.cancelAddPerson()
    },

    cancelAddPerson: function(){
      this.addLastName = ""
      this.addFirstName = ""
      this.addPatronymic = ""
      this.addDateOfBirth = null
      this.addPersonGender = null
      this.isActive = false
    },

    showDeletePerson: function(index){
      this.isWarning = true
      this.isUpdate = false
      this.cancelAddPerson()
      this.currentIndex = index
    },

    deletePerson: function(){
      var index = this.currentIndex
      this.currentIndex = 0
      this.sendAjaxRequest("/person/deletePerson", "DELETE", JSON.stringify(this.persons[index].id),
          function (){this.sendAjaxRequest("/person/getPersons", "GET", null, this.successGetPersons)}.bind(this))
      this.isWarning = false
    },

    showUpdatePerson: function(index){
      this.isWarning = false
      this.isUpdate = true
      this.currentIndex = index
      this.updateLastName =  this.persons[index].lastName
      this.updateFirstName =  this.persons[index].firstName
      this.updatePatronymic =  this.persons[index].patronymic
      var parts = this.persons[index].dateOfBirth.split('-')
      this.updateDateOfBirth = parts[2] + "-" + parts[1] + "-" + parts[0]
      if (this.persons[index].personGender == "м"){
        this.updatePersonGender = "MALE"
      } else {
        this.updatePersonGender = "FEMALE"
      }
    },

    updatePerson: function(){
      this.sendAjaxRequest("/person/updatePerson", "PUT",
          JSON.stringify({id: this.persons[this.currentIndex].id, lastName: this.updateLastName,
            firstName: this.updateFirstName, patronymic: this.updatePatronymic,
            dateOfBirth: this.updateDateOfBirth, personGender: this.updatePersonGender}),
          function (){this.sendAjaxRequest("/person/getPersons", "GET", null, this.successGetPersons)}.bind(this))
      this.cancelUpdatePerson()
    },

    cancelUpdatePerson: function(){
      this.updateLastName = ""
      this.updateFirstName = ""
      this.updatePatronymic = ""
      this.updateDateOfBirth = null
      this.updatePersonGender = null
      this.isUpdate = false
    },

    successGetPersons: function(response) {
      this.persons = response.data
      console.log(response)
    },

    sendAjaxRequest: function(url, httpMethod, data, successCallback) {
      var request = new XMLHttpRequest();
      request.open(httpMethod, url);
      request.setRequestHeader('Content-Type', 'application/json');
      request.addEventListener('readystatechange', () => {
        if (request.readyState === 4 && request.status === 200) {
          successCallback(JSON.parse(request.responseText));
        }
      });
      if (data != null) {
        request.send(data);
      }
      else {
        request.send();
      }
    },
  }
}
</script>


