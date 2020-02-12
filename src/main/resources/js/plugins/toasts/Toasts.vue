<template>
    <div aria-live="polite"
         aria-atomic="true"
         class="toasts-container">
        <div class="toast"
             v-for="toast in toasts"
             :key="toast.id"
             role="alert"
             aria-live="assertive"
             aria-atomic="true"
             @mouseover="pauseProgress(toast)"
             @mouseout="startProgress(toast)">
            <div class="toast-header"
                 :class="[toast.headerClass,{'toast-header-clickable' : toast.onClick}]"
                 @click="onClick(toast)">
                <img v-if="toast.image"
                     :src="toast.image"
                     class="rounded mr-2 toast-image"
                     :alt="toast.headerText" />
                <strong class="mr-auto text-light">{{$t(toast.headerText)}}</strong>
                <button type="button"
                        class="ml-2 mb-1 close"
                        data-dismiss="toast"
                        aria-label="Close"
                        @click.stop="removeToast(toast)">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body"
                 v-text="toast.message" />
        </div>
    </div>
</template>

<script>

export default {
    props: {
        showtime: {
            type: Number,
            default: 3000
        }
    },
    data() {
        return {
            toasts: [],
            index: 0
        };
    },
    created() {
        this.$toast.slot(this.handleToastEvent);
    },
    methods: {
        createToast(message, options = {}) {
            let toast = {
                id: this.index++,
                message: message,
                lifetime: options.lifetime || this.showtime,
                image: options.image,
                type: options.type,
                headerClass: options.headerClass,
                headerText: options.headerText,
                onClick: options.onClick
            };
            this.toasts.push(toast);
            this.createTimer(toast);
        },
        createTimer(toast) {
            let self = this;
            toast.timer = setInterval(() => this.removeToast(toast), toast.lifetime);
        },
        stopTimer(toast) {
            clearInterval(toast.timer);
        },
        removeToast(toast) {
            this.stopTimer(toast);
            const index = this.toasts.findIndex(item => item.id === toast.id);
            this.toasts.splice(index, 1);
        },
        pauseProgress(toast) {
            this.stopTimer(toast);
        },
        startProgress(toast) {
            this.createTimer(toast);
        },
        handleToastEvent(payload) {
            this.createToast(payload.message, payload.options);
        },
        onClick(toast) {
            if (toast.onClick) {
                this.$router.push(toast.onClick);
            }
        }
    }
};
</script>

<style scoped>
.toast {
    opacity: 1 !important;
}
.toast-header-clickable:hover {
    cursor: pointer;
}
.toasts-container {
    position: fixed;
    bottom: 10px;
    right: 10px;
    min-width: 350px;
    z-index: 1090;
}

.toast-image {
    max-height: 25px;
    max-width: 25px;
}
</style>
