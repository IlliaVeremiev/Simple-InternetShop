<template>
    <div class="header">
        <nav class="navbar navbar-expand navbar-dark bg-dark flex-column flex-md-row bd-navbar">
            <router-link class="navbar-brand mr-0 mr-md-2"
                         :to="Links.Catalog">{{ $t('header.logo') }}</router-link>

            <div class="navbar-nav-scroll">
                <ul class="navbar-nav bd-navbar-nav flex-row">
                    <li class="nav-item">
                        <router-link class="nav-link"
                                     :to="Links.Catalog">{{ $t('header.link.catalog') }}</router-link>
                    </li>
                    <li class="nav-item"
                        v-if="isAuthorized">
                        <router-link class="nav-link"
                                     :to="{name: 'cart'}">{{ $t('header.link.cart') }}</router-link>
                    </li>
                </ul>
            </div>
            <ul class="navbar-nav flex-row ml-md-auto d-md-flex align-items-baseline"
                v-if="isAuthorized">
                <li class="nav-item mx-3">
                    <locale-select class="m2" />
                </li>
                <li class="nav-item mx-1">
                    <user-panel class="nav-link p-2" />
                </li>
                <li class="nav-item mx-1">
                    <router-link :to="{name: 'logout'}">{{ $t('header.link.logout') }}</router-link>
                </li>
            </ul>
            <ul class="navbar-nav flex-row ml-md-auto d-md-flex align-items-baseline"
                v-else>
                <li class="nav-item mx-3">
                    <locale-select />
                </li>
                <li class="nav-item mx-1">
                    <router-link :to="{name: 'login'}">{{ $t('header.link.login') }}</router-link>
                </li>
                <li class="nav-item mx-1">
                    <router-link :to="{name: 'registration'}">{{ $t('header.link.registration') }}</router-link>
                </li>
            </ul>
        </nav>
    </div>
</template>

<script>
import LocaleSelect from "components/common/LocaleSelect.vue";
import { Links } from "properties.js";
import UserPanel from "components/common/UserPanel.vue";
import { mapGetters } from "vuex";

export default {
    components: {
        LocaleSelect,
        UserPanel
    },
    data() {
        return {
            Links: Links
        };
    },
    computed: {
        ...mapGetters(["isAuthorized"])
    }
};
</script>

<style>
</style>