export const data = {
    labels: ["2024-05", "2024-06", "2024-07", "2024-08", "2024-09"],
    datasets: [
        {
            label: "KT",
            backgroundColor: "rgba(248, 121, 121, 0.5)",
            borderColor: "rgba(248, 121, 121, 0.8)",
            data: [3231, 1729, 2387, 1847, 2321],
        },
        {
            label: "BC카드",
            backgroundColor: "rgba(54, 162, 235, 0.5)",
            borderColor: "rgba(54, 162, 235, 0.8)",
            data: [5812, 1729, 454, 2356, 1212],
        },
        {
            label: "밀리의서재",
            backgroundColor: "rgba(75, 192, 192, 0.5)",
            borderColor: "rgba(75, 192, 192, 0.8)",
            data: [8562, 3485, 35689, 3678, 2323],
        },
        {
            label: "K지니뮤직T",
            backgroundColor: "rgba(153, 102, 255, 0.5)",
            borderColor: "rgba(153, 102, 255, 0.8)",
            data: [3423, 23656, 2387, 9645, 4343],
        },
        {
            label: "케이뱅크",
            backgroundColor: "rgba(255, 206, 86, 0.5)",
            borderColor: "rgba(255, 206, 86, 0.8)",
            data: [15577, 765, 4563, 5421, 3223],
        },
    ],
};

export const options = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
        legend: {
            position: "top",
            labels: {
                boxWidth: 10,
            },
        },
        tooltip: {
            enabled: true,
        },
        datalabels: {
            anchor: "end",
            align: "top",
            color: "#444",
            font: {
                weight: 400,
            },
            formatter: (value) => value, // 데이터 레이블 값 표시
        },
    },
    scales: {
        x: {
            title: {
                display: true,
                text: "월",
            },
        },
        y: {
            title: {
                display: true,
                text: "걸음 수",
            },
        },
    },
};
