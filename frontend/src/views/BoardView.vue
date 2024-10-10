<script setup>
import { data, options } from "../data/GroupData";
import { ref, onMounted } from "vue";
import axios from "axios";
import { Line } from "vue-chartjs";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import ChartDataLabels from "chartjs-plugin-datalabels";
//챌린지
import { Button } from "@/components/ui/button";
import { toast } from "vue-sonner";
//카드
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
//icon
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
//DateView
import DateDisplay from "../components/DateDisplay.vue";
//apexChart
import ApexChart from "@/components/ApexChart.vue";
import GlobalChart from "@/components/GlobalChart.vue";
import CompanyChart from "@/components/CompanyChart.vue";
//statusBox
import StatusView from "@/components/StatusView.vue";
//RankingView
import RankingView from "@/components/RankingView.vue";
import ChallengeView from "@/components/ChallengeView.vue";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  ChartDataLabels
);

// 날씨 데이터를 저장할 reactive 상태 정의
const weatherData = ref({
  baseDate: "",
  baseTime: "",
  t1h: "", // 기온
  reh: "", // 습도
  wsd: "", // 풍속
  rn1: "", // 강수량
});

const weatherWeight = ref({
  weight: 1.0,
})

// API 요청 함수 정의
const fetchWeatherData = async () => {
  try {
    const response = await axios.get("http://4.230.151.151:8080/usrtFcstHsts/search/findAllByNxAndNy", {
      params: {
        nx: 60,
        ny: 127,
      },
    });
    
    // API 응답 데이터를 weatherData에 할당
    weatherData.value = {
      baseDate: response.data._embedded.usrtFcstHsts[0].baseDate,
      baseTime: response.data._embedded.usrtFcstHsts[0].baseTime,
      t1h: response.data._embedded.usrtFcstHsts[3].fcstValue || "N/A",
      reh: response.data._embedded.usrtFcstHsts[1].fcstValue || "N/A",
      wsd: response.data._embedded.usrtFcstHsts[7].fcstValue || "N/A",
      rn1: response.data._embedded.usrtFcstHsts[2].fcstValue || "N/A",
    };
  } catch (error) {
    console.error("Error fetching weather data:", error);
  }
};

const fetchWeatherWeight = async () => {
  try {
    const response = await axios.get("http://4.230.151.151:8080/pointStandardInfos/search/findByNxAndNy", {
      params: {
        nx: 60,
        ny: 127,
      },
    });

    console.log(response.data);    

    weatherWeight.value = {
      weight: response.data.weight
    }
  } catch (error) {
    console.error("Error fetching weather weight:", error);
  }
}

onMounted(() => {
  fetchWeatherData();
  fetchWeatherWeight();
});
</script>

<template>
  <div class="board-content">
    <div class="leader-box">
      <h1 class="leader-name">기준 정보</h1>
      <p class="desc">
        사용자의 위치 및 환경 정보와, 현재 걸음 부스트 정보를 확인할 수 있어요
      </p>
    </div>

    <div class="box env-box">
      <div class="row-box">
        <div class="column-box">
          <!-- DateDisplay는 기존 컴포넌트로 유지 -->
          <DateDisplay />
          <p>서울특별시</p>
        </div>
        <table class="weather-table">
          <tr>
            <td class="name">온도</td>
            <td class="value">{{ weatherData.t1h }}°</td>
          </tr>
          <tr>
            <td class="name">습도</td>
            <td class="value">{{ weatherData.reh }}%</td>
          </tr>
          <tr>
            <td class="name">풍속</td>
            <td class="value">{{ weatherData.wsd }}m/s</td>
          </tr>
          <tr>
            <td class="name">강수량</td>
            <td class="value">{{ weatherData.rn1 }}mm</td>
          </tr>
        </table>
      </div>

      <div class="boost-content row-box">
        <div>
          <h2>걸음 부스트</h2>
          <p class="desc">걸음 부스트는 날씨 환경에 영향을 받습니다</p>
        </div>
        <div class="row-box boost-box">
          <div class="row-box view">
            <div class="mine row-box">{{ weatherWeight.weight }}P</div>
            <div class="sign row-box">/</div>
            <div class="all row-box">1걸음</div>
          </div>
        </div>
      </div>
    </div>
    <div class="leader-box">
      <h1 class="leader-name">이 달의 랭킹</h1>
      <p class="desc">현재 진행 중인 내 랭킹 정보를 확인할 수 있어요</p>
    </div>
    <RankingView />
    <div class="leader-box">
      <h1 class="leader-name">기업 리더보드</h1>
      <p class="desc">워킹스튜디오에 등록된 기업별 랭킹을 확인할 수 있어요</p>
    </div>
    <div class="chart-box">
      <CompanyChart></CompanyChart>
    </div>
    <div class="leader-box">
      <h1 class="leader-name">전국 리더보드</h1>
      <p class="desc">워킹스튜디오에 등록된 기업별 랭킹을 확인할 수 있어요</p>
    </div>
    <div class="chart-box">
      <GlobalChart></GlobalChart>
    </div>
    <div class="leader-box">
      <h1 class="leader-name">팀 리더보드</h1>
      <p class="desc">내가 속한 팀에서 몇명인지 볼 수 있어요</p>
    </div>
    <div class="chart-box">
      <ApexChart></ApexChart>
    </div>
  </div>
  <ChallengeView />
  <StatusView />
</template>

<script>
export default {
  name: "그룹챌린지",
  components: {
    Line,
    DateDisplay: DateDisplay,
    ApexChart: ApexChart,
    GlobalChart: GlobalChart,
    StatusView: StatusView,
    RankingView: RankingView,
    ChallengeView: ChallengeView,
  },
};
</script>

<style scoped>
.board-content {
  width: calc(100vw - 500px);
  min-width: 800px;
  padding: 30px;
}

.board-content .box {
  margin-bottom: 12px;
}
/* env-box */

.env-box {
  display: flex;
  justify-content: space-between;
  height: 160px;
  padding: 20px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}

.env-box .weather-table {
  margin-left: 64px;
  font-size: 0.9rem;
}

.env-box .weather-table td {
  width: 60px;
}

.env-box .weather-table .name {
  color: var(--adaptiveGrey700);
}

.env-box .weather-table .value {
  font-weight: 700;
}

.env-box .boost-content h2 {
  font-size: 1.2rem;
  font-weight: 800;
}

.env-box .boost-content .desc {
  font-size: 0.8rem;
  font-weight: 400;
  color: var(--adaptiveGrey700);
}

.env-box .boost-box {
  margin-left: 12px;
  padding: 20px;
  border-radius: 12px;
  color: #fff;
  background-color: hsl(var(--primary));
  align-items: center;
}

.env-box .boost-box .view {
  height: 60px;
}

.env-box .boost-box .view div {
  margin: 0 4px;
}

.env-box .boost-box .mine {
  align-items: flex-start;
  font-size: 2rem;
  font-weight: 600;
}

.env-box .boost-box .sign {
  align-items: center;
}

.env-box .boost-box .all {
  justify-content: end;
  align-items: flex-end;
  font-size: 0.8rem;
  font-weight: 400;
  color: var(--adaptiveGrey100);
}

/* my-box */

.my-box {
  display: flex;
  flex-direction: column;
  height: 240px;
  padding: 20px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}

.board-content .leader-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 80px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
}

.board-content .leader-box h1 {
  font-size: 1.4rem;
  font-weight: 600;
}

.board-content .leader-box .desc {
  font-size: 0.9rem;
  color: var(--adaptiveGrey700);
}

.chart-box {
  width: 100%;
  height: 400px;
}
</style>
