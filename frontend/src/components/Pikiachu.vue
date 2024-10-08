<template>
    <div class="pika">나는 피카츄 컴포넌트</div>
    <h2 v-for="(product, idx) in products">{{ product }}</h2>
</template>

<script>
export default {
    name: "Pikachue",
    props: {
        products: Array,
    },
};
</script>

<style></style>
