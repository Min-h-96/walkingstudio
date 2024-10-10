<script setup>
import { ref, onMounted, onBeforeUnmount, watch } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/userInfo";
import axios from "axios";

const router = useRouter();
const userStore = useUserStore();
const userInfo = userStore.userInfo;

// useState
const isStatusBarOpen = ref(true);
const lastModified = ref(""); // 마지막 수집 시간
const walkingCount = ref(0); // 오늘 걸음 수
const isWalking = ref(true); // 걷고 있는지 여부
const opacity = ref(1); // 사람의 색상 투명도 (1 = 불투명, 0.5 = 반투명 등)

let pollingInterval;

// 상태체크
async function fetchWalkingData() {
    try {
        const response = await axios.post("http://4.230.151.151:8080/walkingHsts/todayStat", {
            pUserId: userInfo.puserId,
            baseDate: new Date().toISOString().split("T")[0].replace(/-/g, ""),
        });

        // console.log(response.data);

        const { allWalking, lastModified: lastMod } = response.data;
        lastModified.value = lastMod;
        walkingCount.value = allWalking;
        lastModified.value = formatDateTime(lastMod); // 포맷된 시간 저장

        const currentTime = new Date();
        const collectedTime = new Date(lastMod);
        const timeDiff = (currentTime - collectedTime) / 1000;

        if (timeDiff > 60) {
            isWalking.value = false;
            opacity.value = 0.5;
        } else {
            isWalking.value = true;
            opacity.value = 1;
        }
    } catch (error) {
        console.error("Error fetching walking data:", error);
    }
}

onMounted(() => {
    if (!userInfo) {
        router.push("/");
    } else {
        fetchWalkingData();
        pollingInterval = setInterval(fetchWalkingData, 5000);
    }
});

onBeforeUnmount(() => {
    clearInterval(pollingInterval);
});

//토글 바
function toggleStatusBar() {
    isStatusBarOpen.value = !isStatusBarOpen.value;
}

// 날짜 포맷팅 함수
function formatDateTime(dateTime) {
    const date = new Date(dateTime);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0"); // 1월은 0이므로 +1
    const day = String(date.getDate()).padStart(2, "0");
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const seconds = String(date.getSeconds()).padStart(2, "0");

    return `${year}년 ${month}월 ${day}일 ${hours}시 ${minutes}분 ${seconds}초`;
}

// 숫자 변경 감지 및 애니메이션 효과
watch(walkingCount, (newCount, oldCount) => {
    if (newCount !== oldCount) {
        const statusItem = document.querySelector(".status-item .value");
        statusItem.classList.add("animate");
        setTimeout(() => {
            statusItem.classList.remove("animate");
        }, 300); // 0.5초 동안 애니메이션 적용
    }
});
</script>

<template>
    <div v-if="userInfo">
        <!-- 하단 바를 열고 닫는 버튼 -->
        <button
            :class="{ 'status-button': true, open: isStatusBarOpen, closed: !isStatusBarOpen }"
            @click="toggleStatusBar"
        >
            {{ isStatusBarOpen ? "닫기" : "열기" }}
        </button>
        <!-- 하단 바 -->
        <transition name="slide">
            <div v-if="isStatusBarOpen" class="row-box status-box">
                <div :style="{ opacity: opacity }" class="avatar-box">
                    <div class="running">
                        <div :class="{ paused: !isWalking }" class="outer">
                            <div :class="{ paused: !isWalking }" class="body">
                                <div :class="{ paused: !isWalking }" class="arm behind"></div>
                                <div :class="{ paused: !isWalking }" class="arm front"></div>
                                <div :class="{ paused: !isWalking }" class="leg behind"></div>
                                <div :class="{ paused: !isWalking }" class="leg front"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="status-content column-box">
                    <div class="name-box row-box">
                        <span class="name">{{ userInfo.puserName }}</span>
                        <span>님</span>
                    </div>
                    <p class="desc">{{ isWalking ? "지금 걷고 계신가요?" : "지금은 쉬고 계시군요?" }}</p>
                    <p class="desc last-modified">마지막 걸은 시간 : {{ lastModified }}</p>
                    <div class="status-item column-box">
                        <p class="desc">오늘 걸음 수</p>
                        <span class="value">{{ walkingCount.toLocaleString() }}</span>
                    </div>
                </div>
            </div>
        </transition>
    </div>
</template>

<script>
export default {
    name: "StatusView",
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@700&display=swap");

.status-item .value {
    transition: transform 0.3s ease-in-out, opacity 0.3s ease-in-out;
}

.status-item .value.animate {
    transform: scale(1.1);
    opacity: 0.5;
}

.outer.paused,
.outer .body.paused,
.outer .arm.paused,
.outer .leg.paused,
.arm.paused::before,
.leg.paused::before {
    animation: none;
}

.avatar-box {
    aspect-ratio: 1 / 1;
    height: 100%;
    background-color: #f5f5f5;
    border-radius: 50%;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2), inset 0 2px 5px rgba(255, 255, 255, 0.5);
    border: 2px solid #ddd;
    display: flex;
    align-items: center;
    justify-content: center;
}

.avatar-box:hover {
    box-shadow: 0 6px 15px rgba(0, 0, 0, 0.3), inset 0 3px 7px rgba(255, 255, 255, 0.7);
    transform: translateY(-2px);
    transition: all 0.3s ease;
}

