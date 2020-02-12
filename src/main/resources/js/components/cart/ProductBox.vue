<template>
    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 p-0">
        <div class="border m-1">
            <!-- Component layout -->
            <div class="row p-2">
                <!-- Image layout -->
                <div class="col-md-4 col-sm-4 col-4">
                    <div class="image-box">
                        <img :src="item.product.primaryPhoto.path"
                             alt="Product photo"
                             class="product-box-image" />
                    </div>
                </div>
                <!-- Info layout -->
                <div class="col-md-8 col-sm-8 col-8">
                    <div>
                        <!-- createProductSpecification boudle click button -->
                        <button class="float-right close close-button"
                                @click="removePressed()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="p-3 h-100">
                        <div class="d-flex h-100 flex-column">
                            <!-- Naming row -->
                            <div class="title-box text-truncate"
                                 :title="item.product.name">{{item.product.name}}</div>
                            <!-- Price row -->
                            <div class="d-flex justify-content-between align-items-center mt-auto">
                                <div v-if="item.count > 0"
                                     class="price-box font-weight-bold">{{item.product.price}} x {{item.count}} = {{item.subtotal}}</div>
                                <div v-else
                                     class="text-secondary out-of-stock-box">{{$t('cart.product.out_of_stock')}}</div>

                                <div class="d-flex flex-column">
                                    <counter @countChanged="countChanged"
                                             :startCount="item.count"
                                             :min="1"></counter>
                                    <warehouses-dropdown :warehouses="item.product.warehouseEntries"
                                                         :count="count"
                                                         class="warehouse-dropdown"
                                                         @selected="warehouseSelected" />
                                </div>
                                <!--  -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--  -->
        </div>
    </div>
</template>

<script>
import Counter from "components/common/Counter.vue";
import WarehousesDropdown from "components/cart/WarehousesDropdown.vue";

export default {
    components: {
        Counter,
        WarehousesDropdown
    },
    props: ["item"],
    data() {
        return {
            count: 0
        };
    },
    created() {
        this.count = this.item.count;
    },
    methods: {
        countChanged(count) {
            this.count = count;
            this.$emit("productCountChanged", { product: this.item.product, count: count });
        },
        removePressed() {
            this.$emit("removePressed", this.item.product);
        },
        warehouseSelected() {
            //TODO
        }
    }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Lato|Raleway&display=swap");
@import url("https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css");

.count-info {
    padding: 0px 30px 0px 30px;
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

.close-button {
    outline: none;
}
</style>