<template>
    <div class="block-section">
        <div class="row justify-content-center">
            <div class="container row justify-content-md-center">
                <div class="col-12 col-sm-10 col-md-8 col-lg-6">
                    <h3 class="mb-4 billing-heading">{{$t('authorization.login.page.header.login')}}</h3>
                    <login-form @success-login="successLogin" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import LoginForm from "components/authorization/login/LoginForm.vue";
import { Links } from "properties.js";

export default {
    components: {
        LoginForm
    },
    methods: {
        successLogin() {
            this.$router.push(this.$route.query.redirect || Links.Catalog);
        }
    }
};
</script>

<style>
</style>