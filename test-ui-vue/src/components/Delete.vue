<template>
  <div>
    <Dialog :visible.sync="isDelete" v-on:hide="cancelDeletePerson()" class="delete-dialog">
      <template #header>
        <h3>Удалить человека {{model.lastName}} {{model.firstName}}
          {{model.patronymic}}?</h3>
      </template>
      <div></div>
      <template #footer class="buttons-block">
        <Button v-on:click="cancelDeletePerson()" label="Отмена" icon="pi pi-times" class="p-button-text"/>
        <Button v-on:click="deletePerson()" label="Удалить" icon="pi pi-check" autofocus/>
      </template>
    </Dialog>
  </div>
</template>

<script>
import Button from 'primevue/button';
import Dialog from 'primevue/dialog';
import Utils from './utils.js';

export default {
  name: "Delete",
  data: function(){
    return {
      isDelete: false,
    }
  },
  props: {
    model: Object
  },
  components: {Button, Dialog},
  methods:{
    openDelete: function (){
      this.isDelete = true
    },

    deletePerson: function(){
      Utils.sendAjaxRequest("/person/deletePerson", "DELETE", JSON.stringify(this.model.id),
          function(){this.$emit('get-persons')}.bind(this))
      this.isDelete = false;
    },

    cancelDeletePerson: function(){
      this.isDelete = false;
    },
  }
}
</script>

<style scoped>

</style>