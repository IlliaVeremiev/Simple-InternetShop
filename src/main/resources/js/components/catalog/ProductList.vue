<template>
    <div class="row">
        <product-box v-for="product in products"
                     :product="product"
                     :key="product.id"
                     v-on="$listeners" />
    </div>
</template>

<script>
import ProductBox from "components/catalog/ProductBox.vue";

export default {
    components: {
        ProductBox
    },
    props: ["products"]
};
</script>

<style>
</style>