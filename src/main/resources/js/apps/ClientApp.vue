<template>
    <div class="body">
        <shop-header />
        <section class="content-block content">
            <transition name="fade">
                <router-view :key="$route.fullPath"/>
            </transition>
        </section>
        <shop-footer />
        <toasts />
    </div>
</template>

<script>
import ShopHeader from "components/common/ShopHeader.vue";
import ShopFooter from "components/common/ShopFooter.vue";
import Toasts from "plugins/toasts/Toasts.vue";

export default {
    components: {
        ShopHeader,
        ShopFooter,
        Toasts
    },
    created() {
        this.$store.dispatch("updateUserInfo");
    }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Lato|Raleway&display=swap");

html,
body,
.body {
    height: 100%;
    min-width: 576px;
}

.body {
    display: flex;
    flex-direction: column;
}

.content {
    flex: 1 0 auto;
}

.fade-enter-active {
    transition: opacity 0.2s;
    transition-delay: 0.2s;
}
.fade-leave-active {
    transition: opacity 0.1s;
}
.fade-enter,
.fade-leave-to {
    opacity: 0;
}

.block-section {
    margin: 20px;
}
</style>