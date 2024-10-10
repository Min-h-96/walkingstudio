package walkingstudio.infra;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import walkingstudio.domain.WalkingHst;
import walkingstudio.domain.WalkingHstRepository;
import walkingstudio.dto.WalkCollectRequest;

@Service
@Transactional
public class WalkingHstService {

  @Autowired
  private WalkingHstRepository walkingHstRepository;

  @Autowired
  private EntityManager entityManager;

  public void saveOrUpdateWalkData(WalkCollectRequest request) {
    Optional<WalkingHst> existingRecord = walkingHstRepository.findBypUserIdAndBaseDateAndBaseTime(request.getPuserId(), request.getBaseDate(), request.getBaseTime());

    if (existingRecord.isPresent()) {
      // 이미 데이터가 있으면 업데이트
      WalkingHst walkData = existingRecord.get();
      walkData.setWalking(request.getWalking());
      walkData.setModDt(LocalDateTime.now());
      System.out.println("Updating walking data"); // 확인 로그
      walkingHstRepository.save(walkData);
    } else {
      // 데이터가 없으면 새로 추가
      System.out.println("1"); // 확인 로그
      WalkingHst walkData = new WalkingHst();
      System.out.println("2"); // 확인 로그
      walkData.setPUserId(request.getPuserId());
      walkData.setBaseDate(request.getBaseDate());
      walkData.setBaseTime(request.getBaseTime());
      walkData.setWalking(request.getWalking());
      walkData.setCretDt(LocalDateTime.now());
      walkData.setModDt(LocalDateTime.now());
      System.out.println("Saving walking data"); // 확인 로그
      walkingHstRepository.save(walkData);
    }

  }

  // 요청 받은 pUserId와 baseDate를 기반으로 걸음 수와 최신 수정 시간 반환
  public Map<String, Object> getTodayStat(String pUserId, String baseDate) {
    Map<String, Object> result = new HashMap<>();

    // 1. 해당 pUserId와 baseDate에 일치하는 걸음 수 총합 계산
    Integer allWalking = walkingHstRepository.calculateTotalWalking(pUserId, baseDate);

    // 2. pUserId에 해당하는 가장 최근 수정된 데이터 조회
    Optional<WalkingHst> latestWalkingHst = walkingHstRepository.findLatestByPUserId(pUserId);

    if (latestWalkingHst.isPresent()) {
      WalkingHst latestStat = latestWalkingHst.get();
      result.put("baseDate", baseDate);
      result.put("allWalking", allWalking);
      result.put("lastModified", latestStat.getModDt());
      result.put("pUserId", pUserId);
    } else {
      result.put("message", "No data found for the user.");
    }

    return result;
  }
}
