<template>
  <div>
    <Dialog :visible.sync="isAdd" v-on:hide="cancelAddPerson()" class="add-dialog">
      <template #header>
        <h3>Добавить человека</h3>
      </template>
      <p><div class="family">Фамилия: </div><InputText type="text" v-model="add.lastName" />
      <p><div class="family">Имя: </div><InputText type="text" v-model="add.firstName" />
      <p><div class="family">Отчество: </div><InputText type="text" v-model="add.patronymic" />
      <p><div class="family">Дата рождения: </div>
      <Calendar id="navigators" v-model="add.dateOfBirth" :monthNavigator="true" :yearNavigator="true"
                yearRange="1800:2030" dateFormat="yy-mm-dd" class="add-calendar"/>
      <br>&nbsp;
      <p><div class="family">Пол: </div><div class="p-field-radiobutton" style="display: inline-block">
      <RadioButton id="g1" name="addPersonGender" value="MALE" v-model="add.personGender" />
      <label for="g1">&nbsp; муж &nbsp;</label>
    </div>
      <div class="p-field-radiobutton" style="display: inline-block">
        <RadioButton id="g2" name="addPersonGender" value="FEMALE" v-model="add.personGender" />
        <label for="g2">&nbsp; жен</label>
      </div>
      <template #footer class="buttons-block">
        <Button v-on:click="cancelAddPerson()" label="Отмена" icon="pi pi-times" class="p-button-text"/>
        <Button v-on:click="addPerson" label="Добавить" icon="pi pi-check" autofocus/>
      </template>
    </Dialog>
  </div>
</template>

<script>
import Button from 'primevue/button';
import Dialog from 'primevue/dialog';
import InputText from 'primevue/inputtext';
import Calendar from 'primevue/calendar';
import RadioButton from 'primevue/radiobutton';
import Utils from './utils.js';

class ChangePerson{
  constructor(lastName, firstName, patronymic, dateOfBirth, personGender){
    this.lastName = lastName;
    this.firstName = firstName;
    this.patronymic = patronymic;
    this.dateOfBirth = dateOfBirth;
    this.personGender = personGender;
  }
}

export default {
  name: "Add",
  data: function(){
    return {
      isAdd: false,
      add: new ChangePerson()
    }
  },

  components: {Button, Dialog, InputText, Calendar, RadioButton},
  methods:{
    openAdd: function (){
      this.isAdd = true
    },

    addPerson: function (){
      Utils.sendAjaxRequest("/person/addPerson", "POST",
          JSON.stringify({lastName: this.add.lastName, firstName: this.add.firstName, patronymic: this.add.patronymic,
            dateOfBirth: this.add.dateOfBirth, personGender: this.add.personGender}),
          function(){this.$emit('get-persons')}.bind(this))
      this.cancelAddPerson()
    },

    cancelAddPerson: function(){
      this.add.lastName = ""
      this.add.firstName = ""
      this.add.patronymic = ""
      this.add.dateOfBirth = null
      this.add.personGender = null
      this.isAdd = false
    }
  }
}
</script>

<style scoped>

</style>