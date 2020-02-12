<template>
    <div @keyup.enter="submit">
        <h6 v-if="errorMessage"
            class="mb-4 billing-heading text-danger" v-text="errorMessage"></h6>
        <div class="form-group">
            <label>{{$t('authorization.login.form.label.username')}}</label>
            <input class="form-control"
                   v-model="username" />
        </div>

        <div class="w-100"></div>

        <div class="form-group">
            <label>{{$t('authorization.login.form.label.password')}}</label>
            <input type="password"
                   class="form-control"
                   v-model="password" />
        </div>

        <button class="btn btn-primary"
                data-toggle="button"
                aria-pressed="false"
                autocomplete="off"
                @click="submit">{{$t('authorization.login.form.button.submit')}}</button>
    </div>
</template>

<script>

export default {
    data() {
        return {
            username: "",
            password: "",
            errorMessage: ""
        };
    },
    methods: {
        submit() {
            this.login({ username: this.username, password: this.password });
        },
        async login(data) {
            try {
                await this.$store.dispatch("login", data);
                if (this.$store.getters.isAuthorized) {
                    this.$emit("success-login");
                }
            } catch (error) {
                if (error.body.message == null) {
                    this.errorMessage = this.$t("authorization.login.failure.exception.badcredentials");
                } else {
                    this.errorMessage = error.body.message;
                }
            }
        }
    }
};
</script>

<style>
</style>