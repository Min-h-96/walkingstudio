package walkingstudio.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import walkingstudio.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "walkingHsts",
    path = "walkingHsts"
)
public interface WalkingHstRepository
    extends PagingAndSortingRepository<WalkingHst, WalkingHstId> {
  Optional<WalkingHst> findBypUserIdAndBaseDateAndBaseTime(String pUserId, String baseDate, String baseTime);

  // 1. pUserId와 baseDate로 일치하는 걸음 수 총합 계산
  @Query("SELECT SUM(w.walking) FROM WalkingHst w WHERE w.pUserId = :pUserId AND w.baseDate = :baseDate")
  Integer calculateTotalWalking(String pUserId, String baseDate);

  // 2.가장 최근의 수정된 데이터 1건을 가져오는 쿼리 메서드
  @Query(value = "SELECT * FROM walk.walking_hst WHERE p_user_id = :pUserId ORDER BY mod_dt DESC NULLS LAST LIMIT 1", nativeQuery = true)
  Optional<WalkingHst> findLatestByPUserId(String pUserId);
}
