<template>
    <div>
        <apexchart width="100%" height="500" :options="chartOptions" :series="series"></apexchart>
    </div>
</template>

<script>
export default {
    name: "ApexChart",
    data: function () {
        return {
            chartOptions: {
                chart: {
                    height: 350,
                    type: "line",
                    dropShadow: {
                        enabled: true,
                        color: "#000",
                        top: 18,
                        left: 7,
                        blur: 10,
                        opacity: 0.2,
                    },
                    zoom: {
                        enabled: false,
                    },
                    toolbar: {
                        show: false,
                    },
                },
                colors: [
                    "rgba(248, 121, 121, 0.8)", // KT
                    "rgba(54, 162, 235, 0.8)", // BC카드
                    "rgba(75, 192, 192, 0.8)", // 밀리의서재
                    "rgba(153, 102, 255, 0.8)", // K지니뮤직T
                    "rgba(255, 206, 86, 0.8)", // 케이뱅크
                ],
                dataLabels: {
                    enabled: true,
                },
                stroke: {
                    curve: "smooth",
                },
                grid: {
                    borderColor: "#e7e7e7",
                    row: {
                        colors: ["#f3f3f3", "transparent"],
                        opacity: 0.5,
                    },
                },
                markers: {
                    size: 1,
                },
                xaxis: {
                    categories: ["2024-05", "2024-06", "2024-07", "2024-08", "2024-09"], // 기존의 labels
                    title: {
                        text: "월",
                    },
                },
                yaxis: {
                    title: {
                        text: "걸음 수",
                    },
                },
                legend: {
                    position: "top",
                    horizontalAlign: "right",
                    floating: true,
                },
            },
            series: [
                {
                    name: "KT",
                    data: [3231, 1729, 2387, 1847, 23212], // KT의 데이터
                },
                {
                    name: "BC카드",
                    data: [5812, 1729, 454, 2356, 1212], // BC카드의 데이터
                },
                {
                    name: "밀리의서재",
                    data: [8562, 3485, 35689, 3678, 2323], // 밀리의서재의 데이터
                },
                {
                    name: "지니뮤직",
                    data: [3423, 23656, 2387, 9645, 4343], // K지니뮤직T의 데이터
                },
                {
                    name: "케이뱅크",
                    data: [15577, 765, 4563, 5421, 3223], // 케이뱅크의 데이터
                },
            ],
        };
    },
};
</script>
