<template>
    <div v-if="user">{{$t('header.greeting', {'username':user.email.split("@")[0]})}}</div>
</template>

<script>
export default {
    computed: {
        user() {
            return this.$store.state.authentication.user;
        }
    },
    created() {
        this.$store.dispatch("updateUserInfo");
    }
};
</script>

<style>
</style>