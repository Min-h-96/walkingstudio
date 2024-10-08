<script setup>
import { Progress } from "@/components/ui/progress";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/userInfo";
import { ref, onMounted, onBeforeUnmount } from "vue";
import { Button } from "@/components/ui/button";
import axios from "axios";
//odometer
import Vue3Odometer from "vue3-odometer";
import "odometer/themes/odometer-theme-default.css";

const router = useRouter();
const userStore = useUserStore();
const userInfo = userStore.userInfo;

// 전국 챌린지
const globalCurrentSteps = ref(0); // 전국 챌린지의 현재 걸음 수
const globalGoalSteps = 100000000; // 전국 챌린지 목표 걸음 수
const globalCompletionRate = ref(0); // 전국 챌린지 달성률
const globalCompletionProgressRate = ref(0); // 전국 챌린지 진행률

//회사 챌린지
const companyCurrentSteps = ref(0); // 기업 챌린지의 현재 걸음 수
const companyGoalSteps = ref(2000000); // 기업 챌린지 목표 걸음 수
const companyCompletionRate = ref(0); // 달성률
const companyCompletionProgressRate = ref(0); // 달성프로그래스바

// 팀 챌린지
const teamCurrentSteps = ref(0); // 팀 챌린지의 현재 걸음 수
const teamGoalSteps = ref(200000); // 팀 챌린지 목표 걸음 수
const teamCompletionRate = ref(0); // 팀 달성률
const teamCompletionProgressRate = ref(0); // 팀 달성률 프로그래스바

let intervalId = null;

// 전국 챌린지 데이터 불러오기
const fetchGlobalData = async () => {
    try {
        const { startDate, endDate } = getCurrentMonthDates();

        // API로부터 전국 챌린지 데이터를 받아옴
        const response = await axios.get("http://localhost:8080/api/stat/walk/global", {
            params: {
                startDate: startDate,
                endDate: endDate,
            },
        });

        if (response.data) {
            globalCurrentSteps.value = response.data; // 전체 걸음 수 업데이트
            globalCompletionRate.value = Math.floor((globalCurrentSteps.value / globalGoalSteps) * 100); // 달성률 계산
            globalCompletionProgressRate.value = globalCompletionRate.value > 100 ? 100 : globalCompletionRate.value;
        }
    } catch (error) {
        console.error("전국 챌린지 데이터를 불러오는 중 오류 발생:", error);
    }
};

const fetchCompanyData = async () => {
    try {
        const { startDate, endDate } = getCurrentMonthDates();

        // API로부터 기업 챌린지 데이터를 받아옴
        const response = await axios.get("http://localhost:8080/api/stat/walk/com", {
            params: {
                startDate: startDate,
                endDate: endDate,
                comId: userInfo.comId,
            },
        });

        if (response.data && response.data.length > 0) {
            const companyData = response.data[0];
            companyCurrentSteps.value = companyData.walking; // 현재 걸음 수
            companyCompletionRate.value = Math.floor((companyCurrentSteps.value / companyGoalSteps.value) * 100); // 달성률 계산
            companyCompletionProgressRate.value = companyCompletionRate.value > 100 ? 100 : companyCompletionRate.value;
        }
    } catch (error) {
        console.error("기업 챌린지 데이터를 불러오는 중 오류 발생:", error);
    }
};

const fetchTeamData = async () => {
    try {
        const { startDate, endDate } = getCurrentMonthDates();

        // API로부터 팀 챌린지 데이터를 받아옴
        const response = await axios.get("http://localhost:8080/api/stat/walk/team", {
            params: {
                startDate: startDate,
                endDate: endDate,
                teamId: userInfo.teamId, // 팀 ID를 기반으로 API 호출
            },
        });

        if (response.data && response.data.length > 0) {
            const teamData = response.data[0];
            teamCurrentSteps.value = teamData.walking; // 팀의 현재 걸음 수
            teamCompletionRate.value = Math.floor((teamCurrentSteps.value / teamGoalSteps.value) * 100); // 팀 달성률 계산
            teamCompletionProgressRate.value = teamCompletionRate.value > 100 ? 100 : teamCompletionRate.value;
        }
    } catch (error) {
        console.error("팀 챌린지 데이터를 불러오는 중 오류 발생:", error);
    }
};

const getCurrentMonthDates = () => {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth(); // 현재 월 (0 = 1월)

    const startDate = new Date(year, month, 1).toISOString().split("T")[0]; // 해당 달의 첫째 날
    const endDate = new Date(year, month + 1, 0).toISOString().split("T")[0]; // 해당 달의 마지막 날

    return { startDate, endDate };
};

