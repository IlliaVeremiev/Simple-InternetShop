import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

import Catalog from 'pages/Catalog.vue'
import Cart from 'pages/Cart.vue'
import Login from 'pages/Login.vue'
import Logout from 'pages/Logout.vue'
import Registration from 'pages/Registration.vue'

import NotFound from 'pages/error/NotFound.vue'

Vue.directive('title', {
    inserted: (el, binding) => document.title = binding.value,
    update: (el, binding) => document.title = binding.value
})

const routes = [{
    path: '/catalog/:page',
    name: 'catalog',
    component: Catalog
},
{
    path: '/cart',
    name: 'cart',
    component: Cart,
    meta: {
        requiresAuth: true
    }
},
{
    path: '/login',
    name: 'login',
    component: Login
},
{
    path: '/logout',
    name: 'logout',
    component: Logout
},
{
    path: '/registration',
    name: 'registration',
    component: Registration
},
{
    path: '/404',
    name: '404',
    component: NotFound,
},
{
    path: '/',
    redirect: 'catalog/1'
},
{
    path: '*',
    redirect: '/404'
}]

const router = new VueRouter({
    mode: 'history',
    routes
});


export default router