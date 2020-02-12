<template>
    <div class="block-section">
        <div class="row justify-content-center">
            <div class="container row">
                <div class="col-12 col-md-12">
                    <product-list :cartEntries="cart.entries"
                                  @productCountChanged="productCountChanged"
                                  @removePressed="removePressed" />
                    <totals :productSubtotal="cart.subtotal" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ProductList from "components/cart/ProductList.vue";
import Totals from "components/cart/Totals.vue";
import { mapState } from "vuex";

export default {
    components: {
        ProductList,
        Totals
    },
    created() {
        this.$store.dispatch("updateCart");
    },
    computed: {
        ...mapState({ cart: state => state.cart })
    },
    methods: {
        productCountChanged(payload) {
            this.$tim.wait('updateCart', async () => {
                await this.$store.dispatch("setCartProductCount", payload);
                this.$store.dispatch("updateCart");
            }, 300);
        },
        async removePressed(product) {
            await this.$store.dispatch("removeCartProduct", product);
            this.$store.dispatch("updateCart");
        }
    }
};
</script>

<style>
</style>