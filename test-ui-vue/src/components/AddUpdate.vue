<template>
  <div>
    <Dialog :visible.sync="isAddUpdate" v-on:hide="cancelAddUpdatePerson()"
            v-bind:class="{'update-dialog': flagUpdate, 'add-dialog': !flagUpdate}">
      <template #header>
        <h3 v-if="flagUpdate">Изменить данные о человеке</h3>
        <h3 v-else>Добавить человека</h3>
      </template>
      <p><div class="family" v-if="flagUpdate">id: {{addUpdate.id}}</div>
      <p><div class="family">Фамилия: </div><InputText type="text" v-model="addUpdate.lastName" />
      <p><div class="family">Имя: </div><InputText type="text" v-model="addUpdate.firstName" />
      <p><div class="family">Отчество: </div><InputText type="text" v-model="addUpdate.patronymic" />
      <p><div class="family">Дата рождения: </div>
      <Calendar id="navigate" v-model="addUpdate.dateOfBirth" :monthNavigator="true" :yearNavigator="true"
                yearRange="1800:2030" dateFormat="yy-mm-dd" class="add-calendar"/>
      <br>&nbsp;
      <p><div class="family">Пол: </div><div class="p-field-radiobutton" style="display: inline-block">
      <RadioButton id="g3" name="addPersonGender" value="MALE" v-model="addUpdate.personGender" />
      <label for="g3">&nbsp; муж &nbsp;</label>
    </div>
      <div class="p-field-radiobutton" style="display: inline-block">
        <RadioButton id="g4" name="addPersonGender" value="FEMALE" v-model="addUpdate.personGender" />
        <label for="g4">&nbsp; жен</label>
      </div>
      <template #footer class="buttons-block">
        <Button v-on:click="cancelAddUpdatePerson()" label="Отмена" icon="pi pi-times" class="p-button-text"/>
        <Button v-on:click="addUpdatePerson()" label="Изменить" icon="pi pi-check" autofocus v-if="flagUpdate"/>
        <Button v-on:click="addUpdatePerson()" label="Добавить" icon="pi pi-check" autofocus v-else/>
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

export default {
  name: "AddUpdate",
  data: function(){
    return {
      isAddUpdate: false,
    }
  },
  props: {
    addUpdate: Object,
    flagUpdate: Boolean
  },
  components: {Button, Dialog, InputText, Calendar, RadioButton},
  methods:{
    openAddUpdate: function (){
      this.isAddUpdate = true
    },

    addUpdatePerson: function(){
      if (this.flagUpdate === true){
        Utils.sendAjaxRequest("/person/updatePerson", "PUT",
            JSON.stringify({id: this.addUpdate.id, lastName: this.addUpdate.lastName,
              firstName: this.addUpdate.firstName, patronymic: this.addUpdate.patronymic,
              dateOfBirth: this.addUpdate.dateOfBirth, personGender: this.addUpdate.personGender}),
            function(){this.$emit('get-persons')}.bind(this))
      } else {
        Utils.sendAjaxRequest("/person/addPerson", "POST",
            JSON.stringify({lastName: this.addUpdate.lastName, firstName: this.addUpdate.firstName,
              patronymic: this.addUpdate.patronymic, dateOfBirth: this.addUpdate.dateOfBirth,
              personGender: this.addUpdate.personGender}),
            function(){this.$emit('get-persons')}.bind(this))
      }

      this.cancelAddUpdatePerson()
    },

    cancelAddUpdatePerson: function(){
      this.addUpdate.lastName = ""
      this.addUpdate.firstName = ""
      this.addUpdate.patronymic = ""
      this.addUpdate.dateOfBirth = null
      this.addUpdate.personGender = null
      this.isAddUpdate = false
    },
  }
}
</script>

<style scoped>
</style>