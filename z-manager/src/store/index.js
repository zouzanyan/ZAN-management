import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex)

// 全局共享变量
export default new Vuex.Store({
    state: {
        // 后端url
        globalhost: 'http://192.168.1.27:8181/',

    }, mutations: {


    }, action: {}, modules: {}, computed: {

    }
})