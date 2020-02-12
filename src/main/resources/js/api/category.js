import Vue from 'vue'
import VueResource from 'vue-resource'
Vue.use(VueResource)

const categoriesEndpoint = Vue.resource('/api/categories')

export default {
    getAll: () => categoriesEndpoint.get()
}