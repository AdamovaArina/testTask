<template>
  <div>
      <AddUpdate
        v-bind:addUpdate="add"
        v-bind:flagUpdate="false"
        v-on:get-persons="getPersons()"
        ref="add"
      ></AddUpdate>

      <AddUpdate
          v-bind:addUpdate="update"
          v-bind:flagUpdate="true"
          v-on:get-persons="getPersons()"
          ref="update"
      ></AddUpdate>

      <Delete
        v-bind:model="model"
        v-on:get-persons="getPersons()"
        ref="delete"
      ></Delete>

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

import AddUpdate from './AddUpdate'
import Delete from './Delete'

import "primevue/resources/themes/saga-blue/theme.css"
import "primevue/resources/primevue.min.css"
import "primeicons/primeicons.css"

import Utils from './utils.js';

class ChangePerson{
  constructor(id, lastName, firstName, patronymic, dateOfBirth, personGender){
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.patronymic = patronymic;
    this.dateOfBirth = dateOfBirth;
    this.personGender = personGender;
  }
}

export default {
  name: 'HelloWorld',
  data: function(){
    return {
      persons: [],
      currentIndex: 0,

      add: new ChangePerson(),
      update: new ChangePerson(),
      model: new ChangePerson()
    }
  },
  components: {DataTable, Column, Button, AddUpdate, Delete},

  created() {
    this.getPersons();
  },

  methods: {
    getPersons: function(){
      Utils.sendAjaxRequest("/person/getPersons", "GET", null, this.successGetPersons)
    },

    getPersonsSort: function(){
      Utils.sendAjaxRequest("/person/getPersonsSort", "GET", null, this.successGetPersons)
    },

    showAddPerson: function(){
      this.$refs.add.openAddUpdate();
      this.$refs.delete.cancelDeletePerson()
    },

    showDeletePerson: function(index){
      this.currentIndex = index
      this.$refs.delete.openDelete()
      this.$refs.add.cancelAddUpdatePerson()
      this.$refs.update.cancelAddUpdatePerson()
      this.model.id =  this.persons[index].id
      this.model.lastName =  this.persons[index].lastName
      this.model.firstName =  this.persons[index].firstName
      this.model.patronymic =  this.persons[index].patronymic
      this.currentIndex = 0
      console.log(index)
    },

    showUpdatePerson: function(index){
      this.$refs.delete.cancelDeletePerson()
      this.$refs.update.openAddUpdate();
      this.currentIndex = index
      this.update.id =  this.persons[index].id
      this.update.lastName =  this.persons[index].lastName
      this.update.firstName =  this.persons[index].firstName
      this.update.patronymic =  this.persons[index].patronymic
      var parts = this.persons[index].dateOfBirth.split('-')
      this.update.dateOfBirth = parts[2] + "-" + parts[1] + "-" + parts[0]
      if (this.persons[index].personGender === "м"){
        this.update.personGender = "MALE"
      } else {
        this.update.personGender = "FEMALE"
      }
    },

    successGetPersons: function(response) {
      this.persons = [];
      for (var i = 0; i < response.data.length; i++){
        var pers = {};
        pers.id = response.data[i].id;
        pers.lastName = response.data[i].lastName;
        pers.firstName = response.data[i].firstName;
        pers.patronymic = response.data[i].patronymic;
        var date = new Date(response.data[i].dateOfBirth);
        var y = date.getFullYear();
        var m1 = date.getMonth() + 1;
        var m = {};
        if (m1 < 10){
          m = '0' + m1;
        } else {
          m = m1;
        }
        var d1 = date.getDate();
        var d = {};
        if (d1 < 10){
          d = '0' + d1;
        } else {
          d = d1;
        }
        pers.dateOfBirth = d + '-' + m + '-' + y;
        if (response.data[i].personGender === 'MALE'){
          pers.personGender = 'м'
        } else {
          pers.personGender = 'ж'
        }
        this.persons.push(pers);
      }
      console.log(response)
    },
  }
}
</script>
<style scoped>
</style>


