import Vue from 'vue'
import VueResource from 'vue-resource'
Vue.use(VueResource)

var actions = {
    login: { method: 'POST', url: '/login' },
    logout: { method: 'POST', url: '/logout' },
    current: { method: 'GET', url: '/api/users/me' }
}

const authenticationEndpoint = Vue.resource('/api/users', {}, actions)

export default {
    getCurrentUser: () => authenticationEndpoint.current(),
    logout: (data) => authenticationEndpoint.logout(data, {}),
    login: (data) => authenticationEndpoint.login(data, {}),
    registrate: (data) => authenticationEndpoint.save(data, {})
}