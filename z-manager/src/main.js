import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import axios from 'axios';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/global.css'
import store from './store'

Vue.config.productionTip = false
window.axios = axios
window.axios.defaults.withCredentials = true
Vue.use(ElementUI, {size: 'small', zIndex: 3000});

//
// console.log('********* Cookie *************')
// console.log(document.cookie)


new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')



