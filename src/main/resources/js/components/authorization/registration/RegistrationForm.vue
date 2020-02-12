<template>
    <div @keyup.enter="submit">
        <h6 v-if="errorMessage"
            class="mb-4 billing-heading text-danger" v-text="errorMessage"></h6>
        <div class="form-group">
            <label>{{$t('authorization.registration.form.label.username')}}</label>
            <input class="form-control"
                   v-model="username"
                   :class="validation.usernameClass"
                   @change="validateUsername" />
            <div class="invalid-feedback">{{$t('authorization.registration.form.label.invalid_username')}}</div>
        </div>

        <div class="w-100"></div>

        <div class="form-group">
            <label>{{$t('authorization.registration.form.label.password')}}</label>
            <input type="password"
                   class="form-control"
                   v-model="password"
                   :class="validation.passwordClass"
                   @change="validatePassword" />
            <div class="invalid-feedback">{{$t('authorization.registration.form.label.invalid_password')}}</div>
        </div>

        <button class="btn btn-primary"
                data-toggle="button"
                aria-pressed="false"
                autocomplete="off"
                @click="submit">{{$t('authorization.registration.form.button.submit')}}</button>
    </div>
</template>

<script>
import { Const } from 'properties.js'

export default {
    data() {
        return {
            validation: {
                usernameClass: "",
                passwordClass: ""
            },
            username: "",
            password: "",
            errorMessage: ""
        };
    },
    methods: {
        stringMatchRegExp(regExp, value) {
            return value.match(regExp);
        },
        usernameValid() {
            return this.stringMatchRegExp(Const.EmailRegExp, this.username);
        },
        passwordValid() {
            return this.password.length >= 3;
        },
        validateUsername() {
            let valid = this.usernameValid();
            this.validation.usernameClass = valid ? "is-valid" : "is-invalid";
            return valid;
        },
        validatePassword() {
            let valid = this.passwordValid();
            this.validation.passwordClass = valid ? "is-valid" : "is-invalid";
            return valid;
        },
        submit() {
            this.submitRegistration({
                username: this.username,
                password: this.password
            });
        },
        async submitRegistration(data) {
            if (!this.validateUsername() | !this.validatePassword()) return;
            try {
                await this.$store.dispatch('registrate', data);
                this.$emit("success-registration");
            } catch (error) {
                this.errorMessage = error.body.message;
            }
        }
    }
};
</script>

<style>
</style>