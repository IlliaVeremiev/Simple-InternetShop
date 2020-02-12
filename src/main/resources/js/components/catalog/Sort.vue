<template>
    <div class="btn-group btn-group-toggle"
         data-toggle="buttons">
        <label v-for="sort in sortings"
               :key="sort.key"
               class="btn btn-secondary"
               :class="{'active' : selected === sort.key}"
               @click="change(sort)">
            <input type="radio"
                   name="options"
                   :id="sort.key"
                   autocomplete="off"
                   checked />
            {{sort.value}}
        </label>
    </div>
</template>

<script>
export default {
    props: ["sortings"],
    data() {
        return {
            selected: ""
        };
    },
    created() {
        this.selected = this.$route.query.sort;
    },
    methods: {
        change(sort) {
            this.selected = sort.key;
            this.$emit("selected", sort);
        }
    }
};
</script>

<style>
</style>