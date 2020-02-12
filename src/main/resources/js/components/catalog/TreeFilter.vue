<template>
    <div class="list-group">
        <li class="list-group-item-dark disabled list-group-item list-group-item-actionlist-group-item d-flex justify-content-between align-items-center">{{$t('catalog.content.filter.treeview.title')}}</li>
        <a class="list-group-item list-group-item-actionlist-group-item d-flex justify-content-between align-items-center"
           :class="{ active: currentCategory == null}"
           @click.prevent="selected('')">{{$t('catalog.content.filter.treeview.all')}}</a>
        <a class="list-group-item list-group-item-actionlist-group-item d-flex justify-content-between align-items-center"
           :class="{ active: currentCategory === category.category.code}"
           v-for="category in categories"
           :key="category.category.id"
           @click.prevent="selected(category.category)">
            {{category.category.name}}
            <span class="badge badge-pill"
                  :class="{'badge-secondary': currentCategory !== category.category.code, 'badge-light':currentCategory === category.category.code}">{{category.count}}</span>
        </a>
    </div>
</template>

<script>
import { mapState } from "vuex";

export default {
    data() {
        return {
            currentCategory: ""
        }
    },
    computed: {
        ...mapState({ categories: state => state.catalog.categories })
    },
    created() {
        this.$store.dispatch('loadAllCategories');
        this.currentCategory = this.$route.query.category;
    },
    methods: {
        selected(category) {
            this.$emit("categoryUpdated", category);
        }
    }
};
</script>

<style scoped>
a.active {
    background-color: #343a40 !important;
    color: #fff !important;
    border-color: #fff !important;
}
</style>