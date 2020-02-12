<template>
    <div class="btn-group">
        <button type="button"
                class="btn btn-secondary dropdown-toggle text-truncate"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
                :disabled="!values.length">{{ selected.value }}</button>
        <div class="dropdown-menu dropdown-menu-right">
            <slot @click="selected" />
            <button class="dropdown-item"
                    type="button"
                    :disabled="value.key === selected.key"
                    v-for="value in values"
                    :key="value.key"
                    @click="change(value)"
                    v-text="value.value" />
        </div>
    </div>
</template>

<script>
export default {
    props: ["values"],
    data() {
        return {
            selected: {
                key: 0,
                value: ""
            }
        };
    },
    created() {
        this.update();
    },
    methods: {
        change(element) {
            if (this.selected.key === element.key) return;
            this.selected = element;
            this.$emit("selected", element);
        },
        selectFirst() {
            if (this.values.length > 0) {
                this.change(this.values[0]);
            }
        },
        setEmpty() {
            this.selected.key = 0;
            this.selected.value = "";
            this.$emit("selected", null);
        },
        update() {
            if (this.values.length === 0) {
                if (this.selected.key !== 0) {
                    this.setEmpty();
                }
            } else if (!this.values.some(e => e.key === this.selected.key)) {
                this.selectFirst();
            }
        }
    },
    watch: {
        values() {
            this.update();
        }
    }
};
</script>

<style scoped>
.disabled {
    pointer-events: none;
}
</style>