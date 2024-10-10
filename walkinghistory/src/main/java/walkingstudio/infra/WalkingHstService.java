package walkingstudio.infra;

import java.time.LocalDateTime;
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
}
