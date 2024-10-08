<template>
    <div>
        <apexchart width="100%" height="400" :options="chartOptions" :series="series"></apexchart>
    </div>
</template>

<script>
export default {
    name: "GlobalChart",
    data: function () {
        return {
            chartOptions: {
                chart: {
                    type: "area",
                    height: 200,
                    zoom: {
                        enabled: false,
                    },
                },
                dataLabels: {
                    enabled: false,
                },
                stroke: {
                    curve: "smooth", // For smooth curves between points
                },
                title: {
                    align: "left",
                },
                xaxis: {
                    categories: [
                        "0-10%",
                        "10-20%",
                        "20-30%",
                        "30-40%",
                        "40-50%",
                        "50-60%",
                        "60-70%",
                        "70-80%",
                        "80-90%",
                        "90-100%",
                    ], // Percentage ranges for x-axis
                    title: {
                        text: "Percentage Range",
                    },
                },
                yaxis: {
                    title: {
                        text: "",
                    },
                },
                legend: {
                    horizontalAlign: "left",
                },
                fill: {
                    type: "gradient", // Add gradient to make the area chart look better
                    gradient: {
                        shadeIntensity: 1,
                        opacityFrom: 0.7,
                        opacityTo: 0.9,
                        stops: [0, 90, 100],
                    },
                },
            },
            series: [
                {
                    name: "Data Distribution",
                    data: [2, 5, 23, 34, 22, 11, 5, 4, 3, 1], // Count of distribution per percentage range
                },
            ],
        };
    },
};
</script>
