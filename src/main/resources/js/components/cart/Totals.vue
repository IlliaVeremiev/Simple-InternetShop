<template>
    <div>
        <table class="table table-bordered">
            <tbody>
                <tr>
                    <th scope="row">{{$t('cart.totals.subtotal_for_products')}}</th>
                    <td>{{productSubtotal}}</td>
                </tr>
                <tr>
                    <th scope="row">{{$t('cart.totals.discounts')}}</th>
                    <td>0</td>
                </tr>
                <tr>
                    <th scope="row">{{$t('cart.totals.total_for_products')}}</th>
                    <td>0</td>
                </tr>
                <tr>
                    <th scope="row">{{$t('cart.totals.tax')}}</th>
                    <td>0</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    props: ['productSubtotal']
};
</script>

<style>
</style>