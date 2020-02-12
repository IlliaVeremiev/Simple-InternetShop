<template>
    <div class="row">
        <product-box v-for="item in cartEntries"
                     :item="item"
                     :key="item.id"
                     v-on="$listeners"/>
    </div>
</template>

<script>
import ProductBox from "components/cart/ProductBox.vue";

export default {
    components: {
        ProductBox
    },
    props: ["cartEntries"],
    data() {
        return {
            items: []
        };
    }
};
</script>

<style>
</style>