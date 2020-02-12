import Vue from 'vue'
import VueI18n from 'vue-i18n'
Vue.use(VueI18n);

import { en } from 'plugins/localization/translations/en';
import { ru } from 'plugins/localization/translations/ru';
import { Const } from 'properties.js'

export const i18n = new VueI18n({
    locale: $cookies.get('locale'),
    fallbackLocale: Const.DefaultLocale,
    messages: { en, ru }
});

