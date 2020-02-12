<template>
    <div />
</template>

<script>

export default {
    async created() {
        try {
            await this.$store.dispatch("logout");
            this.$router.go(-1);
        } catch (error) {
            console.log(error);
        }
    }
};
</script>

<style>
</style>