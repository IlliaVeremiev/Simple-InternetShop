<template>
    <div class="block-section">
        <div class="row justify-content-center">
            <div class="container row justify-content-md-center flex-column align-items-center">
                <div>
                    <i class="ion-sad-outline sad-icon" />
                </div>
                <div class="error-code-404">404</div>
                <div class="error-status-npt-found">{{$t("error.page.404.status")}}</div>
                <div>{{$t("error.page.404.description_first_line")}}</div>
                <div>
                    {{$t("error.page.404.description_second_line")}}
                    <a href="#"
                       @click.prevent="goBack">{{$t("error.page.404.go_back_button")}}
                    </a>
                    {{$t("error.page.404.description_third_line")}}
                    <a :href="$t('common.head_url')">
                        {{$t('error.page.404.go_main_button')}}
                    </a>
                    {{$t("error.page.404.description_fourth_line")}}
                </div>
            </div>

        </div>
    </div>
</template>

<script>
export default {
    methods: {
        goBack() {
            this.$router.go(-1);
        }
    }
};
</script>

<style scoped>
.sad-icon {
    line-height: 1;
    font-size: 2000%;
}

.error-code-404 {
    font-size: 1000%;
    line-height: 1;
}

.error-status-npt-found {
    line-height: 1;
    margin-bottom: 10px;
    font-size: 300%;
}
</style>