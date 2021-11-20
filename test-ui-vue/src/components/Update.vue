<template>
  <div>
    <Dialog :visible.sync="isUpdate" v-on:hide="cancelUpdatePerson()" class="update-dialog">
      <template #header>
        <h3>Изменить данные о человеке</h3>
      </template>
      <p><div class="family">id: {{update.id}}</div>
      <p><div class="family">Фамилия: </div><InputText type="text" v-model="update.lastName" />
      <p><div class="family">Имя: </div><InputText type="text" v-model="update.firstName" />
      <p><div class="family">Отчество: </div><InputText type="text" v-model="update.patronymic" />
      <p><div class="family">Дата рождения: </div>
      <Calendar id="navigate" v-model="update.dateOfBirth" :monthNavigator="true" :yearNavigator="true"
                yearRange="1800:2030" dateFormat="yy-mm-dd" class="add-calendar"/>
      <br>&nbsp;
      <p><div class="family">Пол: </div><div class="p-field-radiobutton" style="display: inline-block">
      <RadioButton id="g3" name="addPersonGender" value="MALE" v-model="update.personGender" />
      <label for="g3">&nbsp; муж &nbsp;</label>
    </div>
      <div class="p-field-radiobutton" style="display: inline-block">
        <RadioButton id="g4" name="addPersonGender" value="FEMALE" v-model="update.personGender" />
        <label for="g4">&nbsp; жен</label>
      </div>
      <template #footer class="buttons-block">
        <Button v-on:click="cancelUpdatePerson()" label="Отмена" icon="pi pi-times" class="p-button-text"/>
        <Button v-on:click="updatePerson()" label="Изменить" icon="pi pi-check" autofocus/>
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
  name: "Update",
  props: {
    isUpdate: Boolean,
    update: Object
  },
  components: {Button, Dialog, InputText, Calendar, RadioButton},
  methods:{
    updatePerson: function(){
      Utils.sendAjaxRequest("/person/updatePerson", "PUT",
          JSON.stringify({id: this.update.id, lastName: this.update.lastName,
            firstName: this.update.firstName, patronymic: this.update.patronymic,
            dateOfBirth: this.update.dateOfBirth, personGender: this.update.personGender}),
          function(){this.$emit('get-persons')}.bind(this))
      this.cancelUpdatePerson()
    },

    cancelUpdatePerson: function(){
      this.update.lastName = ""
      this.update.firstName = ""
      this.update.patronymic = ""
      this.update.dateOfBirth = null
      this.update.personGender = null
      this.$emit('close-update')
    },
  }
}
</script>

<style scoped>

</style>