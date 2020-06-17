import Vue from 'vue'
import Vuex from 'vuex'
import AuthenticationApi from 'api/authentication.js'
import CartApi from 'api/cart.js'
import CatalogApi from 'api/catalog.js'
import CategoryApi from 'api/category.js'
import NotAuthorizedError from 'errors/NotAuthorizedError.js'
import { Const } from 'properties.js'

Vue.use(Vuex)

import cookies from 'vue-cookies'

export default new Vuex.Store({
    state: {
        authentication: {
            user: null
        },
        csrf: '',
        cart: {
            entries: [],
            id: 0
        },
        catalog: {
            products: [],
            pagesCount: 0,
            categories: []
        }
    },
    getters: {
        getAuthentication: state => state.authentication,
        isAuthorized: state => state.authentication.user != null,
        getCart: state => state.cart,
        csrf: (state) => state.csrf,
        getCart: (state) => state.cart,
        currentLocale: () => cookies.get("locale") || Const.DefaultLocale
    },
    mutations: {
        setCSRF(state, csrf) {
            state.csrf = csrf;
        },
        setUser(state, user) {
            state.authentication.user = user;
        },
        setCart(state, cart) {
            state.cart = cart;
        },
        setCatalogData(state, data) {
            state.catalog.products = data.content;
            state.catalog.pagesCount = data.totalPages;
        },
        setCategories(state, data) {
            state.catalog.categories = data;
        },
        setLocale(state, locale) {
            cookies.set("locale", locale, Infinity);
        }
    },
    actions: {
        async updateUserInfo(context) {
            try {
                var result = await AuthenticationApi.getCurrentUser();
                const data = await result.json();
                context.commit("setUser", data);
            } catch (error) {
                context.commit("setUser", null);
            }
        },
        async logout(context) {
            await context.dispatch('updateCSRF');
            await AuthenticationApi.logout({ _csrf: context.getters.csrf });
            context.commit("setUser", null);
        },
        async login(context, data) {
            data._csrf = await context.dispatch('updateCSRF');
            await AuthenticationApi.login(data);
            await context.dispatch('updateUserInfo');
        },
        async registrate(context, data) {
            data._csrf = await context.dispatch('updateCSRF');
            await AuthenticationApi.registrate(data);
            await context.dispatch('login', data);
        },
        async updateCSRF({ state }) {
            return state.csrf = cookies.get("XSRF-TOKEN");
        },
        async updateCart(context) {
            const result = await CartApi.getCart();
            const data = await result.json();
            context.commit('setCart', data);
        },
        async setCartProductCount(context, data) {
            const csrf = await context.dispatch('updateCSRF');
            await CartApi.setProductCount({ productId: data.product.id, count: data.count, _csrf: csrf });
        },
        async removeCartProduct(context, product){
            const csrf = await context.dispatch('updateCSRF');
            await CartApi.removeProduct({ productId: product.id, _csrf: csrf });
        },
        async addToCart(context, product) {
            const csrf = await context.dispatch('updateCSRF');
            if (context.getters.isAuthorized) {
                await CartApi.addProduct({ productId: product.id, _csrf: csrf });
            } else {
                throw new NotAuthorizedError("Required authorization before add item to cart");
            }
        },
        async loadCatalogPage(context, params) {
            const result = await CatalogApi.loadCatalog(params);
            const data = await result.json();
            console.log(data);
            context.commit('setCatalogData', data);
        },
        async loadAllCategories(context) {
            const result = await CategoryApi.getAll();
            const data = await result.json();
            context.commit('setCategories', data);
        },
        setLocale(context, locale) {
            context.commit("setLocale", locale);
        }
    }
})