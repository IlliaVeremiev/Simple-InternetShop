import Vue from 'vue'
import VueResource from 'vue-resource'
Vue.use(VueResource)

var actions = {
    current: { method: 'GET', url: '/api/cart' }
}

const cartEndpoint = Vue.resource('/api/cart{/productId}', {}, actions)

export default {
    getCart: () => cartEndpoint.current(),
    addProduct: (data) => cartEndpoint.save(data, {}),
    setProductCount: (data) => cartEndpoint.update(data, {}),
    removeProduct: (data) => cartEndpoint.delete(data, {})
}