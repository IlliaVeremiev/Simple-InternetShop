<template>
    <div class="block-section">
        <div class="row justify-content-center">
            <div class="container row">
                <div class="col-12 col-md-4">
                    <!--  -->
                    <div class="navbar-expand-md sticky-top py-2">
                        <div class="collapse navbar-collapse"
                             id="bd-docs-nav">
                            <div class="w-100">
                                <tree-filter @categoryUpdated="categoryUpdated" />
                            </div>
                        </div>
                    </div>
                    <!--  -->
                </div>
                <div class="col-12 col-md-8">
                    <div class="row align-items-baseline">
                        <button class="btn btn-outline-secondary d-md-none p-0 collapsed col-1"
                                data-target="#bd-docs-nav"
                                type="button"
                                data-toggle="collapse"
                                aria-controls="bd-docs-nav"
                                aria-expanded="false">
                            <i class="ion-ios-color-filter nav-link"></i>
                        </button>
                        <search-input @searchTextUpdated="searchTextUpdated"
                                      class="col-11 col-md-12 mb-2" />
                        <div class="col-12 mb-2 d-flex justify-content-center">
                            <sort :sortings="sortings"
                                  @selected="sortSelected" />
                        </div>
                    </div>
                    <product-list :products="catalog.products" />
                    <div class="col-12 row justify-content-center pagination-container">
                        <pagination class="col-6"
                                    :current="page"
                                    :total="catalog.pagesCount"
                                    @pageUpdated="pageUpdated" />
                    </div>
                </div>
            </div>
        </div>
        <modal :name="'login-modal'">
            <button type="button"
                    class="btn btn-secondary"
                    data-dismiss="modal">{{$t('catalog.cart.popup.authorization.close_button')}}</button>
            <button type="button"
                    class="btn btn-primary"
                    @click="redirect('login')">{{$t('catalog.cart.popup.authorization.login_button')}}</button>
            <button type="button"
                    class="btn btn-primary"
                    @click="redirect('registration')">{{$t('catalog.cart.popup.authorization.registration_button')}}</button>
        </modal>
    </div>
</template>

<script>
import ProductList from "components/catalog/ProductList.vue";
import SearchInput from "components/catalog/SearchInput.vue";
import TreeFilter from "components/catalog/TreeFilter.vue";
import Sort from "components/catalog/Sort.vue";
import Pagination from "components/common/Pagination.vue";
import Modal from "plugins/modal/Modal.vue";
import { Const } from "properties.js";
import { mapState } from "vuex";

export default {
    components: {
        ProductList,
        SearchInput,
        TreeFilter,
        Sort,
        Pagination,
        Modal
    },
    data() {
        return {
            page: 0,
            category: null,
            searchText: "",
            sortOrder: null,
            sortings: Const.AvailableCatalogSortings
        };
    },
    computed: {
        ...mapState(["catalog"])
    },
    created() {
        this.page = Number.parseInt(this.$route.params.page);
        this.category = this.$route.query.category;
        this.searchText = this.$route.query.searchText;
        this.sortOrder = this.$route.query.sort;
        this.loadPage();
    },
    methods: {
        pushPage() {
            this.$router.push({ params: { page: this.page }, query: { category: this.category, searchText: this.searchText, sort: this.sortOrder } });
        },
        loadPage() {
            this.$store.dispatch("loadCatalogPage", { page: this.page - 1, category: this.category, searchText: this.searchText, sort: this.sortOrder });
        },
        categoryUpdated(category) {
            this.category = category.code;
            this.page = 1;
            this.pushPage();
        },
        searchTextUpdated(searchText) {
            this.searchText = searchText;
            this.page = 1;
            this.pushPage();
        },
        sortSelected(sort) {
            this.sortOrder = sort.key;
            this.pushPage();
        },
        pageUpdated(page) {
            this.page = page;
            this.pushPage();
        },
        validateParams() {
            if (Number.parseInt(this.$route.params.page) < 0) {
                this.page = 1;
                this.pushPage();
            }
        },
        redirect(pageName) {
            this.$modal.hide("login-modal");
            this.$router.push({ name: pageName, query: { redirect: this.$router.currentRoute.fullPath } });
        }
    }
};
</script>

<style scoped>
.pagination-container {
    margin-left: 0px;
    padding-left: 31px;
}
</style>