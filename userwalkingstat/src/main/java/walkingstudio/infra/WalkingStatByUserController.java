package walkingstudio.infra;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import walkingstudio.domain.UserInfoRepository;
import walkingstudio.domain.WalkingStatByUser;
import walkingstudio.domain.WalkingStatByUserRepository;

@RestController
@Transactional
@CrossOrigin(origins = "*")
public class WalkingStatByUserController {

    @Autowired
    private WalkingStatByUserRepository walkingStatByUserRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @GetMapping("/userPersonalStat")
    public ResponseEntity<?> getUserPersonalStat(
        @RequestParam String pUserId,
        @RequestParam String baseDate) {

        // 1. 총 사용자 수 가져오기
        long totalUsers = userInfoRepository.count();

        // 2. baseDate를 해당 월의 첫째 날로 변환
        LocalDate firstDayOfMonth = LocalDate.parse(baseDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
            .withDayOfMonth(1);

        // 3. 해당 pUserId에 해당하는 사용자의 걷기 통계 조회
        Optional<WalkingStatByUser> userStatOptional = walkingStatByUserRepository.findBypUserIdAndBaseDate(pUserId, java.sql.Date.valueOf(firstDayOfMonth));

        if (userStatOptional.isPresent()) {
            WalkingStatByUser userStat = userStatOptional.get();

            // 4. 해당 사용자의 랭킹 계산 (walking이 높은 순서대로 정렬하여 랭킹 산정)
            int ranking = walkingStatByUserRepository.calculateUserRanking(pUserId, java.sql.Date.valueOf(firstDayOfMonth));

            // 5. 결과 반환
            Map<String, Object> result = new HashMap<>();
            result.put("total", totalUsers);
            result.put("ranking", ranking);
            result.put("walking", userStat.getWalking());

            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User stats not found for the given base date.");
        }
    }
}
