<template>

    <div class="input-group">
        <input type="text"
               class="form-control"
               :placeholder="$t('catalog.content.filter.search.placeholder')"
               v-model="searchInputText"
               @keyup.enter="search" />
        <div class="input-group-append">
            <button class="btn btn-outline-secondary"
                    type="button"
                    @click="search"
                    v-text="$t('catalog.content.filter.search.button')" />
        </div>
    </div>

</template>

<script>
export default {
    data() {
        return {
            searchInputText: ""
        };
    },
    created() {
        this.searchInputText = this.$route.query.searchText;
    },
    methods: {
        search() {
            this.$emit("searchTextUpdated", this.searchInputText);
        }
    }
};
</script>

<style>
</style>