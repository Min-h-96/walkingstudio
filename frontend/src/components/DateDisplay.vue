<template>
    <div>
        <p class="date-box">
            {{
                currentTime.toLocaleDateString("ko-KR", {
                    year: "numeric",
                    month: "long",
                    day: "numeric",
                    weekday: "long",
                })
            }}
        </p>
        <p class="time-box">
            {{ currentTime.toLocaleTimeString() }}
        </p>
    </div>
</template>

<script type="module">
import { useCurrentTime } from "../composables/useCurrentTime";
export default {
    name: "DateDisplay",
    setup() {
        const { currentTime } = useCurrentTime();
        console.log(currentTime.value);

        return { currentTime };
    },
};
</script>

<style>
.date-box {
    width: 180px;
    font-size: 0.9rem;
    font-weight: 500;
    color: var(--adaptiveGrey700);
}

.time-box {
    font-size: 1.5rem;
    font-weight: 900;
}
</style>
