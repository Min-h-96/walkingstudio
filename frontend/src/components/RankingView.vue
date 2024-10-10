<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import { useRouter } from "vue-router";
import { Button } from "@/components/ui/button";
import { useUserStore } from "@/stores/userInfo";
import axios from "axios";

//odometer
import Vue3Odometer from "vue3-odometer";
import "odometer/themes/odometer-theme-default.css";

const router = useRouter();
const userStore = useUserStore();
const userInfo = userStore.userInfo;

console.log(userInfo);

const personalWalking = ref(0); // 개인 걸음 수
const totalUsers = ref(0); // 총 사용자 수
const userRanking = ref(0); // 개인 랭킹
let intervalId = null;

const fetchPersonalStatData = async () => {
    try {
        const now = new Date();
        const year = now.getFullYear();
        const month = String(now.getMonth() + 1).padStart(2, "0");
        const baseDate = `${year}${month}01`;

        const response = await axios.get("http://4.230.151.151:8080/users/userPersonalStat", {
            params: {
                pUserId: userInfo.puserId,
                baseDate: baseDate,
            },
        });

        if (response.data) {
            console.log(response.data);
            personalWalking.value = response.data.walking; // 걸음 수 업데이트
            totalUsers.value = response.data.total; // 총 사용자 수 업데이트
            userRanking.value = response.data.ranking; // 랭킹 업데이트
        }
    } catch (error) {
        console.error("개인 통계 데이터를 불러오는 중 오류 발생:", error);
    }
};

// const fetchPersonalWalkingData = async () => {
//     try {
//         const now = new Date();
//         const startDate = new Date(now.getFullYear(), now.getMonth(), 1).toISOString().split("T")[0];
//         const endDate = new Date(now.getFullYear(), now.getMonth() + 1, 0).toISOString().split("T")[0];

//         // API 호출로 개인 걸음 수 가져오기
//         const response = await axios.get("http://localhost:8080/api/stat/walk/personal", {
//             params: {
//                 pUserId: userInfo.puserId,
//                 startDate: startDate,
//                 endDate: endDate,
//             },
//         });

//         if (response.data) {
//             personalWalking.value = response.data; // 걸음 수 업데이트
//         }
//     } catch (error) {
//         console.error("걸음 수 데이터를 불러오는 중 오류 발생:", error);
//     }
// };

onMounted(() => {
    if (!userInfo) {
        router.push("/");
    } else {
        // 페이지가 마운트되면 처음으로 데이터 불러오기
        fetchPersonalStatData();

        // 5초마다 데이터 갱신
        intervalId = setInterval(fetchPersonalStatData, 5000);
    }
});

onBeforeUnmount(() => {
    if (intervalId) {
        clearInterval(intervalId);
    }
});
</script>

<template>
    <div v-if="userInfo" class="box my-box">
        <div class="info-box">
            <div class="row-box name-div">
                <p class="name">{{ userInfo.puserName }}</p>
                <p>님</p>
            </div>
            <div class=""></div>
            <div class="row-box value-box">
                <p>보유 포인트</p>
                <Button> 124,393P</Button>
            </div>
            <div class="row-box value-box">
                <p>걸음 수</p>
                <Button class="custom-button">
                    <Vue3Odometer class="o-text" :value="personalWalking" /> <span class="space">걸음</span></Button
                >
            </div>
        </div>
        <div class="info-content row-box">
            <div class="row-box">
                <img class="logo" src="@/assets/logo/map.webp" />
                <div class="column-box logo-content">
                    <h2 class="">내 위치</h2>
                    <p class="desc">서울특별시</p>
                    <div class="row-box rank-box">
                        <div class="mine row-box">{{ userRanking }}등</div>
                        <div class="sign row-box">/</div>
                        <div class="all row-box">{{ totalUsers }}명</div>
                    </div>
                </div>
            </div>
            <div class="row-box">
                <img src="@/assets/logo/kt.svg" class="logo" />
                <div class="column-box logo-content">
                    <h2 class="">내 회사</h2>
                    <p class="desc">KT</p>
                    <div class="row-box rank-box">
                        <div class="mine row-box">3등</div>
                        <div class="sign row-box">/</div>
                        <div class="all row-box">6개</div>
                    </div>
                </div>
            </div>
            <div class="row-box">
                <img src="@/assets/logo/AICT.webp" class="logo" />
                <div class="column-box logo-content">
                    <h2 class="">내 팀</h2>
                    <p class="desc">AICT Futer TF</p>
                    <div class="row-box rank-box">
                        <div class="mine row-box">30등</div>
                        <div class="sign row-box">/</div>
                        <div class="all row-box">41명</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "RankingView",
    data() {},
};
</script>

<style>
.my-box .info-box {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 60px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    padding-bottom: 12px;
}
.my-box .info-box .name-div {
    align-items: end;
}

.my-box .info-box .name-div .name {
    font-size: 1.2rem;
    font-weight: 700;
    margin-right: 2px;
}

.my-box .info-content {
    display: flex;
    height: 100%;
    align-items: center;
    justify-content: space-between;
}

.my-box .info-box .value-box {
    display: flex;
    align-items: center;
}

.my-box .value-box p {
    font-size: 1.2rem;
    color: var(--adaptiveGrey800);
    margin: 0 8px;
}

.my-box .logo {
    height: 120px; /* 너비를 100%로 설정 */
    aspect-ratio: 1 / 1; /* 너비와 높이를 1:1 비율로 고정 */
    border-radius: 50%;
    border: 1px solid rgba(0, 0, 0, 0.2);
    object-fit: cover;
    display: inline-block;
}

.my-box .logo-content {
    margin-left: 20px;
}

.my-box .logo-content h2 {
    font-size: 1.2rem;
    font-weight: 800;
}

.my-box .logo-content .desc {
    color: var(--adaptiveGrey800);
}

.my-box .rank-box {
    margin-top: 12px;
    height: 40px;
    width: 120px;
}

.my-box .rank-box div {
    margin: 0 1px;
}

.my-box .rank-box .mine {
    align-items: flex-start;
    font-size: 1.2rem;
    font-weight: 600;
}

.my-box .rank-box .sign {
    align-items: center;
}

.my-box .rank-box .all {
    justify-content: end;
    align-items: flex-end;
    font-size: 0.8rem;
    font-weight: 400;
    color: var(--adaptiveGrey700);
}
.custom-button span {
    color: white;
}

.custom-button .space {
    margin-left: 4px;
}
</style>