onMounted(() => {
    if (!userInfo) {
        router.push("/");
    } else {
        //초기화
        fetchGlobalData();
        fetchCompanyData();
        fetchTeamData();

        // 5초마다 데이터 갱신
        intervalId = setInterval(() => {
            fetchGlobalData();
            fetchCompanyData();
            fetchTeamData();
        }, 5000);
    }
});

onBeforeUnmount(() => {
    // 컴포넌트가 언마운트되면 interval을 정리
    if (intervalId) {
        clearInterval(intervalId);
    }
});
</script>
<template>
    <div v-if="userInfo" class="challenge-content">
        <div class="challenge-box">
            <div class="com-box">
                <h1>9월 전국 챌린지</h1>
            </div>
            <p class="desc">다음 걸음 목표 수를 달성하세요</p>
            <table class="target-table">
                <tr>
                    <td class="name">목표 걸음 수</td>
                    <td>{{ globalGoalSteps.toLocaleString() }}</td>
                </tr>
                <tr>
                    <td class="name">현재 걸음 수</td>
                    <Vue3Odometer class="o-text" :value="globalCurrentSteps" />
                </tr>
                <tr>
                    <td class="name">달성률</td>
                    <td><Vue3Odometer class="o-text" :value="globalCompletionRate" />%</td>
                </tr>
            </table>
            <div class="progress-box">
                <Progress :model-value="globalCompletionProgressRate" />
                <div class="percent-box">
                    <p>0%</p>
                    <p>100%</p>
                </div>
            </div>
            <Button disabled>보상받기</Button>
        </div>
        <!-- 기업 챌린지 -->
        <div class="challenge-box">
            <div class="com-box">
                <h1>기업 챌린지</h1>
                <img class="com-logo" src="@/assets/logo/KT.png" alt="KT_logo" />
            </div>
            <p class="desc">다음 걸음 목표 수를 달성하세요</p>
            <table class="target-table">
                <tr>
                    <td class="name">목표 걸음 수</td>
                    <td>{{ companyGoalSteps.toLocaleString() }}</td>
                </tr>
                <tr>
                    <td class="name">현재 걸음 수</td>
                    <Vue3Odometer class="o-text" :value="companyCurrentSteps" />
                </tr>
                <tr>
                    <td class="name">달성률</td>
                    <td><Vue3Odometer class="o-text" :value="companyCompletionRate" />%</td>
                </tr>
            </table>
            <div class="progress-box">
                <Progress :model-value="companyCompletionProgressRate" />
                <div class="percent-box">
                    <p>0%</p>
                    <p>100%</p>
                </div>
            </div>
            <Button :disabled="companyCompletionRate < 100">보상받기</Button>
        </div>
        <!-- 팀 챌린지 -->
        <div class="challenge-box">
            <div class="com-box">
                <h1>팀 챌린지</h1>
            </div>
            <p class="desc">다음 걸음 목표 수를 달성하세요</p>
            <table class="target-table">
                <tr>
                    <td class="name">목표 걸음 수</td>
                    <td>{{ teamGoalSteps.toLocaleString() }}</td>
                </tr>
                <tr>
                    <td class="name">현재 걸음 수</td>
                    <Vue3Odometer class="o-text" :value="teamCurrentSteps" />
                </tr>
                <tr>
                    <td class="name">달성률</td>
                    <td><Vue3Odometer class="o-text" :value="teamCompletionRate" />%</td>
                </tr>
            </table>
            <div class="progress-box">
                <Progress :model-value="teamCompletionProgressRate" />
                <div class="percent-box">
                    <p>0%</p>
                    <p>100%</p>
                </div>
            </div>
            <Button :disabled="teamCompletionRate < 100">보상받기</Button>
        </div>
    </div>
</template>

<script>
export default {
    name: "ChallengeView",
};
</script>

<style>
.challenge-content {
    top: 0;
    position: sticky;
    flex-direction: column;
    align-items: center;
    width: 500px;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 30px;
}
.challenge-box {
    display: flex;
    flex-direction: column;
    width: 300px;
    padding: 20px;
    border: 1px solid rgba(0, 0, 0, 0.1);
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
}

.challenge-box h1 {
    font-size: 1.3rem;
    font-weight: 600;
}

.challenge-box p {
    font-size: 0.9rem;
}

.challenge-box .desc {
    font-weight: 400;
    color: var(--adaptiveGrey700);
    margin-bottom: 8px;
}

.challenge-box .progress-box {
    margin: 12px 0;
}

.challenge-box .progress-box .percent-box,
.challenge-box .com-box {
    margin-top: 4px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

.challenge-box .target-table {
    font-size: 0.9rem;
}

.challenge-box .target-table .name {
    width: 6rem;
}

.challenge-box .com-box .com-logo {
    width: 2rem;
    height: 2rem;
    border-radius: 50%;
    border: 1px solid rgba(0, 0, 0, 0.2);
    object-fit: cover;
    display: inline-block;
}
</style>
