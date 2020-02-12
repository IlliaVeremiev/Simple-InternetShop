<template>
    <ul class="d-flex justify-content-center pagination">
        <li class="page-item"
            :class="{'disabled': current <= 1}">
            <a href="#"
               @click.prevent="setPage(current-1)"
               class="page-link">Previous</a>
        </li>
        <li class="page-item">
            <a href="#"
               @click.prevent="setPage(1)"
               class="page-link"
               v-if="current > 1">1</a>
        </li>

        <li class="page-item">
            <a href="#"
               @click.prevent="setPage(2)"
               class="page-link"
               v-if="current > 2">2</a>
        </li>

        <span v-if="current - 3 >= 3"></span>

        <li class="page-item">
            <a href="#"
               @click.prevent="setPage(current-2)"
               class="page-link"
               v-if="current - 2 >= 3">{{current-2}}</a>
        </li>

        <li class="page-item">
            <a href="#"
               @click.prevent="setPage(current-1)"
               class="page-link"
               v-if="current - 1 >= 3">{{current-1}}</a>
        </li>

        <li class="page-item disabled">
            <p class="page-link">{{current}}</p>
        </li>

        <li class="page-item">
            <a href="#"
               @click.prevent="setPage(current+1)"
               class="page-link"
               v-if="current + 1 <= total -2">{{current+1}}</a>
        </li>

        <li class="page-item">
            <a href="#"
               @click.prevent="setPage(current+2)"
               class="page-link"
               v-if="current + 2 <= total -2">{{current+2}}</a>
        </li>

        <span v-if="current + 3 <= total -2"></span>

        <li class="page-item">
            <a href="#"
               @click.prevent="setPage(total-1)"
               class="page-link"
               v-if="current < total-1">{{total-1}}</a>
        </li>

        <li class="page-item">
            <a href="#"
               @click.prevent="setPage(total)"
               class="page-link"
               v-if="current < total">{{total}}</a>
        </li>

        <li class="page-item"
            :class="{'disabled': current >= total}">
            <a href="#"
               @click.prevent="setPage(current+1)"
               class="page-link">Next</a>
        </li>
    </ul>
</template>

<script>
export default {
    props: {
        current: {
            type: Number,
            default: 1
        },
        total: {
            type: Number,
            default: 0
        }
    },
    methods: {
        setPage(page) {
            this.$emit("pageUpdated", page);
        }
    }
};
</script>

<style>
</style>