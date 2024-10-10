<script setup>
import { ref } from "vue";
import {
  PinInput,
  PinInputGroup,
  PinInputInput,
} from "@/components/ui/pin-input";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/userInfo"; // Pinia 스토어 임포트
import axios from "axios";

const value = ref([]);
const errorMessage = ref("");
const router = useRouter();
const userStore = useUserStore(); // Pinia 스토어 인스턴스 가져오기

const handleComplete = async () => {
  try {
    // OTP 값을 가져와서 API 요청
    const otp = value.value.join(""); // 배열로 되어있는 OTP 값을 문자열로 변환
    const response = await axios.post("http://4.230.151.151:8080/users", {
      otp,
    });
    console.log(response);

    // 응답 받은 유저 정보를 Pinia 스토어에 저장
    userStore.setUserInfo(response.data);

    // 유저 정보를 저장한 후 다음 페이지로 이동
    router.push("/board");
  } catch (error) {
    if (error.response && error.response.status === 400) {
      errorMessage.value = error.response.data;
    } else {
      errorMessage.value = "OTP 인증에 실패했습니다.";
    }

    alert(errorMessage.value);
    value.value = [];
  }
};
</script>

<template>
  <div class="home-content">
    <div class="otp-box">
      <h1 class="otp-name">워킹 스튜디오</h1>
      <p>사내 앱을 통해 발급된 OTP 6자리를 입력해 주세요</p>
      <PinInput
        id="pin-input"
        v-model="value"
        placeholder="-"
        @complete="handleComplete"
      >
        <PinInputGroup>
          <PinInputInput v-for="(id, index) in 6" :key="id" :index="index" />
        </PinInputGroup>
      </PinInput>
    </div>
  </div>
</template>

<style scoped>
.home-content {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.otp-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 672px;
  padding: 64px 136px;
  color: #fff;
  background: linear-gradient(0deg, rgba(0, 0, 0, 0.05), rgba(0, 0, 0, 0.08)),
    rgba(0, 0, 0, 0.05);
  background-blend-mode: overlay, normal;
  border: 1px solid rgba(0, 0, 0, 0.05);
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.15);
  backdrop-filter: 40px;
  border-radius: 12px;
}

.otp-name {
  font-size: 1.5rem;
  font-weight: 600;
}

.otp-box p {
  color: var(--adaptiveGrey900);
  font-weight: 100;
}
</style>
