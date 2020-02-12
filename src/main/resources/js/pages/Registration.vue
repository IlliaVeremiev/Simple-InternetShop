<template>
    <div class="block-section">
        <div class="row justify-content-center">
            <div class="container row justify-content-md-center">
                <div class="col-12 col-sm-10 col-md-8 col-lg-6">
                    <h3 class="mb-4 billing-heading">{{$t('authorization.registration.page.header.registration')}}</h3>
                    <registration-form @success-registration="successRegistration" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import RegistrationForm from "components/authorization/registration/RegistrationForm.vue";
import { Links } from "properties.js";

export default {
    components: {
        RegistrationForm
    },
    methods: {
        successRegistration() {
            this.$router.push(this.$route.query.redirect || Links.Catalog);
        }
    }
};
</script>

<style>
</style>