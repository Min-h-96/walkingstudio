<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";
import VueApexCharts from "vue3-apexcharts";
import axios from "axios";

const chartRef = ref(null); // 차트 참조

// 차트 옵션 및 데이터 관리
const chartOptions = ref({
    chart: {
        type: "bar",
        height: 380,
    },
    plotOptions: {
        bar: {
            barHeight: "100%",
            distributed: true,
            horizontal: true,
            dataLabels: {
                position: "bottom",
            },
            borderRadius: 10,
            borderRadiusApplication: "end",
        },
    },
    colors: [
        "#33b2df",
        "#E2CCFA",
        "#d4526e",
        "#13d8aa",
        "#A5978B",
        "#2b908f",
        "#f9a3a4",
        "#90ee7e",
        "#f48024",
        "#69d2e7",
    ],
    dataLabels: {
        enabled: true,
        textAnchor: "start",
        distributed: false,
        offsetX: 0,
        offsetY: 10,
        style: {
            colors: ["var(--adaptiveGrey900)"],
            fontSize: "1rem",
            fontWeight: "700",
        },
        formatter: function (val, opt) {
            return opt.w.globals.labels[opt.dataPointIndex] + " :  " + val;
        },
    },
    stroke: {
        width: 1,
        colors: ["#fff"],
    },
    xaxis: {
        categories: [], // 기본 카테고리
    },
    yaxis: {
        labels: {
            show: false,
        },
    },
    subtitle: {
        text: "9월 기업별 리더보드",
        align: "center",
    },
    animations: {
        enabled: true,
        easing: "easeinout",
        speed: 400,
        animateGradually: {
            enabled: true,
            delay: 150,
        },
        dynamicAnimation: {
            enabled: true,
            speed: 1000,
        },
    },
});

const series = ref([
    {
        name: "Walks",
        data: [0], // 초기 데이터
    },
]);

// 데이터를 갱신하는 함수
const fetchCompanyData = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/stat/walk/com", {
            params: {
                startDate: "2024-09-01",
                endDate: "2024-09-30",
            },
        });

        if (response.data && response.data.length > 0) {
            const companyNames = response.data.map((item) => item.comName); // 기업 이름
            const walkingData = response.data.map((item) => item.walking); // 기업 걸음 수

            // 시리즈 데이터 업데이트 및 애니메이션 활성화
            if (chartRef.value) {
                chartRef.value.updateSeries(
                    [
                        {
                            name: "Walks",
                            data: walkingData,
                        },
                    ],
                    true
                );

                // xaxis 카테고리 업데이트 및 애니메이션 활성화
                chartRef.value.updateOptions(
                    {
                        xaxis: {
                            categories: companyNames,
                        },
                    },
                    true,
                    true,
                    true
                );
            }
        }
    } catch (error) {
        console.error("기업 데이터를 불러오는 중 오류 발생:", error);
    }
};

let intervalId = null;

onMounted(() => {
    // 초기 데이터 로드
    fetchCompanyData();

    // 5초마다 데이터 갱신
    intervalId = setInterval(fetchCompanyData, 5000);
});

onBeforeUnmount(() => {
    // 컴포넌트가 언마운트되면 interval을 정리
    if (intervalId) {
        clearInterval(intervalId);
    }
});
</script>

<template>
    <div>
        <VueApexCharts ref="chartRef" width="100%" height="400" :options="chartOptions" :series="series" />
    </div>
</template>

<script>
export default {
    name: "CompanyChart",
};
</script>
