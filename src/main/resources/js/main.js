import Vue from 'vue'
import '@babel/polyfill'
import VueResource from 'vue-resource'
Vue.use(VueResource)
import ClientApp from 'apps/ClientApp.vue'
import VueCookies from 'vue-cookies'
Vue.use(VueCookies)
import router from 'routes/router'
import store from 'store/store'
import { i18n } from 'plugins/localization/i18n'
import { ToastsPlugin } from 'plugins/toasts/index.js'
Vue.use(ToastsPlugin)
import { ModalPlugin } from 'plugins/modal/index.js'
Vue.use(ModalPlugin)
import { TimerPlugin } from 'plugins/timer/index.js'
Vue.use(TimerPlugin)

const app = new Vue({
    render: a => a(ClientApp),
    router,
    i18n,
    store
});
app.$mount('#app');