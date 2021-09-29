<template>
  <div>
    <Dialog :visible.sync="isActive" class="add-dialog">
      <template #header>
        <h3>Добавить человека</h3>
      </template>
        <p><div class="family">Фамилия: </div><InputText type="text" v-model="addLastName" />
        <p><div class="family">Имя: </div><InputText type="text" v-model="addFirstName" />
        <p><div class="family">Отчество: </div><InputText type="text" v-model="addPatronymic" />
        <p><div class="family">Дата рождения: </div>
          <Calendar id="navigators" v-model="addDateOfBirth" :monthNavigator="true" :yearNavigator="true"
                    yearRange="1800:2030" dateFormat="yy-mm-dd" class="add-calendar"/>
        <br>&nbsp;
        <p><div class="family">Пол: </div><div class="p-field-radiobutton" style="display: inline-block">
          <RadioButton id="g1" name="addPersonGender" value="MALE" v-model="addPersonGender" />
          <label for="g1">&nbsp; муж &nbsp;</label>
        </div>
        <div class="p-field-radiobutton" style="display: inline-block">
          <RadioButton id="g2" name="addPersonGender" value="FEMALE" v-model="addPersonGender" />
          <label for="g2">&nbsp; жен</label>
        </div>
      <template #footer class="buttons-block">
        <Button v-on:click="cancelAddPerson()" label="Отмена" icon="pi pi-times" class="p-button-text"/>
        <Button v-on:click="addPerson" label="Добавить" icon="pi pi-check" autofocus/>
      </template>
    </Dialog>

    <Dialog :visible.sync="isWarning" class="delete-dialog">
      <template #header>
        <h3>Удалить человека {{persons[currentIndex].lastName}} {{persons[currentIndex].firstName}}
          {{persons[currentIndex].patronymic}}?</h3>
      </template>
      <div></div>
      <template #footer class="buttons-block">
        <Button v-on:click="cancelDeletePerson()" label="Отмена" icon="pi pi-times" class="p-button-text"/>
        <Button v-on:click="deletePerson()" label="Удалить" icon="pi pi-check" autofocus/>
      </template>
    </Dialog>

    <Dialog :visible.sync="isUpdate" class="update-dialog">
      <template #header>
        <h3>Изменить данные о человеке</h3>
      </template>
      <p><div class="family">id: {{persons[currentIndex].id}}</div>
      <p><div class="family">Фамилия: </div><InputText type="text" v-model="updateLastName" />
      <p><div class="family">Имя: </div><InputText type="text" v-model="updateFirstName" />
      <p><div class="family">Отчество: </div><InputText type="text" v-model="updatePatronymic" />
      <p><div class="family">Дата рождения: </div>
      <Calendar id="navigate" v-model="updateDateOfBirth" :monthNavigator="true" :yearNavigator="true"
                yearRange="1800:2030" dateFormat="yy-mm-dd" class="add-calendar"/>
      <br>&nbsp;
      <p><div class="family">Пол: </div><div class="p-field-radiobutton" style="display: inline-block">
      <RadioButton id="g3" name="addPersonGender" value="MALE" v-model="updatePersonGender" />
      <label for="g3">&nbsp; муж &nbsp;</label>
    </div>
      <div class="p-field-radiobutton" style="display: inline-block">
        <RadioButton id="g4" name="addPersonGender" value="FEMALE" v-model="updatePersonGender" />
        <label for="g4">&nbsp; жен</label>
      </div>
      <template #footer class="buttons-block">
        <Button v-on:click="cancelUpdatePerson()" label="Отмена" icon="pi pi-times" class="p-button-text"/>
        <Button v-on:click="updatePerson()" label="Изменить" icon="pi pi-check" autofocus/>
      </template>
    </Dialog>

    <DataTable :value = "persons" class="box" autoLayout="true">
      <Column field="id" header="id" bodyClass="cell" headerClass="header"></Column>
      <Column field="lastName" header="Фамилия" bodyClass="cell" headerClass="header"></Column>
      <Column field="firstName" header="Имя" bodyClass="cell" headerClass="header"></Column>
      <Column field="patronymic" header="Отчество" bodyClass="cell" headerClass="header"></Column>
      <Column field="dateOfBirth" header="Дата рождения" bodyClass="cell" headerClass="header"></Column>
      <Column field="personGender" header="Пол" bodyClass="cell" headerClass="header"></Column>
      <Column header="" bodyClass="cell" headerClass="header">
        <template #body="slotProps">
          <Button type="button" icon="pi pi-trash" v-on:click="showDeletePerson(slotProps.index)" title="изменить" style="margin-right: .5em"></Button>
          <Button type="button" icon="pi pi-pencil" v-on:click="showUpdatePerson(slotProps.index)" title="удалить" ></Button>
        </template>
      </Column>
    </DataTable>

    <br/><Button type="button" v-on:click="getPersons" class="button-manage">Показать в произвольном порядке</Button>
    <br/><Button type="button" v-on:click="getPersonsSort" class="button-manage">Отсортировать по дате рождения</Button>
    <br/><Button type="button" v-on:click="showAddPerson()" class="button-manage">Добавить человека</Button>

  </div>
</template>

<style src="./../style.css"></style>

<script>
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import Button from 'primevue/button';
import Dialog from 'primevue/dialog';
import InputText from 'primevue/inputtext';
import Calendar from 'primevue/calendar';
import RadioButton from 'primevue/radiobutton';

import "primevue/resources/themes/saga-blue/theme.css"
import "primevue/resources/primevue.min.css"
import "primeicons/primeicons.css"

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
  config: {},
  components: {DataTable, Column, Button, Dialog, InputText, Calendar, RadioButton},

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
      this.currentIndex = index
      this.isWarning = true
      this.isUpdate = false
      this.cancelAddPerson()
      console.log(index)
    },

    deletePerson: function(){
      var index = this.currentIndex
      this.currentIndex = 0
      this.sendAjaxRequest("/person/deletePerson", "DELETE", JSON.stringify(this.persons[index].id),
          function (){this.sendAjaxRequest("/person/getPersons", "GET", null, this.successGetPersons)}.bind(this))
      this.isWarning = false
    },

    cancelDeletePerson: function(){
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

<style scoped>

/deep/ .p-dialog-footer {
  text-align: justify !important;
  text-align-last: justify !important;
}

</style>


