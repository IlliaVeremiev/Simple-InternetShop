<template>
    <div class="d-flex align-items-center counter-container">
        <input class="no-spin round-input font-weight-bold"
               type="number"
               :min="min"
               :max="max"
               :value="count"
               @keyup="setCount($event.target.value)" />
        <button @click="increment"
                class="counter-button up-button m-1">
            <i class="ion-ios-arrow-up"></i>
        </button>

        <button @click="decrement"
                class="counter-button down-button m-1">
            <i class="ion-ios-arrow-down"></i>
        </button>
    </div>
</template>

<script>
export default {
    props: {
        startCount: {
            type: Number,
            default: 0
        },
        min: {
            type: Number,
            default: -Infinity
        },
        max: {
            type: Number,
            default: Infinity
        }
    },
    data() {
        return {
            count: 1
        };
    },
    created() {
        this.count = Number.parseInt(this.startCount);
    },
    watch:{
        startCount(old){
            this.count = old;
        }
    },
    methods: {
        increment() {
            this.setCount(this.count + 1);
        },
        decrement() {
            this.setCount(this.count - 1);
        },
        setCount(count) {
            count = Number.parseInt(count) | 0;
            if (count < this.min) {
                if (this.count === this.min) {
                    return;
                }
                count = this.min;
            }
            if (count > this.max) {
                if (this.count === this.max) {
                    return;
                }
                count = this.max;
            }
            this.count = count;
            this.$emit("countChanged", count);
        }
    }
};
</script>

<style scoped>
.no-spin::-webkit-inner-spin-button,
.no-spin::-webkit-outer-spin-button {
    -webkit-appearance: none !important;
    margin: 0 !important;
    -moz-appearance: textfield !important;
}

.round-input {
    border-radius: 999px;
    outline: none;
    border: none;
    height: 30px;
    text-align: center;
    width: 126px;
    font-family: "Raleway";
    font-size: 120%;
}

.round-input:focus {
    box-shadow: 0px 0px 0px 1px #aaa;
}

.counter-button {
    outline: none;
    border-radius: 999px;
    width: 30px;
    height: 30px;
    border-width: 0.1px;
    box-shadow: 0px 0px 1px 1px #777;
    background-color: transparent;
}

.up-button {
    margin-left: -30px !important;
}
.down-button {
    margin-left: -130px !important;
}

.counter-container {
    padding-right: 92px;
}
</style>