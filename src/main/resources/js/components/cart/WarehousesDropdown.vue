<template>
    <dropdown :values="availableWarehouses"
              class="warehouse-dropdown"
              v-on="$listeners" />
</template>

<script>
import Dropdown from "components/common/Dropdown.vue";
import { Validate } from "properties.js";

export default {
    components: {
        Dropdown
    },
    props: ["warehouses", "count"],
    computed: {
        availableWarehouses() {
            return this.warehouses
                .filter(e => e.count >= this.count)
                .filter(e => Validate("AvailableWarehouseEntryStatus", e.status))
                .filter(e => e.count > 0)
                .map(e => { return { key: e.warehouse.id, value: e.warehouse.name }; });
        }
    }
};
</script>

<style>
.warehouse-dropdown {
    max-width: 126px;
}
</style>