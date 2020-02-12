<template>
    <select @change="setLocale"
            class="form-control form-control-sm">
        <option v-for="locale in getAvailableLocales"
                :selected="$store.getters.currentLocale === locale"
                :key="locale"
                :value="locale">{{$t(`header.locale.${locale}`)}}</option>
    </select>
</template>

<script>
export default {
    computed: {
        getAvailableLocales() {
            return Object.keys(this.$i18n.messages);
        }
    },
    methods: {
        setLocale(event) {
            let locale = event.target.value;
            this.$i18n.locale = locale;
            this.$store.dispatch("setLocale", locale);
        }
    }
};
</script>

<style>
</style>