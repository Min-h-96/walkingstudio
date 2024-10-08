import axios from "axios";
import cron from "node-cron";
import UserList from "../config/userList.js"; // userList 모듈 가져오기

const userLastHour = {};

cron.schedule("*/5 * * * * *", async () => {
    try {
        const currentDate = new Date();
        const currentHour = currentDate.getHours(); // 현재 시간을 가져옴 (HH)

        // 유저마다 반복문을 돌려서 각 유저의 데이터를 보냄
        UserList.forEach(async (user) => {
            const baseDate = currentDate.toISOString().split("T")[0].replace(/-/g, ""); // yyyyMMdd
            const baseTime = `${currentHour.toString().padStart(2, "0")}00`; // HH00

            // 유저별로 lastHour 체크. 시간이 변경되면 걸음 수를 초기화
            if (userLastHour[user.userId] !== currentHour) {
                user.walk = 0;
                userLastHour[user.userId] = currentHour;
            }

            const walkIncrement = Math.floor(Math.random() * 21) + 0; // 0 ~ 20 사이의 랜덤 값
            user.walk += walkIncrement;

            const requestData = {
                puserId: user.userId,
                baseDate: baseDate,
                baseTime: baseTime,
                walking: user.walk,
            };

            console.log(`[REQ] : ${user.userId} at ${baseTime}, walking: ${user.walk}`);

            // App서버로 송신
            const response = await axios.post("http://20.249.200.206:8080/app/walk/collect", requestData);

            console.log("[RES] : ", response.data);
        });
    } catch (error) {
        console.error("Error sending data:", error.message);
    }
});
