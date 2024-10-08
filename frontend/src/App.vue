<script setup>
import { RouterLink, RouterView, useRouter } from "vue-router";
import Pikiachu from "./components/Pikiachu.vue";
import { Toaster } from "@/components/ui/sonner";

const router = useRouter();

const goHome = () => {
    router.push("/");
};
</script>

<template>
    <header class="header-content">
        <span class="header-name" @click="goHome">워킹스튜디오</span>
    </header>
    <main class="main-content">
        <RouterView />
    </main>
    <!-- <nav>
            <RouterLink to="/">Home</RouterLink>
            <RouterLink to="/about">About</RouterLink>
        </nav>
        <div class="menu">
            <a v-for="(product, idx) in products" :key="idx">{{ product }}</a>
        </div>

        <div v-for="(skill, idx) in skills" :key="idx">
            <h4>{{ skill }}</h4>
            <p>3.5</p>
            <h2>{{ counts[idx] }}</h2>
            <Button @click="increase(idx)">날 클릭해</Button>
        </div>
        {{ price1 }} -->
    <!-- <Pikiachu :products="products" /> -->
    <Toaster />
</template>

<script>
export default {
    name: "App",
    data() {
        return {
            open: false,
            counts: [0, 0, 0],
            price1: 60,
            products: ["피카츄", "라이츄", "꼬부기"],
            skills: ["방전", "막치기", "하이드로캐논"],
        };
    },
    methods: {
        increase(idx) {
            this.counts[idx] += 1;
        },
    },
    components: {
        Pikiachu: Pikiachu,
    },
};
</script>

<style scoped>
.header-content {
    display: flex;
    align-items: center;
    padding: 0 20px;
    height: 60px;
    width: 100%;
    position: fixed;
    top: 0;
    z-index: 100;
    left: 0;
    left: auto;
    background-color: rgba(255, 255, 255);
    border-bottom: 1px solid var(--adaptiveGrey100);
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.header-name {
    color: hsl(var(--primary));
    cursor: pointer;
    font-size: 1.2rem;
    font-weight: 800;
}

.main-content {
    display: flex;
    width: 100%;
    max-width: 1660px;
    min-width: 1200px;
    background-color: hsla(222, 47%, 11%, 0.0025);
    height: 100%;
    position: relative;
    padding-top: 60px;
    overflow-y: auto; /* 스크롤 문제 해결 */
}
</style>
