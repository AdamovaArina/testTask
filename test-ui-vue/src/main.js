import PrimeVue from 'primevue/config'
import Vue from 'vue'
import App from './App.vue'

Vue.use(PrimeVue);
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
