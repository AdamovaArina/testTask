<template>
  <div>
    <Dialog :visible.sync="isWarning" v-on:hide="cancelDeletePerson()" class="delete-dialog">
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

export default {
  name: "Delete",
  props: {
    isWarning: Boolean,
    model: Object
  },
  components: {Button, Dialog},
  methods:{
    deletePerson: function(){
      this.sendAjaxRequest("/person/deletePerson", "DELETE", JSON.stringify(this.model.id),
          function(){this.$emit('get-persons')}.bind(this))
      this.$emit('close-delete')
    },

    cancelDeletePerson: function(){
      this.$emit('close-delete')
    },

    sendAjaxRequest: function(url, httpMethod, data, successCallback) {
      var request = new XMLHttpRequest();
      request.open(httpMethod, url);
      request.setRequestHeader('Content-Type', 'application/json');
      request.addEventListener('readystatechange', () => {
        if (request.readyState === 4 && request.status === 200) {
          if (JSON.parse(request.responseText).status === true){
            successCallback(JSON.parse(request.responseText));
          }
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

</style>