/* 버튼  */
.status-button {
    position: fixed;
    width: 100%;
    left: 50%;
    transform: translateX(-50%);
    padding: 10px 20px;
    background-color: hsl(var(--primary));
    color: #fff;
    border: none;
    cursor: pointer;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    transition: all 0.3s ease, bottom 0.5s ease, top 0.5s ease;
    z-index: 1000;
    animation: blink 1s infinite ease-in-out; /* 깜빡임 애니메이션 추가 */
}

/* 닫혔을 때 (closed 상태일 때) */
.status-button.closed {
    bottom: 0px;
}

/* 열렸을 때 (open 상태일 때) */
.status-button.open {
    bottom: 360px;
}

/* 버튼 Hover 효과 */
.status-button:hover {
    background-color: #f5f5f5;
    color: hsl(var(--primary));
    border: 1px solid hsl(var(--primary));
}

@keyframes blink {
    0%,
    100% {
        transform: translateX(-50%);
        opacity: 1;
    }
    50% {
        transform: translateX(-50%);
        opacity: 0.8;
    }
}

.status-box {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    height: 360px;
    background-color: #e8e8e8;
    border-top-left-radius: 20px;
    border-top-right-radius: 20px;
    box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
    padding: 48px;
    z-index: 20;
}

.slide-enter-active,
.slide-leave-active {
    transition: transform 0.5s ease;
}

.slide-enter-from {
    transform: translateY(100%); /* 처음에는 화면 밖에 위치 */
}

.slide-leave-to {
    transform: translateY(100%); /* 닫힐 때 아래로 사라짐 */
}

.status-content {
    width: 100%;
    height: 100%;
    margin-left: 20px;
}

.status-content .name-box {
    align-items: end;
}

.status-content .name-box .name {
    font-size: 1.5rem;
    font-weight: 700;
    margin-right: 2px;
}

.status-content .desc {
    margin-top: 4px;
    font-size: 0.9rem;
}

.status-content .desc.last-modified {
    color: var(--adaptiveGrey600);
}

.status-content .status-item {
    height: 100%;
    justify-content: center;
    align-items: center;
}

.status-content .status-item .desc {
    font-size: 1.2rem;
    color: var(--adaptiveGrey700);
}

.status-content .status-item .value {
    font-family: "Poppins", sans-serif;
    font-weight: 700;
    font-size: 6rem;
    color: hsl(var(--primary));
    letter-spacing: 2px; /* 글자 간격 */
    text-align: center; /* 가운데 정렬 */
    display: block;
}

.running {
    --color: hsl(var(--primary));
    --duration: 0.8s;
    --scale: 3;
    transform: scale(var(--scale, 1)) translateY(-4px);
}

.outer {
    animation: outer var(--duration) linear infinite;
}

.body {
    background: var(--color);
    height: 15px;
    width: 8px;
    border-radius: 4px;
    transform-origin: 4px 11px;
    position: relative;
    transform: rotate(32deg);
    animation: body var(--duration) linear infinite;
}

.body:before {
    content: "";
    width: 8px;
    height: 8px;
    border-radius: 4px;
    bottom: 16px;
    left: 0;
    position: absolute;
    background: var(--color);
}

.arm,
.arm:before,
.leg,
.leg:before {
    content: "";
    width: var(--w, 11px);
    height: 4px;
    top: var(--t, 0);
    left: var(--l, 2px);
    border-radius: 2px;
    transform-origin: 2px 2px;
    position: absolute;
    background: var(--c, var(--color));
    transform: rotate(var(--r, 0deg));
    animation: var(--name, arm-leg) var(--duration) linear infinite;
}

.arm:before {
    --l: 7px;
    --name: arm-b;
}

.arm.front {
    --r: 24deg;
    --r-to: 164deg;
}

.arm.front:before {
    --r: -48deg;
    --r-to: -36deg;
}

.arm.behind {
    --r: 164deg;
    --r-to: 24deg;
}

.arm.behind:before {
    --r: -36deg;
    --r-to: -48deg;
}

.leg {
    --w: 12px;
    --t: 11px;
}

.leg:before {
    --t: 0;
    --l: 8px;
}

.leg.front {
    --r: 10deg;
    --r-to: 108deg;
}

.leg.front:before {
    --r: 18deg;
    --r-to: 76deg;
}

.leg.behind {
    --r: 108deg;
    --r-to: 10deg;
    --c: none;
}

.leg.behind:before {
    --c: var(--color);
    --r: 76deg;
    --r-to: 18deg;
}

.leg.behind:after {
    content: "";
    top: 0;
    right: 0;
    height: 4px;
    width: 6px;
    clip-path: polygon(2px 0, 6px 0, 6px 4px, 0 4px);
    border-radius: 0 2px 2px 0;
    position: absolute;
    background: var(--color);
}

@keyframes outer {
    50% {
        transform: translateY(0);
    }
    25%,
    75% {
        transform: translateY(4px);
    }
}

@keyframes body {
    50% {
        transform: rotate(16deg);
    }
    25%,
    75% {
        transform: rotate(24deg);
    }
}

@keyframes arm-leg {
    50% {
        transform: rotate(var(--r-to));
    }
}

@keyframes arm-b {
    30%,
    70% {
        transform: rotate(var(--r-to));
    }
}
</style>
