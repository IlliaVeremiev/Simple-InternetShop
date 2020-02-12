<template>
    <div class="col-xl-3 col-lg-4 col-md-6 col-sm-12 col-12 p-0 d-md-flex">
        <div class="border m-1 content-container">
            <div class="row p-2">
                <div class="col-md-12 col-sm-4 col-4">
                    <div class="image-box">
                        <img :src="product.primaryPhoto.path"
                             alt="Product photo"
                             class="product-box-image" />
                    </div>
                </div>
                <div class="col-md-12 col-sm-8 col-8 p-3">
                    <div class="d-flex h-100 flex-column">
                        <div class="title-box text-truncate"
                             :title="product.name">{{product.name}}</div>
                        <div class="d-flex justify-content-between align-items-center mt-auto">
                            <div v-if="available"
                                 class="price-box font-weight-bold">{{product.price}}</div>
                            <div v-else
                                 class="text-secondary out-of-stock-box">out of stock</div>
                            <button class="cart-button"
                                    @click="addToCart"
                                    v-if="available">
                                <i class="ion-ios-cart"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!--  -->
        </div>
    </div>
</template>

<script>
import { Modals, Validate } from "properties.js";
import NotAuthorizedError from "errors/NotAuthorizedError.js";

export default {
    props: ["product"],
    methods: {
        async addToCart() {
            try {
                await this.$store.dispatch("addToCart", this.product);
                this.$toast.info(this.$t("catalog.cart.toast.successfuly_added", { productname: this.product.name }), { image: this.product.primaryPhoto.path, onClick: { name: "cart" } });
            } catch (error) {
                if (error instanceof NotAuthorizedError) {
                    this.$modal.show("login-modal", Modals.CatalogLogin);
                } else {
                    this.$toast.error(this.$t("catalog.cart.toast.failed", { productname: this.product.name, exception: error.data.message }), { image: this.product.primaryPhoto.path });
                }
            }
        }
    },
    computed: {
        available() {
            return this.product.warehouseEntries.some(entry => Validate("AvailableWarehouseEntryStatus", entry.status) && entry.count > 0);
        }
    }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Lato|Raleway&display=swap");
@import url("https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css");

.cart-button {
    border-radius: 15%;
    padding: 0px 30px 0px 30px;
    background-color: #f85c37;
    box-shadow: inset 0px 39px 0px -24px #e67a73;
    border-radius: 4px;
    border: 1px solid #ffffff;
    color: #ffffff;
    font-size: 140%;
    outline: 0;
}

.cart-button:active {
    background-color: #f84c27;
}

.out-of-stock-box {
    font: 11px/1.35 Arial, Helvetica, sans-serif;
}

.price-box {
    font-family: "Raleway";
    font-size: 120%;
}

.title-box {
    font: 14px/1.35 Arial, Helvetica, sans-serif;
}

.image-box {
    padding: 0px;
}
.product-box {
    height: 100px;
}

.product-box-image {
    object-fit: scale-down;
    width: 100%;
    height: 150px;
}

.content-container {
    min-width: 0;
}
</style>