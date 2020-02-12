import Vue from 'vue'
import VueResource from 'vue-resource'
Vue.use(VueResource)

const catalogEndpoint = Vue.resource('/api/products')

export default {
    loadCatalog: (params) => catalogEndpoint.get(params)
}